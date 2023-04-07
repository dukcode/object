package org.example.object.chap14.billing.step1;

import java.time.Duration;

import org.example.object.chap14.money.Money;

public class FixedFeePolicy extends BasicRatePolicy {

	private Money amount;
	private Duration seconds;

	public FixedFeePolicy(Money amount, Duration seconds) {
		this.amount = amount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}
}
