package org.example.object.chap14.billing.step2;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.example.object.chap14.time.DateTimeInterval;

public class DayOfWeekFeeCondition implements FeeCondition {

	private List<DayOfWeek> dayOfWeeks = new ArrayList<>();

	public DayOfWeekFeeCondition(DayOfWeek[] dayOfWeeks) {
		this.dayOfWeeks = Arrays.asList(dayOfWeeks);
	}

	@Override
	public List<DateTimeInterval> findTimeInterval(Call call) {
		return call.splitByDay().stream()
			.filter(each -> dayOfWeeks.contains(each.getFrom().getDayOfWeek()))
			.collect(Collectors.toList());
	}
}
