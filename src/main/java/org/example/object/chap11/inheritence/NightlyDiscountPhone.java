package org.example.object.chap11.inheritence;

import java.time.Duration;

import org.example.object.chap11.call.Call;
import org.example.object.chap11.money.Money;

public class NightlyDiscountPhone extends Phone {

	private static final int LATE_NIGHT_HOUR = 22;

	private Money nightAmount;
	private Money regularAmount;
	private Duration seconds;

	public NightlyDiscountPhone(Money nightAmount, Money regularAmount, Duration seconds) {
		this.nightAmount = nightAmount;
		this.regularAmount = regularAmount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
			return nightAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
		}

		return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}

}
