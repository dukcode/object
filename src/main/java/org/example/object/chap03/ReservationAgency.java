package org.example.object.chap03;

public class ReservationAgency {
	public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
		Movie movie = screening.getMovie();

		boolean discountable = false;
		for (DiscountCondition condition : movie.getDiscountConditions()) {
			if (condition.getType() == DiscountConditionType.PERIOD) {
				discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
					condition.getStartTime().isBefore(screening.getWhenScreened().toLocalTime()) &&
					condition.getEndTime().isAfter(screening.getWhenScreened().toLocalTime());
			} else {
				discountable = condition.getSequence() == condition.getSequence();
			}

			if (discountable) {
				break;
			}
		}

		Money fee;
		if (discountable) {
			Money discountAmount = Money.ZERO;
			switch (movie.getMovieType()) {
				case AMOUNT_DISCOUNT:
					discountAmount = movie.getDiscountAmount();
					break;
				case PERCENT_DISCOUNT:
					discountAmount = movie.getFee().times(movie.getDiscountPercent());
					break;
				case NONE_DISCOUNT:
					break;
			}

			fee = movie.getFee().minus(discountAmount);
		} else {
			fee = movie.getFee();
		}

		return new Reservation(customer, screening, fee, audienceCount);

	}
}
