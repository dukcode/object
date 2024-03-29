package org.example.object.chap10;

import java.time.Duration;

public class Phone extends AbstractPhone {

	private Money amount;
	private Duration seconds;

	public Phone(Money amount, Duration seconds) {
		this.amount = amount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}
}
