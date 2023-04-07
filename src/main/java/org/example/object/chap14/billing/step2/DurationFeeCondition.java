package org.example.object.chap14.billing.step2;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.example.object.chap14.time.DateTimeInterval;

public class DurationFeeCondition implements FeeCondition {

	private Duration from;
	private Duration to;

	@Override
	public List<DateTimeInterval> findTimeInterval(Call call) {
		if (call.getDuration().compareTo(from) < 0) {
			return Collections.emptyList();
		}

		return List.of(DateTimeInterval.of(
			call.getInterval().getFrom().plus(from),
			call.getDuration().compareTo(to) > 0 ?
				call.getInterval().getFrom().plus(to) :
				call.getInterval().getTo()
		));
	}
}
