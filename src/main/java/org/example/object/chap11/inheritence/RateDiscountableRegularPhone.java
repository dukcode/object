package org.example.object.chap11.inheritence;

import java.time.Duration;

import org.example.object.chap11.money.Money;

public class RateDiscountableRegularPhone extends RegularPhone {

	private Money discountAmount;

	public RateDiscountableRegularPhone(Money amount, Duration seconds, Money discountAmount) {
		super(amount, seconds);
		this.discountAmount = discountAmount;
	}

	@Override
	protected Money afterCalculated(Money fee) {
		return fee.minus(discountAmount);
	}
}
