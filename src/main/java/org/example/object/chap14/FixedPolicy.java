package org.example.object.chap14;

import java.time.Duration;

import org.example.object.chap14.call.Call;
import org.example.object.chap14.money.Money;

public class FixedPolicy extends BasicRatePolicy {

	private Money amount;
	private Duration seconds;

	public FixedPolicy(Money amount, Duration seconds) {
		this.amount = amount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}
}
