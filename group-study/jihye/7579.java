import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st, st1;
		
		st= new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); //앱의 갯수 
		int M = Integer.parseInt(st.nextToken()); //확보해야 하는 메모리 
		
		int app[][] = new int[N+1][2]; //앱의 정보 {메모리, 앱 비용} 
		
		st= new StringTokenizer(br.readLine(), " ");
		st1= new StringTokenizer(br.readLine(), " ");
		int max_time=0; //모든 앱을 비활성화 했을 떄의 최대시간 
		for(int i=1; i<=N; i++) {
			app[i][0]= Integer.parseInt(st.nextToken());
			app[i][1]= Integer.parseInt(st1.nextToken()); 
			max_time += app[i][1]; 
		}
		
		//dp[i][j] : 각 비용 j로 확보할 수 있는 최대 메모리 
		int[][]dp = new int [N+1][max_time+1];
		int ans=Integer.MAX_VALUE; 
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=max_time; j++) {
				//현재 앱을 활성화 시키위한 비용 > 현재 가진 비용 → 현재꺼 활성화 못시킴 
				if(app[i][1] > j) dp[i][j] = dp[i-1][j]; 
				
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-app[i][1]]+app[i][0]); 
				}
				//필요한 메모리 이상이 모였다면, 
				if(dp[i][j] >= M) ans=Math.min(ans, j);  


			}
		}
		
		System.out.println(ans);
		
	}//end of main
}//end of class 
