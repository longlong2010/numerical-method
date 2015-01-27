package polynome;

public class Polynome {

	protected double a[];

	public Polynome(double[] a) {
		this.a = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			this.a[i] = a[i];
		}
	}

	public double value(double x) {
		double v = 0;
		for (int i = 0; i < this.a.length; i++) {
			v += this.a[i] * Math.pow(x, i);
		}
		return v;
	}

	public static Polynome add(Polynome p1, Polynome p2) {
		int n = Math.max(p1.a.length, p2.a.length);
		double[] a = new double[n];
		for (int i = 0; i < n; i++) {
			if (i >= p1.a.length) {
				a[i] = p2.a[i];
			} else if (i >= p2.a.length) {
				a[i] = p1.a[i];
			} else {
				a[i] = p1.a[i] + p2.a[i];
			}
		}
		return new Polynome(a);
	}

	public static void main(String[] args) {
		double[] a1 = {1, 2, 3};
		double[] a2 = {1, 2, 3, 4};
		Polynome p1 = new Polynome(a1);
		Polynome p2 = new Polynome(a2);
		Polynome p3 = Polynome.add(p1, p2);
		
		for (int i = 0; i < p3.a.length; i++) {
			System.out.println(p3.a[i]);
		}

		System.out.println(p1.value(2));
	}
}
