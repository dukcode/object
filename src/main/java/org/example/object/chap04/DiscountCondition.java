package org.example.object.chap04;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
	private DiscountConditionType type;

	private int sequence;

	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;

	public DiscountConditionType getType() {
		return type;
	}

	public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
		if (type != DiscountConditionType.PERIOD) {
			throw new IllegalArgumentException();
		}

		return this.dayOfWeek.equals(dayOfWeek) &&
			this.startTime.isBefore(time) &&
			this.endTime.isAfter(time);
	}

	public boolean isDiscountable(int sequence) {
		if (type != DiscountConditionType.SEQUENCE) {
			throw new IllegalArgumentException();
		}

		return this.sequence == sequence;
	}
}
