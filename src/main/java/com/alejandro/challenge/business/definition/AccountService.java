package com.alejandro.challenge.business.definition;

import com.alejandro.challenge.common.service.CommonService;
import com.alejandro.challenge.model.dto.AccountDTO;
import com.alejandro.challenge.model.response.AccountResponseModel;

public interface AccountService extends CommonService<AccountDTO, AccountResponseModel> {

	public AccountResponseModel finsBuAccountNumber(AccountDTO account);
}
