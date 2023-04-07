package org.example.object.chap14.billing.step1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.object.chap14.money.Money;
import org.example.object.chap14.time.DateTimeInterval;

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

	public static class Call {

		private DateTimeInterval interval;

		public Call(LocalDateTime from, LocalDateTime to) {
			interval = DateTimeInterval.of(from, to);
		}

		public Duration getDuration() {
			return interval.duration();
		}

		public LocalDateTime getFrom() {
			return interval.getFrom();
		}

		public LocalDateTime getTo() {
			return interval.getTo();
		}

		public DateTimeInterval getInterval() {
			return interval;
		}

		public List<DateTimeInterval> splitByDay() {
			return interval.splitByDay();
		}
	}
}
