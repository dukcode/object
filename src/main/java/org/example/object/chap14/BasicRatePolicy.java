package org.example.object.chap14;

import org.example.object.chap14.call.Call;
import org.example.object.chap14.money.Money;

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