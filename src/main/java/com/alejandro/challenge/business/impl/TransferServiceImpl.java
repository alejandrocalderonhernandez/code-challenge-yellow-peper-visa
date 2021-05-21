package com.alejandro.challenge.business.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.challenge.business.definition.TransferService;
import com.alejandro.challenge.common.constants.ExceptionMessages;
import com.alejandro.challenge.common.constants.NumberConstants;
import com.alejandro.challenge.common.constants.StatusTypes;
import com.alejandro.challenge.common.exceptions.InsufficientFundsException;
import com.alejandro.challenge.common.exceptions.LimitExceededException;
import com.alejandro.challenge.model.dto.TransferDTO;
import com.alejandro.challenge.model.entity.AccountEntity;
import com.alejandro.challenge.model.entity.TransferEntity;
import com.alejandro.challenge.model.response.TransferResponseModel;
import com.alejandro.challenge.repository.AccountRepository;
import com.alejandro.challenge.repository.TransferRepository;
import com.alejandro.challenge.rest.client.CurrencyClient;
import com.alejandro.challenge.util.JsonUtil;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

	
	private static final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

	private TransferRepository transferRepository;
	private AccountRepository accountRepository;
    private CurrencyClient currencyClient;
	
	@Autowired
	public TransferServiceImpl(
			TransferRepository transferRepository, 
			AccountRepository accountRepository,
			CurrencyClient currencyClient
	) {
		this.transferRepository = transferRepository;
		this.accountRepository = accountRepository;
		this.currencyClient = currencyClient;
	}

	@Override
	public TransferResponseModel save(TransferDTO transfer) {
		Optional<AccountEntity> destinationAcountOptional = this.accountRepository.findById(transfer.getDestinationAccount());
		Optional<AccountEntity> originAccountOptional = this.accountRepository.findById(transfer.getOriginAccount());
		
		if (destinationAcountOptional.isEmpty() || originAccountOptional.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.ACCOUNT_NOT_FOUNF);
		}
		
		BigDecimal cad = this.getCad(transfer.getCurrency());
		BigDecimal ammountToTransfer = transfer.getAmount().divide(cad, 2, RoundingMode.HALF_UP);
		AccountEntity destinationAccount = destinationAcountOptional.get();
		AccountEntity originAccount = originAccountOptional.get();

		this.validBusinessRules(originAccount, ammountToTransfer);
		
		BigDecimal destinationBalance = destinationAccount.getBalance();
		BigDecimal originBalance = originAccount.getBalance();
		BigDecimal tax = this.getTax(ammountToTransfer);
		
		int originAttempts = originAccount.getAttempts();
		originBalance = originBalance.subtract(tax);
		
		originAccount.setBalance(originBalance.subtract(ammountToTransfer));
		destinationAccount.setBalance(destinationBalance.add(ammountToTransfer));
		originAccount.setAttempts(originAttempts + 1);
		
		this.accountRepository.save(originAccount);
		this.accountRepository.save(destinationAccount);
		this.saveTransfer(transfer);
		
		return new TransferResponseModel(StatusTypes.OK, new ArrayList<>(), tax, cad);
	}

    private BigDecimal getCad(String currency) {
    	if (this.currencyClient.getCurrency().hasBody()) {
    	 	Map<String, BigDecimal> response  = this.currencyClient.getCurrency().getBody().getRates();
        	if(response.containsKey(currency)) {
        		return response.get(currency);
        	}
		}
    	return BigDecimal.ZERO;
    }
    
	private void saveTransfer(TransferDTO transfer) {
		TransferEntity toSave = (TransferEntity) JsonUtil.bodyMapper(transfer, TransferEntity.class);
		toSave.setDate(LocalDateTime.now());
		this.transferRepository.save(toSave);
		log.info("Transaction saved", toSave);
	}
	
	private BigDecimal getTax(BigDecimal ammountToTransfer) {
		if(ammountToTransfer.compareTo(TAX_MIN_CONDITION) >= NumberConstants.ZERO) {
			return ammountToTransfer.multiply(TAX_MIN);
		}
		return ammountToTransfer.multiply(TAX_MAX);
	}
	
	private void validBusinessRules(AccountEntity origin, BigDecimal trasferAmmount) {
		BigDecimal ammountToTransfer = trasferAmmount;
		BigDecimal originBalance = origin.getBalance();
		
		if(origin.getAttempts() > MAX_ATTEMPTS) {
			throw new LimitExceededException();
		}
		
		if(ammountToTransfer.compareTo(originBalance) >= 1) {
			throw new InsufficientFundsException();
		}
		
		if(ammountToTransfer.compareTo(new BigDecimal(NumberConstants.ZERO)) <= NumberConstants.ZERO) {
			throw new IllegalArgumentException(ExceptionMessages.AMMOUNT_NOT_VALID);
		}
	}
	
	private static final int MAX_ATTEMPTS = 2;
	private static final BigDecimal TAX_MIN_CONDITION = new BigDecimal(100);
	private static final BigDecimal TAX_MIN = new BigDecimal(0.2);
	private static final BigDecimal TAX_MAX = new BigDecimal(0.5);
}
