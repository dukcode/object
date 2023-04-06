package org.example.object.chap11.inheritence;

import java.time.Duration;

import org.example.object.chap11.money.Money;

public class RateDiscountableNightlyDiscountPhone extends NightlyDiscountPhone {

	private Money discountAmount;

	public RateDiscountableNightlyDiscountPhone(Money nightAmount, Money regularAmount, Duration seconds,
		Money discountAmount) {
		super(nightAmount, regularAmount, seconds);
		this.discountAmount = discountAmount;
	}

	@Override
	protected Money afterCalculated(Money fee) {
		return fee.minus(discountAmount);
	}
}
