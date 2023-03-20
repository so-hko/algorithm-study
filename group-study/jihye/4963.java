import java.util.*;
import java.io.*;

public class Main {
	static int dx[]= {-1,-1,-1,0,0,1,1,1};
	static int dy[]= {-1,0,1,-1,1,-1,0,1};
	private static int r,c;
	private static int map[][];
	private static boolean visited[][]; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb= new StringBuilder(); 
		
		while(true) {
			st= new StringTokenizer(br.readLine(), " "); 
			c= Integer.parseInt(st.nextToken());
			r= Integer.parseInt(st.nextToken());
			//입력종료
			if(c==0 & r==0) {
				if(sb.length()>0) sb.deleteCharAt(sb.length()-1); 
				break; 
			}
			
			map = new int [r][c]; 
			visited = new boolean[r][c]; 
			for(int i=0; i<r; i++) {
				st=new StringTokenizer(br.readLine(), " "); 
				for(int j=0; j<c; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}//섬과 바다 입력 
			
			int cnt=0; //섬의 갯수 
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					if(map[i][j] == 1) {
//					if(!visited[i][j] && map[i][j] == 1) { //dfs에서 0으로 안바꿀경우
						cnt++; 
						dfs(i,j); 
					}
				}
			}//dfs
			
			sb.append(cnt).append("\n");
		}
	
		System.out.println(sb);
	
	}//end of main 
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int d=0; d<dx.length; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d]; 
			// 경계밖이거나, 탐색했었거나, 바다이면
			if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0) continue;
			
			// 탐색하는 곳이 섬이면 
			map[nx][ny] = 0; //다음번에 똑같은 곳에서 탐색안되도록, 0으로 바꿔주기
			visited[nx][ny] = true;
			
			dfs(nx,ny); 
		}
	}
	
	//지도밖으로 벗어났는지
	private static boolean isOut(int x, int y){
		return x<0 || x>=r || y<0 || y>=c; 
	}
}//end of class 
