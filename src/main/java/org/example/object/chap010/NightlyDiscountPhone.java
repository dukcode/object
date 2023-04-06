package org.example.object.chap010;

import java.time.Duration;

public class NightlyDiscountPhone extends Phone {

	private static final int LATE_NIGHT_HOUR = 22;

	private Money nightlyAmount;

	public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
		super(regularAmount, seconds);
		this.nightlyAmount = nightlyAmount;
	}

	public Money calculateFee() {
		Money result = super.calculateFee();

		Money nightFee = Money.ZERO;
		for (Call call : getCalls()) {
			if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
				nightFee = nightFee.plus(
					getAmount().minus(nightlyAmount).times(
						call.getDuration().getSeconds() / getSeconds().getSeconds()
					));
			}
		}

		return result.minus(nightFee);
	}
}
