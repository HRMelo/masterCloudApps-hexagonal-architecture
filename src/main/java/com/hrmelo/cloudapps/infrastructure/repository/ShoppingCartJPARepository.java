package com.hrmelo.cloudapps.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrmelo.cloudapps.infrastructure.model.ShoppingCartEntity;

public interface ShoppingCartJPARepository extends JpaRepository<ShoppingCartEntity, Long>{

}
