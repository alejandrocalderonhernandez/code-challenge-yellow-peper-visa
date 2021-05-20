package com.alejandro.challenge.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.challenge.business.definition.AccountService;
import com.alejandro.challenge.model.dto.AccountDTO;
import com.alejandro.challenge.model.response.AccountResponseModel;

@RestController
@RequestMapping(path = "v1/account")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping
	public ResponseEntity<AccountResponseModel> get(@RequestBody AccountDTO account) {
		return ResponseEntity.ok(this.service.finsBuAccountNumber(account));
	}
	
	@PostMapping
	public ResponseEntity<AccountResponseModel>save(@RequestBody AccountDTO account) {
		return ResponseEntity.ok(this.service.save(account));
	}
}
