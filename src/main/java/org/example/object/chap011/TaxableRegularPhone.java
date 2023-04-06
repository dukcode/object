package org.example.object.chap011;

import java.time.Duration;

public class TaxableRegularPhone extends RegularPhone {

	private double taxRate;

	public TaxableRegularPhone(Money amount, Duration seconds, double taxRate) {
		super(amount, seconds);
		this.taxRate = taxRate;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		Money fee = super.calculateCallFee(call);
		return fee.plus(fee.times(taxRate));
	}
}
