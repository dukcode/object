package org.example.object.chap11.inheritence;

import java.util.ArrayList;
import java.util.List;

import org.example.object.chap11.call.Call;
import org.example.object.chap11.money.Money;

public abstract class Phone {
	private List<Call> calls = new ArrayList<>();

	public Money calculateFee() {
		Money result = Money.ZERO;
		for (Call call : calls) {
			result = result.plus(calculateCallFee(call));
		}

		return afterCalculated(result);
	}

	abstract protected Money calculateCallFee(Call call);

	protected Money afterCalculated(Money fee) {
		return fee;
	}

	public List<Call> getCalls() {
		return calls;
	}
}
