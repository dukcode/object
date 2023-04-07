package org.example.object.chap14.call;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.example.object.chap14.DateTimeInterval;

public class Call {

	private DateTimeInterval interval;

	public Call(LocalDateTime from, LocalDateTime to) {
		interval = DateTimeInterval.of(from, to);
	}

	public Duration getDuration() {
		return interval.duration();
	}

	public LocalDateTime getFrom() {
		return interval.getFrom();
	}

	public LocalDateTime getTo() {
		return interval.getTo();
	}

	public DateTimeInterval getInterval() {
		return interval;
	}

	public List<DateTimeInterval> splitByDay() {
		return interval.splitByDay();
	}
}
