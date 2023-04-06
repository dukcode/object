package org.example.object.chap10;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPhone {

	private List<Call> calls = new ArrayList<>();

	public Money calculateFee() {
		Money result = Money.ZERO;

		for (Call call : calls) {
			result = calculateCallFee(call);
		}

		return result;
	}

	abstract protected Money calculateCallFee(Call call);
}
