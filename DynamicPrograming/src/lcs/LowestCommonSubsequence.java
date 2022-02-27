package lcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LowestCommonSubsequence {
	
	
	
	public int recursive(String str1,String str2,int i,int j) {
		if(i < 0 || j < 0) {
			return 0;
		}
	
		else if(str1.charAt(i) == str2.charAt(j)) {
			return 1 + recursive(str1,str2,i-1,j-1);
		}
		else {
			return Math.max(recursive(str1,str2,i-1,j),recursive(str1,str2,i,j-1));
		}	
	}
	
	public int topDown(String str1,String str2,int i,int j) {
		//create 2d table to for memorization
		int[][] matrix = new int[i][j];
		for(int x = 0;x<i;x++) {
			for(int y = 0;y<j;y++) {
				//set unvisited
				matrix[x][y] = -1;
			}
		}

		int result= recursive2(str1, str2, i - 1, j - 1,matrix);
		
		return result;
	}
	
	public int recursive2(String str1,String str2,int i,int j,int matrix[][]) {
		if(i < 0 || j < 0) {
			return 0;
		}
		if(matrix[i][j] != -1) {
			return matrix[i][j];
		}
		else if(str1.charAt(i) == str2.charAt(j)) {
			int result = 1 + recursive2(str1, str2, i - 1, j - 1, matrix);
			matrix[i][j] = result;
			return result;
		}
		else {
			int result = Math.max(recursive2(str1, str2, i-1, j, matrix),recursive2(str1, str2, i, j-1, matrix));
			matrix[i][j] = result;
			return result;
		}

	}
	
	public int bottomUp(String str1,String str2,int i,int j) {
		int matrix[][] = new int[i + 1][j + 1];
		char[][] hintarr = new char[i + 1][j + 1];
		
		for(int x = 0;x<=i;x++) {
			matrix[x][0] = 0;
			hintarr[x][0] = '|';
		}
		for(int y = 0;y<=j;y++) {
			matrix[0][y] = 0;
			hintarr[0][y] = '-';
		}
		for(int x = 1;x<=i;x++) {
			for(int y = 1;y<=j;y++) {
				
				/*
				if(x == 0 || y == 0) {
					//LCS("",STR) or LCS(STR,"")
					//RESULT WILL BE 0 ALWAYS
					//matrix[x][y] = 0;
				}
				*/
				//match removed each element from string and count++
				 if(str1.charAt(x-1) == str2.charAt(y-1)) {
					matrix[x][y] = 1 + matrix[x - 1][y - 1];
					hintarr[x][y] = '\\'; //escape char
					//System.out.println(matrix[x][y]);
				}
				
				//no match check remove element from which string yield the best result
				else {
					int result;
					//int result = Math.max(matrix[x-1][y],matrix[x][y-1]);
					if(matrix[x-1][y] >= matrix[x][y-1]) {
						result = matrix[x-1][y];
						hintarr[x][y] = '-';
						
					}
					else {
						result = matrix[x][y-1];
						hintarr[x][y] = '|'; //escape char
						
					}
					matrix[x][y] = result;
				}
			}
		}
		printMatrix(hintarr, i, j);
		getSequence(hintarr, str1, str2, i, j);
		return matrix[i][j];
	}
	private void printMatrix(char[][] hintArr,int i ,int j) {
		for(int y = 0;y<=i;y++) {
			for(int x = 0;x<=j;x++) {
				System.out.print(hintArr[y][x] + " ");
			}System.out.println();
		}
	}
	
	private void getSequence(char[][] hintArr,String str1,String str2,int i,int j) {
		//O(n+m)
		List<Character> strArr = new ArrayList<Character>();
		//array start from index 0
		int x = i;
		int y = j;
		while(x != 0 && y != 0) {
			//System.out.println("x " + x + " y " + y);
			if(hintArr[x][y] == '\\') {
				strArr.add(0, str1.charAt(x-1));
				x--;
				y--;
				
			}
			else if(hintArr[x][y] == '-') {
				x--;
			}
			else if(hintArr[x][y] == '|') {
				y--;
			}
		}
		System.out.println("Sub sequence: " + strArr);
	}
}
