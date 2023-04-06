package org.example.object.chap011.composition;

import org.example.object.chap011.money.Money;

public interface RatePolicy {
	Money calculateFee(Phone phone);
}
