package data;
/*
 * author : Przemysław Stawczyk
 */
public class SeatPlace extends Place {
	private final boolean empty;
	
	public SeatPlace(int row, int column, boolean empty) {
		super(row, column);
		this.empty = empty;
	}

	public SeatPlace(int row, int column) {
		this(row, column, true);
	}

	public boolean isEmpty() {
		return empty;
	}
}
