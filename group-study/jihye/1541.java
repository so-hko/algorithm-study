import java.util.*;


public class Solution_BaekJoon_1541 {
	public static void main(String[] args)  {
		Scanner sc= new Scanner(System.in);
		StringTokenizer st= new StringTokenizer(sc.next(), "-");
		StringTokenizer st1; 
		
		int res = Integer.MAX_VALUE; 
		while(st.hasMoreTokens()) {
			String s = st.nextToken(); // 55  50+40
			st1= new StringTokenizer(s, "+");
			int sum=0; 
			while(st1.hasMoreElements()) {
				sum+= Integer.parseInt(st1.nextToken()); 
			}
			if(res == Integer.MAX_VALUE) res = sum;
			else res-=sum; 
		}
		
		System.out.println(res);

	}//end of main 
}//end of class
