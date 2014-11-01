package equation;

import function.Function;
import exception.MisconvergenceException;

public class NewtonMethod {

	protected static int maxStep = 1000;
	protected static double eps = 1e-8;

	public static void setMaxStep(int maxStep) {
		NewtonMethod.maxStep = maxStep;
	}

	public static void setEps(double eps) {
		NewtonMethod.eps = eps;
	}

	public static double solve(Function f, Function f1, double x0) throws MisconvergenceException {
		double delta;
		double x = x0;
		int i = 0;
		do {
			delta = f.value(x) / f1.value(x);
			x -= delta;
			if (++i > NewtonMethod.maxStep) {
				throw new MisconvergenceException();
			}
		} while (Math.abs(delta) > NewtonMethod.eps);
		
		return x;
	}

	public static void main(String[] args) {
		Function f = new Function() {
			@Override
			public double value(double x) {
				return x * x - 2;
			}
		};

		Function f1 = new Function() {
			@Override
			public double value(double x) {
				return 2 * x;
			}
		};
		NewtonMethod.setEps(1e-12);
		try {
			double x = NewtonMethod.solve(f, f1, 1);
			System.out.println(x);
		} catch (MisconvergenceException e) {
			System.out.println(1);		
		}
	}
}
