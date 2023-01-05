package org.example.object.chap02;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class PeriodCondition implements DiscountCondition {

	private DayOfWeek dayOfWeek;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public PeriodCondition(DayOfWeek dayOfWeek, LocalDateTime startTime, LocalDateTime endTime) {
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public boolean isSatisfiedBy(Screening screening) {
		return screening.getStartTime().getDayOfWeek().equals(dayOfWeek)
			&& screening.getStartTime().compareTo(startTime) <= 0
			&& screening.getStartTime().compareTo(endTime) >= 0;
	}
}
