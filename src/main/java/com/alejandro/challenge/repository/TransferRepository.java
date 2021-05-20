package com.alejandro.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.challenge.model.entity.TransferEntity;

public interface TransferRepository extends JpaRepository<TransferEntity, Long>{

}
