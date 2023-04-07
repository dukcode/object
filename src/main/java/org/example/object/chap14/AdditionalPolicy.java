package org.example.object.chap14;

import org.example.object.chap14.money.Money;

public abstract class AdditionalPolicy implements RatePolicy {

	private RatePolicy next;

	public AdditionalPolicy(RatePolicy next) {
		this.next = next;
	}

	@Override
	public Money calculateFee(Phone phone) {
		Money fee = next.calculateFee(phone);
		return afterCalculated(fee);
	}

	protected abstract Money afterCalculated(Money fee);
}
