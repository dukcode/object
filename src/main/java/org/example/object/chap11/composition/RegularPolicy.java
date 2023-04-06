package org.example.object.chap11.composition;

import java.time.Duration;

import org.example.object.chap11.call.Call;
import org.example.object.chap11.money.Money;

public class RegularPolicy extends BasicRatePolicy {

	private Money amount;
	private Duration seconds;

	public RegularPolicy(Money amount, Duration seconds) {
		this.amount = amount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}
}
