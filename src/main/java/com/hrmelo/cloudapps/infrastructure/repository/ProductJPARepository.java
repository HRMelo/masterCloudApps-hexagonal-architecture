package com.hrmelo.cloudapps.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrmelo.cloudapps.infrastructure.model.ProductEntity;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long>{

}
