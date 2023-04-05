package org.example.object.chap05;

import java.time.Duration;

public class PercentDiscountMovie extends Movie {

	private double discountPercent;

	public PercentDiscountMovie(String title, Duration runningTime, Money fee, double discountPercent,
		DiscountCondition... discountConditions) {
		super(title, runningTime, fee, discountConditions);
		this.discountPercent = discountPercent;
	}

	@Override
	protected Money calculateDiscountAmount() {
		return getFee().times(discountPercent);
	}
}
