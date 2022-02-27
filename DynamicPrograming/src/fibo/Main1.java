package fibo;
import java.util.*;
public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Fibonacci fibo = new Fibonacci();
		System.out.println("Enter n");
		int num = scan.nextInt();
		long start = System.nanoTime();
		int result = fibo.fib(num);
		long end = System.nanoTime();
		System.out.println(result);
		System.out.println("time taken " + (end - start));
		
		System.out.println();
		
		start = System.nanoTime();
		result = fibo.fibTopDown(num);
		end = System.nanoTime();
		System.out.println(result);
		System.out.println("time taken for Top-Down " + (end - start));
		
		System.out.println();
		start = System.nanoTime();
		result = fibo.fibBotUp(num);
		end = System.nanoTime();
		System.out.println(result);
		System.out.println("time taken for Bot-Up " + (end - start));
		
	}

}
