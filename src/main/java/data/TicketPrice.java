package data;
/*
 * author : Przemysław Stawczyk
 */
public class TicketPrice {
	private final int id;
	private final double price;
	private final String type;
	
	public TicketPrice(int id, double price, String type) {
		this.id = id;
		this.price = price;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}


	public String getType() {
		return type;
	}
	
	
}
