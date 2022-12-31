package org.example.object.chap01;

public class Bag {
	private Long amount;
	private Invitation invitation;

	public Bag(Long amount) {
		this.amount = amount;
	}

	public Bag(Long amount, Invitation invitation) {
		this.amount = amount;
		this.invitation = invitation;
	}

	private Ticket ticket;

	public Long hold(Ticket ticket) {
		if (hasInvitation()) {
			setTicket(ticket);
			return 0L;
		} else {
			minusAmount(ticket.getFee());
			setTicket(ticket);
			return ticket.getFee();
		}
	}

	public boolean hasInvitation() {
		return invitation != null;
	}

	private void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	private void minusAmount(Long amount) {
		this.amount -= amount;
	}
}
