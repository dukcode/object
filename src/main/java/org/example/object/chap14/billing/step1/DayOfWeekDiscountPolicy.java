package org.example.object.chap14.billing.step1;

import java.util.ArrayList;
import java.util.List;

import org.example.object.chap14.money.Money;
import org.example.object.chap14.time.DateTimeInterval;

public class DayOfWeekDiscountPolicy extends BasicRatePolicy {
	private List<DayOfWeekDiscountRule> rules = new ArrayList<>();

	public DayOfWeekDiscountPolicy(List<DayOfWeekDiscountRule> rules) {
		this.rules = rules;
	}

	@Override
	protected Money calculateCallFee(DurationDiscountPolicy.Call call) {
		Money result = Money.ZERO;
		for (DateTimeInterval interval : call.splitByDay()) {
			for (DayOfWeekDiscountRule rule : rules) {
				result = result.plus(rule.calculate(interval));
			}
		}

		return result;
	}
}
