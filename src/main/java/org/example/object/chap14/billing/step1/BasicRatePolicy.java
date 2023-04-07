package org.example.object.chap14.billing.step1;

import org.example.object.chap14.money.Money;

public abstract class BasicRatePolicy implements RatePolicy {
	@Override
	public Money calculateFee(Phone phone) {
		Money result = Money.ZERO;
		for (DurationDiscountPolicy.Call call : phone.getCalls()) {
			result = result.plus(calculateCallFee(call));
		}
		return result;
	}

	protected abstract Money calculateCallFee(DurationDiscountPolicy.Call call);

}
