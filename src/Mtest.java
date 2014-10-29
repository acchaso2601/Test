

import java.util.Arrays;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;


public class Mtest {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		double[][] array =  {{0,1,0},{1,0,2},{0,2,0}};
		Matrix m = new Matrix(array);
		
		EigenvalueDecomposition ev = new EigenvalueDecomposition(m);
		System.out.println(Arrays.toString(ev.getRealEigenvalues()));

	}

}
