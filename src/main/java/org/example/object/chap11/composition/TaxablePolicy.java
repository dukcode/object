package org.example.object.chap11.composition;

import org.example.object.chap11.money.Money;

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
