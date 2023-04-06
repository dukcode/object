package org.example.object.chap011.inheritence;

import java.time.Duration;

import org.example.object.chap011.call.Call;
import org.example.object.chap011.money.Money;

public class RegularPhone extends Phone {

	private Money amount;
	private Duration seconds;

	public RegularPhone(Money amount, Duration seconds) {
		this.amount = amount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}

}
