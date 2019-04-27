package data;

public class Place {
	private final int row;
	private final int column;
	private final long reservationId;
	private final double price;
	
	public Place(int row, int column, long reservationId, double price) {
		this.row = row;
		this.column = column;
		this.reservationId = reservationId;
		this.price = price;
	}
	
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public long getReservationId() {
		return reservationId;
	}
	public double getPrice() {
		return price;
	}
}
