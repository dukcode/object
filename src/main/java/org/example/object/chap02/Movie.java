package org.example.object.chap02;

import java.time.Duration;

public class Movie {

	private String title;
	private Duration runningTime;
	private Money fee;
	private DiscountPolicy DiscountPolicy;

	public Movie(String title, Duration runningTime, Money fee, DiscountPolicy DiscountPolicy) {
		this.title = title;
		this.runningTime = runningTime;
		this.fee = fee;
		this.DiscountPolicy = DiscountPolicy;
	}

	public Money getFee() {
		return fee;
	}

	public Money calculateMovieFee(Screening screening) {
		return fee.minus(DiscountPolicy.calculateDiscountAmount(screening));
	}
}
