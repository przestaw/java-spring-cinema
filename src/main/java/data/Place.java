package data;
/*
 * author : Przemysław Stawczyk
 */
public class Place {

	protected final int row;
	protected final int column;

	public Place(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}