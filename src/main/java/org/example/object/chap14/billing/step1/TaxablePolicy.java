package org.example.object.chap14.billing.step1;

import org.example.object.chap14.money.Money;

public class TaxablePolicy extends AdditionalPolicy {

	private double taxRate;

	public TaxablePolicy(double taxRate, RatePolicy next) {
		super(next);
		this.taxRate = taxRate;
	}

	@Override
	protected Money afterCalculated(Money fee) {
		return fee.times(taxRate);
	}
}
