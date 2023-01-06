package org.example.object.chap02;

public class PercentDefaultDiscountPolicy extends DefaultDiscountPolicy {

	private double percent;

	public PercentDefaultDiscountPolicy(double percent, DiscountCondition... discountConditions) {
		super(discountConditions);
		this.percent = percent;
	}

	@Override
	protected Money getDiscountAmount(Screening screening) {
		return screening.getMovieFee().times(percent);
	}
}
