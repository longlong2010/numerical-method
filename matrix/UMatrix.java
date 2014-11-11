package matrix;

public class UMatrix extends Matrix {

	public void set(int i, int j, double v) {
		if (i <= j) {
			this.elements[i][j - i] = v;
		}
	}

	public double get(int i, int j) {
		return i <= j ? this.elements[i][j - i] : 0;
	}

	public UMatrix(int m, int n) {
		this.elements = new double[m][];
		for (int i = 0; i < this.elements.length; i++) {
			this.elements[i] = new double[n - i];
		}
	}
}
