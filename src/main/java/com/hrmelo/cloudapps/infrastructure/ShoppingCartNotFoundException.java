package com.hrmelo.cloudapps.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Shopping Cart not found")
public class ShoppingCartNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8619719572321480450L;

}
