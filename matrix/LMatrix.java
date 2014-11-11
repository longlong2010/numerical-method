package matrix;

public class LMatrix extends Matrix {

	public void set(int i, int j, double v) {
		if (i >= j) {
			this.elements[i][j] = v;
		}
	}

	public double get(int i, int j) {
		return i >= j ? this.elements[i][j] : 0;
	}

	@Override
	public int getColumnSize() {
		int row = this.getRowSize();
		return row > 0 ? this.elements[row - 1].length : 0;
	}

	public LMatrix(int m, int n) {
		this.elements = new double[m][];
		for (int i = 0; i < this.elements.length; i++) {
			this.elements[i] = new double[i + 1];
		}
	}
}
