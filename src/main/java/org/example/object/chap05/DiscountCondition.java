package org.example.object.chap05;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {

	private DiscountConditionType type;

	private int sequence;

	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;

	boolean isSatisfiedBy(Screening screening) {
		if (type == DiscountConditionType.PERIOD) {
			return isSatisfiedByPeriod(screening);
		}

		return isSatisfiedBySequence(screening);
	}

	private boolean isSatisfiedBySequence(Screening screening) {
		return sequence == screening.getSequence();
	}

	private boolean isSatisfiedByPeriod(Screening screening) {
		return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
			startTime.isBefore(screening.getWhenScreened().toLocalTime()) &&
			endTime.isAfter(screening.getWhenScreened().toLocalTime());
	}
}
