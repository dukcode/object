package org.example.object.chap14.billing.step1;

import java.time.Duration;

import org.example.object.chap14.money.Money;

public class NightlyDiscountPolicy extends BasicRatePolicy {

	private static final int LATE_NIGHT_HOUR = 22;

	private Money nightAmount;
	private Money regularAmount;
	private Duration seconds;

	public NightlyDiscountPolicy(Money nightAmount, Money regularAmount, Duration seconds) {
		this.nightAmount = nightAmount;
		this.regularAmount = regularAmount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(DurationDiscountPolicy.Call call) {
		if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
			return nightAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
		}

		return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}
}
