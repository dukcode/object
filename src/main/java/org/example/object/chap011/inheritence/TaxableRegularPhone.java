package org.example.object.chap011.inheritence;

import java.time.Duration;

import org.example.object.chap011.money.Money;

public class TaxableRegularPhone extends RegularPhone {

	private double taxRate;

	public TaxableRegularPhone(Money amount, Duration seconds, double taxRate) {
		super(amount, seconds);
		this.taxRate = taxRate;
	}

	@Override
	protected Money afterCalculated(Money fee) {
		return fee.plus(fee.times(taxRate));
	}
}
