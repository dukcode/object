package org.example.object.chap14;

import java.util.ArrayList;
import java.util.List;

import org.example.object.chap14.call.Call;
import org.example.object.chap14.money.Money;

public class DurationDiscountPolicy extends BasicRatePolicy {
	private List<DurationDiscountRule> rules = new ArrayList<>();

	public DurationDiscountPolicy(List<DurationDiscountRule> rules) {
		this.rules = rules;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		Money result = Money.ZERO;
		for (DurationDiscountRule rule : rules) {
			result.plus(rule.calculate(call));
		}

		return result;
	}
}
