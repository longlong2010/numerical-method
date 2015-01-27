package qr;

import matrix.Matrix;
import matrix.UMatrix;
import matrix.vector.Vector;

public class QR {
	
	private Matrix q;
	private UMatrix r;
	
	public static QR QRDecomposition(Matrix m) throws QRException {
		return HouseholderQRDecomposition(m);
	}
	
	public static QR HouseholderQRDecomposition(Matrix m) throws QRException {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		Matrix u = new Matrix(row, col);
		QR qr = new QR(row, row, col);

		int size = Math.min(row, col);

		for (int i = 0; i < size; i++) {
			qr.q.set(i, i, 1);
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				u.set(i, j, m.get(i, j));	
			}
		}

		for (int i = 0; i < col; i++) {
			int vsize = row - i;
			Vector v = new Vector(vsize);
			for (int j = i; j < row; j++) {
				v.set(j - i, u.get(j, i));		
			}
			double x1 = v.get(0);
			
			v.set(0, x1 + Math.signum(x1) * v.length());
			double l = v.length();
			for (int j = 0; j < vsize; j++) {
				v.set(j, v.get(j) / l);
			}

			for (int j = i; j < col; j++) {
				double s = 0;
				for (int k = i; k < row; k++) {
					s += v.get(k - i) * u.get(k, j);
				}
				
				for (int k = i; k < row; k++) {
					u.set(k, j, u.get(k, j) -  2 * v.get(k - i) * s);
				}
			}

			for (int j = 0; j < col; j++) {
				double s = 0;
				for (int k = i; k < row; k++) {
					s += v.get(k - i) * qr.q.get(j, k);
				}

				for (int k = i; k < row; k++) {
					qr.q.set(j, k, qr.q.get(j, k) -  2 * v.get(k - i) * s);
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = i; j < col; j++) {
				qr.r.set(i, j, u.get(i, j));
			}
		}
		return qr;
	}

	public static QR GivensQRDecomposition(Matrix m) throws QRException {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		Matrix u = new Matrix(row, col);
		QR qr = new QR(row, row, col);
		
		int size = Math.min(row, col);
		
		for (int i = 0; i < size; i++) {
			qr.q.set(i, i, 1);
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				u.set(i, j, m.get(i, j));	
			}
		}

		for (int i = 1; i < size; i++) {
			for (int j = 0; j < i; j++) {
				double y = u.get(i, j);
				if (y == 0) {
					continue;
				}
				double x = u.get(j, j);

				double l = Math.sqrt(x * x + y * y);

				double c = x / l;
				double s = y / l;
				
				for (int k = 0; k < col; k++) {
					x = u.get(j, k);
					y = u.get(i, k);
					u.set(j, k, c * x + s * y);
					u.set(i, k, -s * x + c * y);

					x = qr.q.get(k, j);
					y = qr.q.get(k, i);
					qr.q.set(k, j, c * x + s * y);
					qr.q.set(k, i, -s * x + c * y);
				}
			}
		}
		
		for (int i = 0; i < row; i++) {
			for (int j = i; j < col; j++) {
				qr.r.set(i, j, u.get(i, j));	
			}
		}
		return qr;
	}

	public static QR SchmidtQRDecomposition(Matrix m) throws QRException {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		QR qr = new QR(row, col, col);
		for (int i = 0; i < col; i++) {
			Vector v = new Vector(row);
			for (int k = 0; k < row; k++) {
				v.set(k, m.get(k, i));
			}

			for (int j = 0; j < i; j++) {
				double inner = 0;
				for (int k = 0; k < row; k++) {
					inner += qr.q.get(k, j) * v.get(k);
				}
				qr.r.set(j, i, inner);
				for (int k = 0; k < row; k++) {
					v.set(k, v.get(k) - inner * qr.q.get(k, j));
				}
			}

			double length = v.length();
			qr.r.set(i, i, length);
			for (int k = 0; k < row; k++) {
				qr.q.set(k, i, v.get(k) / length);
			}
		}
		return qr;
	}

	private QR(int m, int n, int l) {
		this.q = new Matrix(m, n);
		this.r = new UMatrix(n, l);
	}
	
	public Matrix getQ() {
		return this.q;
	}

	public UMatrix getR() {
		return this.r;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("No Input File Of Matrix Given");
			return;
		}
		Matrix m = Matrix.loadMatrix(args[0]);	
		m.print();
		try {
			QR qr = QR.SchmidtQRDecomposition(m);
			qr.getQ().print();
			qr.getR().print();
			
			qr = QR.GivensQRDecomposition(m);
			qr.getQ().print();
			qr.getR().print();
			
			qr = QR.HouseholderQRDecomposition(m);
			qr.getQ().print();
			qr.getR().print();
		} catch (QRException ex) {

		}
	}
}
