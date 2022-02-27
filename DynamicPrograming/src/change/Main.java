package change;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int coins[] = {1,3,5};
		int n = 7;
		change(n, coins);
	}
	
	public static void change(int n,int coins[]) {
		//index is the amount
		//entry is the minimum number of coins needed
		int c[] = new int[n+1];
		c[0] = 0;
		for(int i = 1;i<=n;i++) {
			c[i] = Integer.MAX_VALUE;
			for(int k = 0 ;k<coins.length;k++) {
				if(coins[k] <= i) {
					if(c[i] > c[i - coins[k]] + 1) {
						c[i] = c[i - coins[k]] + 1;
					}
				}
			}
		}
		
		
		for(int i = 0;i<=n;i++) {
			System.out.print(c[i] + " ");
		}System.out.println();
		System.out.println("Min number of coins " + c[n]);
	}
}
 
