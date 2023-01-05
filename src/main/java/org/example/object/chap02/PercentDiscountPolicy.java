package org.example.object.chap02;

public class PercentDiscountPolicy extends DiscountPolicy {

	private double percent;

	public PercentDiscountPolicy(double percent, DiscountCondition... discountConditions) {
		super(discountConditions);
		this.percent = percent;
	}

	@Override
	protected Money getDiscountAmount(Screening screening) {
		return screening.getMovieFee().times(percent);
	}
}
