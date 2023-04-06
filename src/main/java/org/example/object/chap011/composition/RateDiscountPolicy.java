package org.example.object.chap011.composition;

import org.example.object.chap011.money.Money;

public class RateDiscountPolicy extends AdditionalPolicy {

	private Money amountDiscount;

	public RateDiscountPolicy(RatePolicy next, Money amountDiscount) {
		super(next);
		this.amountDiscount = amountDiscount;
	}

	@Override
	protected Money afterCalculated(Money fee) {
		return fee.minus(amountDiscount);
	}
}
