polynome;

public class Polynome {

	protected double a[];

	public Polynome(double[] a) {
		this.a = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			this.a[i] = a[i];
		}
	}
}
