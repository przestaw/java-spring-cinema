package data;

public class ResPlace extends Place {
	private final int type;

	public ResPlace(int row, int column, int type) {
		super(row, column);
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
}
