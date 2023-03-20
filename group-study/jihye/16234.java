import java.util.*;
import java.io.*;
/*
 * 특정 땅에서 시작해서 dfs(깊이우선)로 탐색
 * 국경선 열 수 있는 경우, 큐에 넣음 
 * 탐색하다가 더 이상 국경선 열 수 없을 때 큐에 있는 것들 빼서 연합 
 * 큐에 있는 땅들 인구수 조절 
 * 그 다음 탐 색 ㄱ */
public class Main {
	private static int dx[]= {-1,1,0,0};
	private static int dy[]= {0,0,-1,1}; 
	private static int N,L,R;
	private static int[][] map;
	private static boolean[][] visited;
	private static int sum;
	//하나의 땅에 대한 정보 
	static class Ground{
		//땅 위치(x,y)
		int x;
		int y;
		
		public Ground(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static Queue<Ground> que= new LinkedList<>(); 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		N= Integer.parseInt(st.nextToken()); //땅의 크기 
		//인구차이가 L명 이상 R명 이하일 때, 국경선 열 수 있음 
		L=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		map = new int[N][N]; 
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				//각 땅의 인구 수
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		int count =0; 
		
		while(true) {
		boolean isChange=false; 
		visited= new boolean[N][N]; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					sum=0;
					dfs(i,j); 
					
					// 탐색 시작한 거에서 더 연합 못한 경우 
					if(que.size() == 1) { 
						que.poll(); 
						continue;
					}
					isChange = true;
					//연합국 한 나라 인구수 
					int one_people=sum/que.size(); 
					while(!que.isEmpty()) {
						Ground g= que.poll();
						map[g.x][g.y] = one_people; 
					}
					
				}
			}
		}
		
			if(!isChange) break; 
			count ++; 
		}
		System.out.println(count);
		
	}//end of main 
	private static void dfs(int i, int j) {
		//방문체크하고, 큐에 넣고, 연합 인구수합에 더하고 
		visited[i][j] = true;
		que.add(new Ground(i,j)); 
		sum+=map[i][j]; 
		
		for(int d=0; d<4; d++) {
			// 4방탐색 
			int ni = i+dx[d];
			int nj = j+dy[d]; 
			//땅 밖으로 나가거나, 이미 탐색 했던 곳 ( 하루에 동시 탐색하는 땅 x ) 
			if(isOut(ni,nj) || visited[ni][nj]) continue; 
			
			//인접한 두 국가의 인구수 차이
			int dif= Math.abs(map[i][j]-map[ni][nj]);
			if(dif>=L && dif<=R) {
				dfs(ni, nj); 			
			}
			
		}
	}//end of dfs 
	
	private static boolean isOut(int i, int j) {
		return i<0 || i>=N || j<0 || j>=N;
	}
}//end of class 
