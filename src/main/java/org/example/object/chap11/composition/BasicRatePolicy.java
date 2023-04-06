package org.example.object.chap11.composition;

import org.example.object.chap11.call.Call;
import org.example.object.chap11.money.Money;

public abstract class BasicRatePolicy implements RatePolicy {
	@Override
	public Money calculateFee(Phone phone) {
		Money result = Money.ZERO;
		for (Call call : phone.getCalls()) {
			result = result.plus(calculateCallFee(call));
		}
		return result;
	}

	protected abstract Money calculateCallFee(Call call);

}
