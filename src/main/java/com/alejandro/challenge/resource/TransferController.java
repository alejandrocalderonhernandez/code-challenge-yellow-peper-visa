package com.alejandro.challenge.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.challenge.business.definition.TransferService;
import com.alejandro.challenge.model.dto.TransferDTO;
import com.alejandro.challenge.model.response.TransferResponseModel;

@RestController
@RequestMapping(path = "v1/transfer")
public class TransferController {

	@Autowired
	private TransferService service;
	
	@PostMapping
	public ResponseEntity<TransferResponseModel> save(@RequestBody TransferDTO transfer) {
		return ResponseEntity.ok(this.service.save(transfer));
	}
}
