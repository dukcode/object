package org.example.object.chap14.billing.step1;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.example.object.chap14.money.Money;
import org.example.object.chap14.time.DateTimeInterval;

public class TimeOfDayDiscountPolicy extends BasicRatePolicy {

	private List<LocalTime> starts = new ArrayList<>();
	private List<LocalTime> ends = new ArrayList<>();
	private List<Duration> durations = new ArrayList<>();
	private List<Money> amounts = new ArrayList<>();

	@Override
	protected Money calculateCallFee(DurationDiscountPolicy.Call call) {
		Money result = Money.ZERO;
		for (DateTimeInterval interval : call.splitByDay()) {
			for (int idx = 0; idx < starts.size(); idx++) {
				result.plus(amounts.get(idx).times(
					Duration.between(from(interval, starts.get(idx)), to(interval, ends.get(idx))).getSeconds() /
						durations.get(idx).getSeconds()));
			}
		}

		return result;
	}

	private LocalTime to(DateTimeInterval interval, LocalTime to) {
		return interval.getTo().toLocalTime().isAfter(to) ?
			to :
			interval.getTo().toLocalTime();
	}

	private LocalTime from(DateTimeInterval interval, LocalTime from) {
		return interval.getFrom().toLocalTime().isBefore(from) ?
			from :
			interval.getFrom().toLocalTime();
	}
}
