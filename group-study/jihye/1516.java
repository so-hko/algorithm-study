import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		Queue<Integer> que= new LinkedList<>();
		int N = Integer.parseInt(br.readLine()); //건물의 종류 수 
		List<ArrayList<Integer>> list= new ArrayList<>(); 

		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		int[] time = new int[N+1];//건물을 짓는데 걸리는 시간 
		int[] inCnt = new int[N+1];//진입차수 
		//입력받은 값으로 연결 리스트 그래프 구현 
		for(int i=1; i<=N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			while(true) {
				int j = Integer.parseInt(st.nextToken()); 
				if(j == -1) break; 
				inCnt[i]++; 
				list.get(j).add(i); 
			}
			if(inCnt[i]==0) {
				que.add(i);
			}
		}
		
		int pre_time[] = new int[N+1]; //건물을 지을 수 있을 때까지 걸리는 시간 
		while(!que.isEmpty()) {
			int now= que.poll();
			for(int i=0; i<list.get(now).size(); i++) {
				int next= list.get(now).get(i);
				inCnt[next]--;
				pre_time[next] = Math.max(pre_time[next], time[now]+pre_time[now]); 
				if(inCnt[next] == 0) {
					que.add(next);
				}
			}
		}
		
		for(int i=1; i<=N; i++) System.out.println(time[i]+pre_time[i]);
	}//end of main 
	
	
}
