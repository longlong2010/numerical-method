package matrix;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {

	protected double[][] elements;

	public void set(int i, int j, double v) {
		this.elements[i][j] = v;
	}

	public double get(int i, int j) {
		return this.elements[i][j];
	}

	public int getColumnSize() {
		return this.elements.length > 0 ? this.elements[0].length : 0;
	}

	public int getRowSize() {
		return this.elements.length;
	}

	public void print() {
		int row = this.getRowSize();
		int col = this.getColumnSize();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.printf("%g ", this.get(i, j));
			}
			System.out.println();
		}
		System.out.println();
	}

	public static Matrix loadMatrix(String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
			String line;
			try {
				ArrayList<ArrayList<Double>> list = new ArrayList<ArrayList<Double>>();
				int col = 0;
				while ((line = reader.readLine()) != null) {
					Scanner in = new Scanner(line);
					ArrayList<Double> vector = new ArrayList<Double>(); 
					while (in.hasNext()) {
						vector.add(in.nextDouble());
					}
					int count = vector.size();
					if (count > col) {
						col = count;
					}
					list.add(vector);
				}
				int row = list.size();
				Matrix m = new Matrix(row, col);
				for (int i = 0; i < row; i++) {
					ArrayList<Double> vector = list.get(i);
					for (int j = 0; j < vector.size(); j++) {
						m.set(i, j, vector.get(j));
					}
				}
				reader.close();
				return m;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Matrix();
	}
	
	public Matrix() {
		this.elements = new double[0][];	
	}

	public Matrix(int m, int n) {
		this.elements = new double[m][];
		for (int i = 0; i < this.elements.length; i++) {
			this.elements[i] = new double[n];
		}
	}

	public static void main(String[] args) {
		Matrix.loadMatrix(args[0]).print();
	}
}
