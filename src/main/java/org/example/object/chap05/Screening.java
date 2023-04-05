package org.example.object.chap05;

import java.time.LocalDateTime;

public class Screening {

	private Movie movie;
	private int sequence;
	private LocalDateTime whenScreened;

	public Reservation reserve(Customer customer, int audienceCount) {
		return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
	}

	public Money calculateFee(int audienceCount) {
		return movie.calculateMovieFee(this).times(audienceCount);
	}

	public int getSequence() {
		return sequence;
	}

	public LocalDateTime getWhenScreened() {
		return whenScreened;
	}
}
