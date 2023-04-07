package org.example.object.chap14.billing.step2;

import java.time.Duration;

import org.example.object.chap14.money.Money;
import org.example.object.chap14.time.DateTimeInterval;

public class FeePerDuration {
	private Money fee;
	private Duration duration;

	public FeePerDuration(Money fee, Duration duration) {
		this.fee = fee;
		this.duration = duration;
	}

	public Money calculate(DateTimeInterval interval) {
		return fee.times(Math.ceil((double)interval.duration().toNanos() / duration.toNanos()));
	}
}
