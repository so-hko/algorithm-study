import java.util.LinkedList;
import java.util.*;

/**
 * 벽 3개 세울 수 있는거 다 세워보면서 bfs 로 퍼트리고 
 * 최댓값 ? */
public class Main{
	private static int N;
	private static int M;
	private static int[][] map; 
	private static int dx[] = {-1,1,0,0};
	private static int dy[] = {0,0,-1,1};
	private static int max;
	static class Point{
		int r, c ;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in); 
		
		N= sc.nextInt(); 
		M= sc.nextInt(); 
		map = new int[N][M];
		max= Integer.MIN_VALUE; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}//입력 
		
		find(0); 
		
		System.out.println(max);
	}// end of main 

	static void find(int count) { // 0의 좌표를 모아놓은 list의 인덱스 탐색 
		
		//세개 다 찾으면 바이러스 퍼트려 보기 
		if(count==3) {
			int[][] nmap = new int[N][M];
			//배열 하나 복사해서 그걸로 넘기기 
			for(int i=0; i<N; i++){
				for(int j=0; j<M; j++) {
					nmap[i][j] = map[i][j] ; 
				}
			}
			
			virus(nmap); 
			return ; 
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M ; j++) {
				if(map[i][j]==0) {
					map[i][j]=1;
					find(count+1); 
					map[i][j]=0; 
				}
			}
		}
			
	}
	static void virus(int[][] nmap) {
		Queue<Point> que = new LinkedList<>();
		//바이러스가 있는 곳의 위치를 큐에 저장 
		for(int i=0; i<N; i++) {
			for(int j =0; j<M; j++) {
				if(nmap[i][j]==2) que.add(new Point(i,j));
			}
		}
		while(que.size()>0) {
			Point current= que.poll();
			int r=current.r;
			int c=current.c;
			
			for(int dir=0; dir<4; dir++) {
				int nr= r+dx[dir];
				int nc= c+dy[dir];
				//범위 안에 있고 벽이 아니면 
				if(nr>=0 && nr<N && nc>=0 && nc<M && nmap[nr][nc]==0) {
					nmap[nr][nc]=2; 
					que.add(new Point(nr,nc)); 
				}
			}
		}
		
		count(nmap); 
	}
	public static void count(int[][] nmap) {
		 int cnt =0; 
		 for(int i=0; i<N; i++) {
			 for(int j=0; j<M; j++) {
				 if(nmap[i][j]==0) cnt++; 
			 }
		 }
		 if( cnt > max) max= cnt; 
	}
}// end of class
