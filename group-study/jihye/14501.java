import java.util.*;

/*
 * 3 10
 * 5 20
 * 1 10
 * 1 20
 * 2 15
 * 4 40
 * 2 200*/
public class Main {
	public static void main(String args[]) {
		Scanner sc= new Scanner(System.in);
		
		int N= sc.nextInt();
		int[] dp = new int[N+1]; 
		int[] time = new int[N+1]; 
		int[] pay = new int[N+1];
		List<Integer>[] list =new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			list[i]= new ArrayList<>(); 
		}
		
 		for(int i=1; i<=N; i++) {
			time[i] = sc.nextInt();
			pay[i] = sc.nextInt(); 
			int a = i+time[i]-1; 
			if(a>N) continue; 
			list[a].add(i); 
 		
 		}
 		
 		for(int i=1; i<=N; i++){
 			dp[i]=dp[i-1]; 
 			//해당 시간에 끝난 일이 있다면, 
 			if(!list[i].isEmpty()) {
 				for(int j=0; j<list[i].size(); j++) {
 					int a=list[i].get(j); 
 					dp[i] = Math.max(dp[a-1]+pay[a], dp[i]); 
 				}
 			}
 		}
 		
 		System.out.println(dp[N]);
	}
}
