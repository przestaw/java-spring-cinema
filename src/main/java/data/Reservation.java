package data;

public class Reservation {
	private final long id;
	private final long screeningId;
	private final String name;
	private final String surname;
	private final int noOfTickets;
	private final double totalPrice;
	
	public Reservation(long id, long screeningId, String name, String surname, int noOfTickets, double totalPrice) {
		this.id = id;
		this.screeningId = screeningId;
		this.name = name;
		this.surname = surname;
		this.noOfTickets = noOfTickets;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return id;
	}

	public long getScreeningId() {
		return screeningId;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
}
