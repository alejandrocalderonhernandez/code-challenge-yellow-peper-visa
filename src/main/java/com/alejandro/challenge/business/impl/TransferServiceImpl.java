package com.alejandro.challenge.business.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

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
import com.alejandro.challenge.util.JsonUtil;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

	private TransferRepository transferRepository;
	private AccountRepository accountRepository;
	
	@Autowired
	public TransferServiceImpl(TransferRepository transferRepository, AccountRepository accountRepository) {
		this.transferRepository = transferRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public TransferResponseModel save(TransferDTO transfer) {
		Optional<AccountEntity> destinationAcountOptional = this.accountRepository.findById(transfer.getDestinationAccount());
		Optional<AccountEntity> originAccountOptional = this.accountRepository.findById(transfer.getOriginAccount());
		
		if (destinationAcountOptional.isEmpty() || originAccountOptional.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.ACCOUNT_NOT_FOUNF);
		}
		
		AccountEntity destinationAccount = destinationAcountOptional.get();
		AccountEntity originAccount = originAccountOptional.get();
		BigDecimal ammountToTransfer = transfer.getAmount();
		
		this.validBusinessRules(originAccount, ammountToTransfer);
		
		BigDecimal destinationBalance = destinationAccount.getBalance();
		BigDecimal originBalance = originAccount.getBalance();
		int originAttempts = originAccount.getAttempts();
		
		originAccount.setBalance(originBalance.subtract(ammountToTransfer));
		destinationAccount.setBalance(destinationBalance.add(ammountToTransfer));
		originAccount.setAttempts(originAttempts + 1);
		System.out.println(originAccount.getAttempts());
		this.accountRepository.save(originAccount);
		this.accountRepository.save(destinationAccount);
		this.saveTransfer(transfer);
		
		return new TransferResponseModel(StatusTypes.OK, new ArrayList<>(), null, null);
	}
	
	private void saveTransfer(TransferDTO transfer) {
		TransferEntity toSave = (TransferEntity) JsonUtil.bodyMapper(transfer, TransferEntity.class);
		toSave.setDate(LocalDateTime.now());
		this.transferRepository.save(toSave);
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
}
