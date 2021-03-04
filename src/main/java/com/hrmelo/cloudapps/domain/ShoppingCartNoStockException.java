package com.hrmelo.cloudapps.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ShoppingCartNoStockException extends RuntimeException {

	private static final long serialVersionUID = -5174084908595153324L;

	public ShoppingCartNoStockException(String msg) {
		super(msg);
	}
}
