package org.example.object.chap14.billing.step2;

import java.util.List;

import org.example.object.chap14.time.DateTimeInterval;

public interface FeeCondition {
	List<DateTimeInterval> findTimeInterval(Call call);
}
