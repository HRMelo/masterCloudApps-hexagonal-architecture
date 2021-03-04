package com.hrmelo.cloudapps.infrastructure;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.hrmelo.cloudapps.domain.ShoppingCartItem;
import com.hrmelo.cloudapps.domain.repository.CheckAvailability;

@Component
public class CheckAvailabilityAdapter implements CheckAvailability {

	@Override
	public boolean checkProductAvailability(List<ShoppingCartItem> items) {
		Random random = new Random();
		return random.nextBoolean();
	}

}
