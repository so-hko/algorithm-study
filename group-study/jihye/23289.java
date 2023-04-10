import java.util.*;
import java.io.*;

public class Main {
	
	//우 좌 상 하 
	private static int dx[]= {0,0,-1,1};
	private static int dy[]= {1,-1,0,0};
	
	private static int R,C,K;
	private static int[][] room; //방의 정보 
	private static int[][] wind; //바람 나오는 정보 (매번 같음) 
	private static int[][] wall; //벽 정보 // 1 ~ 3 ( 3은 둘다) 
	private static int chocolate=0; //101개 되면, 그냥 101 출력하고 종료 
	
	static Queue<Fan> hotfan= new LinkedList<>(); //주어진 온풍기 
	static List<int[]> search = new ArrayList<>(); //조사해야 하는칸 
	
	//온풍기 있는 곳은 
	static class Fan{
		int x; 
		int y;
		int dir;
		public Fan(int x, int y, int dir) {
			this.x=x;
			this.y=y;
			this.dir=dir;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine(), " "); 
		R=Integer.parseInt(st.nextToken()); 
		C=Integer.parseInt(st.nextToken()); 
		K=Integer.parseInt(st.nextToken());
		
		room= new int[R][C];
		for(int i=0; i<R; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				room[i][j]=Integer.parseInt(st.nextToken()); 
				if(room[i][j] >=1 && room[i][j] <=4) {
					hotfan.add(new Fan(i,j,room[i][j]-1)); 
					room[i][j]=0; //온도랑 헷갈리니까 0으로 바꾸기 
				}
				else if (room[i][j] ==5) {
					search.add(new int[]{i,j});
					room[i][j]=0; //온도랑 헷갈리니까 0으로 바꾸기 
				}
			}
		}
		
		int W= Integer.parseInt(br.readLine());
		wall= new int[R][C]; 
		//1이면 (x,y)와 ( x-1,y사이) , 2이면 (x,y)와 (x,y+1) 사이 
		while(W-->0) {
			st= new StringTokenizer(br.readLine(), " ");
			int wr= Integer.parseInt(st.nextToken())-1;
			int wc= Integer.parseInt(st.nextToken())-1;
			int wt= Integer.parseInt(st.nextToken())+1; 
			
			wall[wr][wc] += wt; 	
		}

