package matrixChain;
import java.util.*;
public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Mcm mcm = new Mcm();
		int d[] = new int[] {20,2,15,40,4}; //array of matrix dimension
		int n = d.length - 1;
		
		long start;
		long end;
		
		start = System.nanoTime();
		int result = mcm.bottomUp(n, d);
		end = System.nanoTime();	
		System.out.println("bottom up time taken :" + (end - start));
		System.out.println("result of bottom up MCM : " + result);
	}
	
	
	
}
