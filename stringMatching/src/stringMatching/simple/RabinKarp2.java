package stringMatching.simple;

public class RabinKarp2 {
	public static int q = 13;
	public static int d = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pattern = "ABS*ED";
		String text = "SVSVDSVABSFEDFB";
		
		int result = RKScan(pattern, text);
		System.out.println("index is: " + result);
	}
	
	public static int RKScan(String pattern,String text) {
		int m = pattern.length();
		int n = text.length();
		char[] p = pattern.toCharArray();
		char[] t = text.toCharArray();
		int mid = m/2;

		//System.out.println("dM = " + getdM(10, m));
		int dM = getdM(10, m);
		int hp = hash(p, m, d);
		int ht = hash(t,m,d); //hash value for first text window
		if(p[mid] == '*') {
			System.out.println("wildcard");
			int x = (t[mid] - p[mid]);
			for(int i = 0;i<(m-1)/2;i++) {
				x = (x * d) % q ;
			}
			System.out.println("x = " + x);
			hp = (hp  +  x) % q; 
		}
		//j position within text
		for(int j = 0;j <= n-m;j++) {
			System.out.println("j is " + j);
			System.out.println("HP = " + hp + " HT = " + ht);
	
			if(hp == ht && equalString(p, t, j, m)) {
				return j;
			}
			
			else if(j < n-m) {
				//rehash for next window j + 1

				ht = reHash(t, j, m, ht, dM);
				if(p[mid] == '*') {
					
					System.out.println("rehash wildcard");
					hp = hash(p, m, d);
					int x = (t[j +mid + 1] - p[mid]);
					for(int i = 0;i<(m-1)/2;i++) {
						x = (x * d) % q ;
					}
					System.out.println("x = " + x);
					hp = (hp  +  x) % q; 
				}
			}
		}
		
		
		return -1;
	}
	
	public static int reHash(char[] str,int j,int m,int ht,int dM) {
		
		//get the MSB value
		int oldest = (str[j] * dM) % q;
		//plus q to take care of potential negative
		int oldest_removed = ((ht + q) - oldest) % q;
		int newHash = ((oldest_removed * d) + str[j + m]) % q;
		return newHash;
	}
		
	//d is the base use decimal would be 10;
	public static int hash(char[] str,int m,int d) {
		int mid = m/2;
		int hashValue = str[0] % q;
		//zero base index
		for(int i = 1;i < m;i++) {
				hashValue = ( (hashValue * d) + str[i]) % q;
			
			
		}
		
		return hashValue;
	}
	//int j is the index of current character in text
	public static boolean equalString(char[] p,char[] tw,int j,int m) {
		int i,k;
		k = 0; //position within pattern
		i = 0; //keep track of how many iteration checks need do max should be m compair
		int mid = m/2;
		while(i < m) {
			//System.out.println("k is " + k);
			//System.out.println("j is " + j);
			
			
			
			System.out.println(p[k] + " == " + tw[j]);
			
			if(i == mid && p[i] == '*') {
				k++;
				j++;
				i++;
			}
			
			else if(p[k] != tw[j]) {
				
				return false;
			}
			else if(p[k] == tw[j]) {
				k++;
				j++;
				i++;
				
			}
			
		}
		
		return true;
	}
	
	public static int getdM(int d,int m) {
		int dm = 1 ;
		for(int i = 1; i <= m - 1;i++) {
			dm = (dm * d) % q;
		}
		return dm;
	}
	
	

	

}
