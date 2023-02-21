package study_17;

import java.util.*;
import java.io.*;

/* dp : 각 (i,j)의 위치에서 먹을 수 있는 사탕의 최댓값 저장 배열 
 * [i-1,j] [i,j-1] [i-1,j-1] 중 최댓값 + [i,j] 
 * 단, i=0/j=0 일 경우에 j-1/i-1 은 indexoutof~ 에러가 발생하므로 
 * 왼쪽, 위쪽 한줄은 0으로 감싸준다. */
public class Solution_BaekJoon_11048 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][M+1]; 
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " "); 
			for(int j=1; j<=M; j++) {
				// 현재 위치에 놓여있는 사탕 갯수 
				int candy = Integer.parseInt(st.nextToken()); 
				dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i-1][j-1])) + candy;
			}
		}
		
		System.out.println(dp[N][M]);
		
		
	}// end of main 
}// end of class 