		simulate(); 
		System.out.println(chocolate);
	}//end of main 
	private static void simulate() {
		//일단 온풍기에서 바람 나올 경우 각 방에 온도가 몇씩 증가하는지 계산해서 저장해두기 
		wind= new int[R][C];
		fanStart(); 

		while(chocolate < 101) {
			//1.집에 있는 모든 온풍기에서 바람이 한번 나옴 
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					room[i][j] += wind[i][j]; 
				}
			}
            
			//2.온도가 조절됨 
			controlTemp(); 

			
			//3.온도가 1이상인 가장 바깥쪽 칸의 온도가 1씩 감소 
			minusTemp();

			//4.초콜렛을 하나 먹는다
			chocolate++;
			
			//5.조사하는 모든 칸의 온도가 K이상 되었는지 검사 
			int res= checkTemp(); 
			if(res == 1) break; 
		}
	}//simulate
	
	/*온풍기에서 바람 나오면 각 칸 온도 얼마나 올라가는지 > wind 에 저장 */
	private static void fanStart() {
		boolean visited[][]; 
		while(!hotfan.isEmpty()) {
			visited=new boolean[R][C];
			//온풍기 하나 틀기 
			Fan fan=hotfan.poll(); 
			int x= fan.x;
			int y= fan.y;
			int dir=fan.dir;
			
			x+=dx[dir];
			y+=dy[dir]; 
/*			//처음 바람은 무조건 감 
			wind[x][y] +=5;
		*/
			windstart(x,y,dir,visited); 
			
		}//while
	}
	/*온풍기 바람 불기 시작, 바람 방향에 따라 보내기 */
	private static void windstart(int x, int y, int dir, boolean visited[][]) {
		switch(dir) {
		//오른쪽 
		case 0:
			dfsRight(x,y,visited,dir,5); 
			break; 
		//왼쪽
		case 1:
			dfsLeft(x,y,visited,dir,5); 
			break; 
		//위쪽
		case 2:
			dfsUp(x,y,visited,dir,5); 
			break;
		//아래쪽 
		case 3:
			dfsDown(x,y,visited,dir,5); 
			break; 
		}
	}
	static int more[]= {-1,0,1}; //위아래 또는 양옆까지 퍼져나가는 부분 
	/*온풍기 방향 오른쪽일 때 */
	private static void dfsRight(int x, int y, boolean visited[][], int dir, int power) {
		//온풍기 바람 끝 ! 
		if(power==0) {
			return; 
		}
		visited[x][y]=true;
		wind[x][y] += power; 
		
		for(int i=0; i<3; i++) {
			int nx= x+dx[dir]+more[i];
			int ny= y+dy[dir]; 
			
			//경계밖으로 나간거나, 이미 바람이 간 곳이거나
			if(isOut(nx,ny) || visited[nx][ny]) continue; 
			//오른쪽 위로 갈때 
			if(i==0) {
				//현재 칸 위쪽이나, 위칸 오른쪽에 벽이 있을 때 못감
				if(wall[x][y] == 3 || wall[nx][y]==3 ||  wall[x][y]==1 || wall[nx][y]==2) continue;
			}
			//바로 오른쪽
			else if(i==1) {
				//현재 칸 오른쪽 벽 있을 때 못감 
				if(wall[x][y] == 3 || wall[x][y]==2) continue; 
			}
			//오른쪽 아래로 갈떄 
			else {
				//아래칸에 위쪽 벽이나, 오른쪽 벽 있을 때 못감 
				if(wall[nx][y]>=1 ) continue; 
			}
			
			//벽도 없고, 경계도 안넘어가서 갈 수 있을 때 
			dfsRight(nx,ny,visited,dir,power-1);
		}
	
	}
	/*온풍기 방향 왼쪽일 때 */
	private static void dfsLeft(int x, int y, boolean visited[][], int dir, int power) {
		//온풍기 바람 끝 ! 
		if(power==0) {
			return; 
		}
		visited[x][y]=true;
		wind[x][y] += power; 
		
		for(int i=0; i<3; i++) {
			int nx= x+dx[dir]+more[i];
			int ny= y+dy[dir]; 
			
			//경계밖으로 나간거나, 이미 바람이 간 곳이거나, 도착할 칸에 오른쪽 벽이 있거나 
			if(isOut(nx,ny) || visited[nx][ny] || wall[nx][ny]>=2) continue; 
			//왼쪽 위로 갈때 
			if(i==0) {
				//현재 칸에 위쪽에 벽있을 때 
				if(wall[x][y]==1 || wall[x][y] ==3 ) continue; 
			}
			//왼쪽 아래로 갈때 
			else if(i==2) {
				//아래칸에 위쪽 벽 있을 때 
				if(wall[nx][y]==1 || wall[nx][y]==3) continue; 
			}
			
			//벽도 없고, 경계도 안넘어가서 갈 수 있을 때 
			dfsLeft(nx,ny,visited,dir,power-1);
		}
	
	}
	
	/*온풍기 방향 위쪽일 때 */
	private static void dfsUp(int x, int y, boolean visited[][], int dir, int power) {
		//온풍기 바람 끝 ! 
		if(power==0) {
			return; 
		}
		visited[x][y]=true;
		wind[x][y] += power; 
		
		for(int i=0; i<3; i++) {
			int nx= x+dx[dir];
			int ny= y+dy[dir]+more[i]; 
			
			//경계밖으로 나간거나, 이미 바람이 간 곳이거나
			if(isOut(nx,ny) || visited[nx][ny]) continue; 
			//왼쪽 위로 갈때 
			if(i==0) {
				//왼쪽 칸에 위쪽 벽이나 오른쪽 벽 있으면 못감
				if(wall[x][ny]>=1) continue; 
			}
			//바로 위로 갈 때 
			else if(i==1) {
				//현재칸에 위쪽 벽 있으면 못감 
				if(wall[x][y]==1 || wall[x][y]==3) continue; 
			}
			//오른쪽 위로 갈떄 
			else {
				//현재칸에 오른쪽 벽있거나, 오른쪽 칸에 위쪽 벽 있는지 확인 
				if(wall[x][y]>=2 || wall[x][ny]==1 ||wall[x][ny]==3) continue; 
			}
			
			//벽도 없고, 경계도 안넘어가서 갈 수 있을 때 
			dfsUp(nx,ny,visited,dir,power-1);
		}
	
	}
	
	
	/*온풍기 방향 아래쪽일 때 */
	private static void dfsDown(int x, int y, boolean visited[][], int dir, int power) {
		//온풍기 바람 끝 ! 
		if(power==0) {
			return; 
		}
		visited[x][y]=true;
		wind[x][y] += power; 
		for(int i=0; i<3; i++) {
			int nx= x+dx[dir];
			int ny= y+dy[dir]+more[i]; 
			
			//경계밖으로 나간거나, 이미 바람이 간 곳이거나, 도착할 벽에 위쪽에 벽이 있으면 
			if(isOut(nx,ny) || visited[nx][ny] ||  wall[nx][ny]==1 || wall[nx][ny]==3) continue; 
			//왼쪽 아래로 갈때 
			if(i==0) {
				//왼쪽 칸에 오른쪽 벽 있으면 못감 
				if(wall[x][ny] >=2 ) continue; 
			}
			//오른쪽 아래로 갈 때 
			else if(i==2) {
				//현재 벽에 오른쪽 벽 있으면 못감 
				if(wall[x][y] >=2) continue; 
			}
			
			//벽도 없고, 경계도 안넘어가서 갈 수 있을 때 
			dfsDown(nx,ny,visited,dir,power-1);
		}
	
	}
	
	static class Point{
		int x; int y; int temp; 
		public Point(int x , int y, int temp) {
			this.x=x;
			this.y=y;
			this.temp=temp; 
		}
	}
	/*온도 조절 - 큐쓸까  벽 있는 곳 조절 안됨 */
	private static void controlTemp() {
		boolean isChange[][] = new boolean[R][C]; 
		int[][] copy = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				copy[i][j] = room[i][j]; 
			}
		}
		Queue<Point> que= new LinkedList<>(); 
		//우선 온도 조절할 수 있는 곳들 다 큐에 넣기 (0 이상 온도 ) 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(room[i][j] >0) {
					que.add(new Point(i,j,room[i][j])); 					
				}
			}
		}
		
		//큐에서 하나씩 빼서 온도 조절하기 
		while(!que.isEmpty()) {
			Point p = que.poll(); 
			int x= p.x;
			int y= p.y;
			int temp = p.temp;
			isChange[x][y] = true; 
			
			for(int dir=0; dir<4; dir++) {
				int nx= x+dx[dir];
				int ny= y+dy[dir];
				
				if(isOut(nx,ny) || isChange[nx][ny]) continue; 
				
				/*벽 있는지 확인*/
				//오른쪽으로 갈 떄는 
				if(dir==0) {
					//현재 위치에 오른쪽 벽이 없어야 함 
					if(wall[x][y]>=2) continue; 
				}
				//왼쪽으로 갈 때는 
				else if(dir==1) {
					//이동한 칸에 오른쪽 벽이 없어야함
					if(wall[nx][ny]>=2) continue; 
				}
				//위로 갈때는
				else if(dir==2) {
					//현재 위치에 위쪽 벽 없어야 함 
					if(wall[x][y]==1 || wall[x][y]==3) continue; 
				}
				//아래로 갈때는, 이동한 위치에 위 벽 없어야 함 
				else {
					if(wall[nx][ny]==1 || wall[nx][ny]==3) continue; 
				}
				
				
				//조절이 가능한 경우
				int ntemp = copy[nx][ny]; //이동한 곳의 온도  
				int a = (Math.abs(temp-ntemp))/4; //조절되어질 온도  
//				System.out.println("온도 조절 : "+x+" "+y +"에서 " +nx+" "+ny+"로 "+a+"만큼");
				if(temp > ntemp) {
					room[x][y] -=a;
					room[nx][ny] +=a;
				}
				else {
					room[x][y] +=a;
					room[nx][ny] -=a; 
				}
				
			}//end of for
		}
	}
	
	
	/*온도가 1이상인 가장 바깥쪽 칸 온도 마이너스 */
	private static void minusTemp() {
		for(int x=0; x<R; x++) {
			if(room[x][0] >0) room[x][0]--; 
			if(room[x][C-1] >0) room[x][C-1] --; 
		}
		for(int y=1; y<C-1; y++) {
			if(room[0][y] >0 ) room[0][y]--;
			if(room[R-1][y] >0) room[R-1][y]--; 
		}
		
	}
	
	/*조사하는 모든 칸의 온도가 k 이상 이 되었는지 검사 */
	private static int checkTemp() {
		for(int i=0; i<search.size(); i++) {
			int x = search.get(i)[0];
			int y = search.get(i)[1];
			if(room[x][y] < K) {
				return -1;
			}	
		}
		return 1;
	}
	/*온풍기 바람이 경계 밖으로 나가는지 확인 */
	private static boolean isOut(int x, int y) {
		return x<0 || x>=R || y<0 || y>=C; 
	}
}//end of class
