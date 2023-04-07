package org.example.object.chap14.billing.step2;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.example.object.chap14.time.DateTimeInterval;

public class TimeOfDayFeeCondition implements FeeCondition {

	private LocalTime from;
	private LocalTime to;

	public TimeOfDayFeeCondition(LocalTime from, LocalTime to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public List<DateTimeInterval> findTimeInterval(Call call) {
		return call.splitByDay().stream()
			.filter(this::isOverlapping)
			.map(each -> DateTimeInterval.of(
				LocalDateTime.of(each.getFrom().toLocalDate(), from(each)),
				LocalDateTime.of(each.getTo().toLocalDate(), to(each))))
			.collect(Collectors.toList());
	}

	private boolean isOverlapping(DateTimeInterval each) {
		return from(each).isBefore(to(each));
	}

	private LocalTime from(DateTimeInterval interval) {
		return interval.getFrom().toLocalTime().isBefore(from) ?
			from : interval.getFrom().toLocalTime();
	}

	private LocalTime to(DateTimeInterval interval) {
		return interval.getTo().toLocalTime().isAfter(to) ?
			to : interval.getTo().toLocalTime();
	}
}
