import java.util.*;
import java.io.*;

public class Solution_BaekJoon_3020 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
		
		int N = Integer.parseInt(st.nextToken()); //동굴의 가로 길이(무조건 짝수) 
		int H = Integer.parseInt(st.nextToken()); //동굴의 세로 높이
		
		int[] block = new int[H+1]; //각 높이에서 장애물의 갯수  
		int[] up = new int[H+1]; //종유석(↓) 위에서부터 특정높이에 몇개걸리는지
		int[] down = new int[H+1]; //석순(↑) 아래서부터 특정높이에 몇개걸리는지 
		
		//석순-종유석 이 번갈아가면서 나온다
		for(int i=0; i<N/2; i++) {
			down[Integer.parseInt(br.readLine())]++;
			up[Integer.parseInt(br.readLine())]++;
		}
		
		for(int i=H; i>1; i--) {
			up[i-1] += up[i]; 
			down[i-1] +=down[i]; 
		}

		int min=Integer.MAX_VALUE;
		int min_cnt=0; 
		for(int i=1; i<=H; i++) {
			block[i] = up[i]+down[H+1-i];
			if(min ==block[i]) {
				min_cnt++;
			}
			if(min>block[i]) {
				min=block[i]; 
				min_cnt=1; 
			}
		}
		System.out.println(min+" "+min_cnt);
	}//end of main
}//end of class
