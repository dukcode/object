package org.example.object.chap14;

import org.example.object.chap14.money.Money;

public interface RatePolicy {
	Money calculateFee(Phone phone);
}
