package org.example.object.chap14.billing.step2;

import java.util.List;

import org.example.object.chap14.time.DateTimeInterval;

public class FixedFeeCondition implements FeeCondition {
	@Override
	public List<DateTimeInterval> findTimeInterval(Call call) {
		return List.of(call.getInterval());
	}
}
