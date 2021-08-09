package com.alejandro.challenge.business.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.challenge.business.definition.AccountService;
import com.alejandro.challenge.common.constants.ExceptionMessages;
import com.alejandro.challenge.common.constants.NumberConstants;
import com.alejandro.challenge.common.constants.StatusTypes;
import com.alejandro.challenge.model.dto.AccountDTO;
import com.alejandro.challenge.model.entity.AccountEntity;
import com.alejandro.challenge.model.response.AccountResponseModel;
import com.alejandro.challenge.repository.AccountRepository;
import com.alejandro.challenge.util.JsonUtil;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	
	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository repository;
	
	@Override
	public AccountResponseModel save(AccountDTO acount) {
		AccountEntity toSave = (AccountEntity) JsonUtil.bodyMapper(acount, AccountEntity.class);
		toSave.setAttempts(NumberConstants.ZERO);
		this.repository.save(toSave);
		log.info("Account saved", toSave);
		return new AccountResponseModel(StatusTypes.OK, new ArrayList<>(), acount.getBalance());
	}

	@Override
	public AccountResponseModel finsBuAccountNumber(AccountDTO account) {
		Optional<AccountEntity> accountOptional = this.repository.findById(account.getAccount());
		if (accountOptional.isPresent()) {
			return new AccountResponseModel(StatusTypes.OK, new ArrayList<>(), accountOptional.get().getBalance());
		}
		throw new IllegalArgumentException(ExceptionMessages.ACCOUNT_NOT_FOUNF);
	}

}
