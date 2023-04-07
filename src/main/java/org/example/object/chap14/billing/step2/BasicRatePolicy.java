package org.example.object.chap14.billing.step2;

import java.util.ArrayList;
import java.util.List;

import org.example.object.chap14.money.Money;

public class BasicRatePolicy implements RatePolicy {

	private List<FeeRule> feeRules = new ArrayList<>();

	public BasicRatePolicy(List<FeeRule> feeRules) {
		this.feeRules = feeRules;
	}

	@Override
	public Money calculateFee(Phone phone) {
		return phone.getCalls()
			.stream()
			.map(this::calculate)
			.reduce(Money.ZERO, Money::plus);
	}

	private Money calculate(Call call) {
		return feeRules.stream()
			.map(rule -> rule.calculateFee(call))
			.reduce(Money.ZERO, Money::plus);
	}
}
