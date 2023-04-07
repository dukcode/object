package org.example.object.chap14.billing.step2;

import org.example.object.chap14.money.Money;

public interface RatePolicy {
	Money calculateFee(Phone phone);
}
