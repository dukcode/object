package org.example.object.chap011.composition;

import java.util.ArrayList;
import java.util.List;

import org.example.object.chap011.call.Call;
import org.example.object.chap011.money.Money;

public class Phone {
	private RatePolicy ratePolicy;
	private List<Call> calls = new ArrayList<>();

	public Phone(RatePolicy ratePolicy) {
		this.ratePolicy = ratePolicy;
	}

	public List<Call> getCalls() {
		return calls;
	}

	public Money calculateFee() {
		return ratePolicy.calculateFee(this);
	}
}
