package stringMatching.simple;


public class RabinKarpWild {
	public static int q = 13;
	public static int d = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "ABABABCCAC";
		String pattern = "BAxC";
		int result = RKScan(pattern, text);
		System.out.println("index is: " + result);
	}
	
	public static int RKScan(String pattern,String text) {
		int m = pattern.length();
		int n = text.length();
		char[] p = pattern.toCharArray();
		char[] t = text.toCharArray();
		//System.out.println("dM = " + getdM(10, m));
		int dM = getdM(d, m);
		int dMid = getdMiddle(d, m);
		int hp = hash(p, m, d);
		int ht = hash(t,m,d); //hash value for first text window
		
		//j position within text
		for(int j = 0;j <= n-m;j++) {
			//System.out.println("j is " + j);
			System.out.println("HP = " + hp + " HT = " + ht);
			if(hp == ht && equalString(p, t, j, m)) {
				return j;
			}
			
			else if(j < n-m) {
				//rehash for next window j + 1
				ht = reHash(t, j, m, ht, dM,dMid);
			}
		}
		
		
		return -1;
	}
		public static int reHash(char[] str,int j,int m,int ht,int dM,int dMid) {
		
		int mid = m/2;
		//get the MSB value
		int oldest = (str[j] * dM) % q;
		//get the old middle to add back
		int oldMiddle = (str[j + m/2] * dMid) % q;
		//remove the MSB
		//add the old middle previously hash value is 0
		int oldRemoved = (ht - oldest + oldMiddle) % q;
		//get the new middle
		int newMiddle = (str[j + m/2 + 1] * dMid) % q;
		
		//plus q to take care of potential negative
		int newHash = ((oldRemoved * d) - newMiddle + q + str[j+m]) % q; 
		
		
		
		
		return newHash;
	}
	public static int getdM(int d,int m) {
		int dm = 1 ;
		for(int i = 1; i < m ;i++) {
			dm = (dm * d) % q;
		}
		return dm;
	}	
	
	//d is the base use decimal would be 10;
	public static int hash(char[] str,int m,int d) {
		int mid = m/2;
		int hashValue = str[0] % q;
		//zero base index
		for(int i = 1;i < m;i++) {
			
			if(i == mid) {
				//System.out.println("wild char = " + str[i]);
				hashValue = (hashValue * d + 0)  % q;
				
			}
			else {
				hashValue = ( (hashValue * d) + str[i]) % q;
			}
			
		}
		
		return hashValue;
	}
	
	public static int getdMiddle(int d,int m) {
		int dmid = 1;
		for(int i = 1;i<=(m-1)/2;i++) {
			dmid = (dmid * d) % q;
		}
		return dmid;
	}
	//int j is the index of current character in text
	public static boolean equalString(char[] p,char[] tw,int j,int m) {
		int i,k;
		k = 0; //position within pattern
		i = 0; //keep track of how many iteration checks need do max should be m compair
		
		while(i < m) {
			//System.out.println("k is " + k);
			//System.out.println("j is " + j);

			System.out.println(p[k] + " == " + tw[j]);
			
			if(i == m/2) {
				System.out.println("wild");
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
		

	

	

}
