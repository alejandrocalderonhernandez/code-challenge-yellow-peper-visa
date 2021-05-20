package com.alejandro.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.challenge.model.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity , String>{

}
