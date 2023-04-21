import java.util.*;
import java.io.*;

/* 부대-탈영병, 탈영병-탈영병 간 최단 거리 구해놓기
 * 탈영별의 수 5명 이하니까 순열*/
public class Main {
	static List<Point> list;//탈영병 위치 리스트
	static int N; 
	static int map[][];
	static int sx, sy; //부대 위치 
	static class Point implements Comparable<Point>{
		int x; int y;
		int dis; 
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		public Point(int x, int y, int dis) {
			this.x=x;
			this.y=y;
			this.dis=dis; 
		}
		@Override
		public int compareTo(Point o) {
			return this.dis-o.dis;
		}
			
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		N= Integer.parseInt(br.readLine()); //격자의 크기 
		map= new int[N][N]; 
		
		list= new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken()); 
				if(map[i][j]==0) list.add(new Point(i,j)); 
				if(map[i][j]==-1) {
					sx=i; sy=j;
					map[i][j] = 0; 
				}
			}
		}
		
		out_num=list.size();//탈영병 몇명 
		//탈영병이 없을 경우, 
		if(out_num==0) {
			System.out.println(0); 
			System.exit(0);
		}
		
		
		mindis=new int[out_num+1][out_num+1];
		check= new boolean[list.size()]; 
		num = new int[list.size()]; 
		
		findMin(); 
//		for(int i=0; i<mindis.length; i++) {
//			for(int j=0; j<mindis.length; j++) {
//				System.out.print(mindis[i][j]+" ");
//			}
//			System.out.println();
//		}
		permute(0); 
		
		System.out.println(min);
	}//end of main 

	static int out_num; //탈영병 수 
	static int mindis[][]; //부대-탈영병간 최단 거리 (0:부대, 1~:탈영병) 
	/*부대-탈영병간의 최단 거리 저장해놓기*/
	static void findMin() {
		for(int i=0; i<=out_num; i++) mindis[i][i]=0;
		
		for(int i=0; i<out_num; i++) {
			for(int j=i+1; j<=out_num; j++) {
				Point next =list.get(j-1); 
				if(i==0) {
					mindis[i][j] = mindis[j][i] = dijkstra(sx, sy, next.x, next.y);
				}
				else {
					Point pre= list.get(i-1);
					mindis[i][j]= mindis[j][i]= dijkstra(pre.x, pre.y, next.x, next.y); 
				}
				
			}
		}
	}

	
	static boolean check[]; 
	static int num[]; 
	static int min= Integer.MAX_VALUE;
	//탈영병 순서 정하기 (순열) n:탈영병 수 
	static void permute(int index) {
		if(index == list.size()) {
			int sum=0; 
			int pre=0; //처음엔 부대에서 시작 
			int next;
			for(int i=0; i<num.length; i++) {
				next= num[i]+1; 
//				System.out.println(pre+" -> "+next);
				sum+=mindis[pre][next];
				pre=next; 
			}

			sum+= mindis[pre][0]; 
			min = Math.min(sum, min);
			

		}
		
		for(int i=0; i<list.size(); i++) {
			if(!check[i]) {
				check[i] = true; 
				num[index] = i;
				permute(index+1); 
				check[i] = false; 
			}
		}
	}
	
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1};
	static boolean visited[][]; 
	static int minDis[][]; 
	 
	//출발지에서 도착지까지의 다익스트라 
	private static int dijkstra(int startx, int starty, int endx, int endy) {
		PriorityQueue<Point> que= new PriorityQueue<>();
		visited = new boolean[N][N]; 
		minDis= new int[N][N]; 
		for(int i=0; i<N; i++) Arrays.fill(minDis[i], Integer.MAX_VALUE); 
		minDis[startx][starty]=0; 
		
		que.add(new Point(startx, starty, 0)); 
		
		while(!que.isEmpty()) {
			Point p =que.poll();
			int x =p.x;
			int y =p.y;
			int dis = p.dis;
			
			if(visited[x][y]) continue;
			visited[x][y]= true; 
			
			for(int dir=0; dir<4; dir++) {
				int nx= x+dx[dir];
				int ny= y+dy[dir];
				
				if(isOut(nx,ny)) continue;

				if(minDis[nx][ny] > map[nx][ny] + dis) {
					minDis[nx][ny] = map[nx][ny]+dis;
					que.add(new Point(nx, ny, minDis[nx][ny])); 
				}
			}
			
		}
		return minDis[endx][endy]; 
	}
	
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=N; 
	}
}//end of class 
