import java.util.*;
import java.io.*;
public class Main {
	private static int dx[] = {-1,1,0,0};
	private static int dy[] = {0,0,-1,1};
	
	private static int N,M;
	private static int tomato[][]; 
	private static Queue<int[]> start= new LinkedList<>(); 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		tomato= new int[N][M]; 
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " ") ;
			for(int j=0; j<M; j++) {
				tomato[i][j]= Integer.parseInt(st.nextToken());
				if(tomato[i][j]==1) start.add(new int[] {i,j}); 
			}
		}
		
		System.out.println(riped());
	
	}//end of main 
	private static int riped() {
		int time=0; 
		while(true) {
			if(isEnd()) return time; //처음부터 다 익었으면 바로 0, 중간에 다 익었으면 return 
			int size= start.size();
			
			if(size==0) break; 
			for(int i=0; i<size; i++) {
				int[] a = start.poll(); 
				int x= a[0];
				int y= a[1];
				
				for(int dir=0; dir<4; dir++) {
					int nx= x+dx[dir];
					int ny= y+dy[dir];
					
					//경계 밖, 익었거나 없거나 
					if(isOut(nx, ny) || tomato[nx][ny] !=0) continue;
					
					tomato[nx][ny]=1;
					start.add(new int[]{nx,ny});
				}
			}
			time++;	
		}
		return -1; 
	}
	
	private static boolean isEnd() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tomato[i][j]==0) return false; //안익은 토마토 남았음 
			}
		}
		return true; 
	}
	
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M; 
	}
}//end of class
 
