import java.util.*;
import java.io.*;
/*
 * 최단거리 구해야하니까 bfs로 
 * 거리 우선순위큐 
 * 방문체크 하되, 각 키의 여부에 따라 따로 체크해야함 
 * 좌표 위치 + 키 여부 모두 포함시킨 3차원 배열 사용 
 * [x좌표][y좌표][키]
 * 키 가지고 있는거 1 , 안가지고 있는거 0으로 해서 비트 
 * 키 6개 = 000000 또는 111111 , 즉 다가지고 있을 경우 63 (0~63)
 * 1에서 부터 (key-'a')만큼 << 연산 
 */
public class Solution_BaekJoon_1194 {
	
	//수평-수직 이동가능 방향 
	private static int dx[]= {-1,1,0,0};
	private static int dy[]= {0,0,-1,1};
	
	private static int N;
	private static int M;
	private static char map[][]; 
	private static boolean visited[][][]; 
	
	/*각 위치의 정보 : 좌표,출발지로부터 거리,key*/
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int distance;//출발점으로부터의 위치 
		int key; //가지고 있는 키 정보 000000~111111
		
		public Point(int x, int y, int distance, int key){
			this.x=x;
			this.y=y;
			this.distance=distance;
			this.key=key; 
		}
		
		@Override
		public int compareTo(Point o) {
			return this.distance-o.distance; 
		} 
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st =new StringTokenizer(br.readLine(), " ");
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken()); 
		map = new char[N][M];
		//키x:000000(0) ~ 키o:111111(63)
		visited= new boolean[N][M][64]; 
		
		int startx=0;
		int starty=0; 
		for(int i=0; i<N; i++) {
			String s =br.readLine(); 
			for(int j=0; j<M; j++) {
				map[i][j]= s.charAt(j); 
				//출발 지점 저장 
				if(map[i][j]=='0') {
					startx= i;
					starty= j;
				}
			}
		}
		
		int res=bfs(startx, starty); 
		System.out.println(res);	
		
	}//end of main
	private static int bfs(int startx, int starty) {
		PriorityQueue<Point> que = new PriorityQueue<>(); 
		//처음 시작할때는 아무 키도 없음 
		int k=0; 
		visited[startx][starty][k]=true; 
		que.add(new Point(startx, starty, 0, k)); 
	
		while(!que.isEmpty()) {
			Point p = que.poll();
			int x = p.x;
			int y = p.y;
			int dis = p.distance; 
			int key = p.key;
			
			//출구 만났을 경우
			if(map[x][y]=='1') {
				return dis;
			}
		
			for(int dir=0; dir<4; dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir]; 
				//미로 밖으로 나갔거나, 벾을 만났을 경우 , 같은 키 상태로 방문했을 경우 
				if(isOut(nx, ny) || map[nx][ny] == '#'|| visited[nx][ny][key]) continue; 
				
				//열쇠를 만났을 경우 
				if(map[nx][ny] >= 'a' && map[nx][ny] <='f') {
					//현재키를 2진수로 표현  :1에서 부터 (key-'a')만큼 << 연산 
					int nkey= 1 << (map[nx][ny]-'a'); 
					//지금 있는 키에 키 더하기 
					int newkey = nkey | key; //or연산을 해야 0|1 = 1 
					visited[nx][ny][newkey]= true; 
					que.add(new Point(nx, ny, dis+1, newkey)); 
					continue; 
				}
				
				//문을 만났을 경우 
				if(map[nx][ny] >='A' && map[nx][ny] <= 'F') {
					//현대 키 상태 and 문을 이진수로 바꾼 
					//and 연산은 둘다 1이어야 1이 나오므로, 문에 대한 키를 가지고 있다면 전체 결과 1 이상 
					int door = 1 << (map[nx][ny]-'A'); 
					
					//문에 대한 키를 가지고 있으면 무조건 1이상 나옴
					if((door & key) < 1) continue; // 키x
				}
				//문에 대한 키가 있거나, 빈칸일 경우 
				visited[nx][ny][key]= true;
				que.add(new Point(nx,ny,dis+1, key)); 
			}

		}//while
		return -1;
	}//end of bfs 
	
	/*미로 밖으로 나갔는지 확인하는 메소드 , 나갔을 경우 true 리턴 */
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M; 
	}
}//end of class
