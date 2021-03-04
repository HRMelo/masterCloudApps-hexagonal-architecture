package com.hrmelo.cloudapps.domain.repository;

import java.util.List;

import com.hrmelo.cloudapps.domain.ShoppingCartItem;

public interface CheckAvailability {

	boolean checkProductAvailability(List<ShoppingCartItem> items);
}
