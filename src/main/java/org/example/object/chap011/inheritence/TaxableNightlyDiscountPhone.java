package org.example.object.chap011.inheritence;

import java.time.Duration;

import org.example.object.chap011.money.Money;

public class TaxableNightlyDiscountPhone extends NightlyDiscountPhone {

	private double taxRate;

	public TaxableNightlyDiscountPhone(Money nightAmount, Money regularAmount, Duration seconds, double taxRate) {
		super(nightAmount, regularAmount, seconds);
		this.taxRate = taxRate;
	}

	@Override
	protected Money afterCalculated(Money fee) {
		return fee.plus(fee.times(taxRate));
	}
}
