package com.alejandro.challenge.business.impl;

import java.util.ArrayList;
import java.util.Optional;

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

	@Autowired
	private AccountRepository repository;
	
	@Override
	public AccountResponseModel save(AccountDTO acount) {
		AccountEntity toSave = (AccountEntity) JsonUtil.bodyMapper(acount, AccountEntity.class);
		toSave.setAttempts(NumberConstants.ZERO);
		this.repository.save(toSave);
		return new AccountResponseModel(StatusTypes.OK, new ArrayList<>(), acount.getBalance());
	}

	@Override
	public AccountResponseModel finsBuAccountNumber(AccountDTO account) {
		Optional<AccountEntity> accountOptional = this.repository.findById(account.getAccount());
		if (accountOptional.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.ACCOUNT_NOT_FOUNF);
		}
		return new AccountResponseModel(StatusTypes.OK, new ArrayList<>(), accountOptional.get().getBalance());
	}

}
