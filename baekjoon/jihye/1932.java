import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		int n = Integer.parseInt(br.readLine()); // 삼각형의 높이
	
		int[][] map = new int[n+1][n+1]; 

		
		int max= Integer.MIN_VALUE; 
		for(int i=1; i<=n; i++) {
			String s= br.readLine(); 
			st = new StringTokenizer(s, " "); 
			for(int j=1; j<i+1; j++) {
				int now = Integer.parseInt(st.nextToken()); 
				map[i][j]  = Math.max(map[i-1][j-1], map[i-1][j])+now;
				
				if(i==n) {
					max = Math.max(max, map[i][j]); 
				}
 			}
		}// end of input 
		
		System.out.println(max);
	}// end of main 
}// end of class 
