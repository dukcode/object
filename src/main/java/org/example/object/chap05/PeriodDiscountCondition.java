package org.example.object.chap05;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodDiscountCondition implements DiscountCondition {
	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;

	public PeriodDiscountCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public boolean isSatisfiedBy(Screening screening) {
		return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
			startTime.isBefore(screening.getWhenScreened().toLocalTime()) &&
			endTime.isAfter(screening.getWhenScreened().toLocalTime());
	}
}
