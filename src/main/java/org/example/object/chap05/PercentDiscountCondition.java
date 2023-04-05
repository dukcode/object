package org.example.object.chap05;

public class PercentDiscountCondition implements DiscountCondition {

	private int sequence;

	public PercentDiscountCondition(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public boolean isSatisfiedBy(Screening screening) {
		return sequence == screening.getSequence();
	}
}
