package org.example.object.chap011.composition;

import org.example.object.chap011.money.Money;

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
