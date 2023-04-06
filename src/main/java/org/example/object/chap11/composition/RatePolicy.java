package org.example.object.chap11.composition;

import org.example.object.chap11.money.Money;

public interface RatePolicy {
	Money calculateFee(Phone phone);
}
