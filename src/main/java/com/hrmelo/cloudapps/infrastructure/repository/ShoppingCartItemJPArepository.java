package com.hrmelo.cloudapps.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrmelo.cloudapps.infrastructure.model.ShoppingCartItemEntity;

public interface ShoppingCartItemJPArepository extends JpaRepository<ShoppingCartItemEntity, Long>{

}
