import java.util.*;
import java.io.*;

public class Main {
	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 
	private static int dx[]= {0,-1,-1,-1,0,1,1,1};
	private static int dy[]= {-1,-1,0,1,1,1,0,-1}; 
	
	//상어 이동방향은 상좌하우만 (인덱스 계산하기 귀찮아서)
	private static int sx[]= {-1,0,1,0};
	private static int sy[]= {0,-1,0,1}; 
	
	private static ArrayList<Fish> map[][]; 
	private static ArrayList<Fish> fish; 
	private static int[][] smell; //물고기 냄새 (0이면 냄새 x) 
	
	private static int sharkr,sharkc; //상어의 위치 
	private static int S;//상어의 연습횟수 

	static class Fish implements Cloneable{
		int r;
		int c;
		int dir;
		
		public Fish(int r, int c, int dir) {
			this.r= r;
			this.c= c;
			this.dir= dir;
		}
		
		@Override
		public Object clone() throws CloneNotSupportedException{
			return super.clone();
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/*4x4 크기의 map 만들고, 각 부분을 Fish리스트로 초기화 */
		map= new ArrayList[4][4]; 
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				map[i][j]= new ArrayList<Fish>(); 
			}
		}
		smell= new int[4][4];
		fish = new ArrayList<Fish>(); 
		
		/*start input*/
		st= new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());//물고기의 수 
		S = Integer.parseInt(st.nextToken());//상어의 연습 횟수 
		
		while(M-- >0) {
			st=new StringTokenizer(br.readLine(), " ");
			//r,c,dir모두 1부터 시작하기 때문에 1씩 빼주어서 인덱스 맞추기 
			int r= Integer.parseInt(st.nextToken())-1;
			int c= Integer.parseInt(st.nextToken())-1;
			int dir=Integer.parseInt(st.nextToken())-1;
			
			fish.add(new Fish(r,c,dir)); 
			
		}//while
		
		st= new StringTokenizer(br.readLine(), " ");
		sharkr= Integer.parseInt(st.nextToken())-1;
		sharkc= Integer.parseInt(st.nextToken())-1;
		/*finish input*/
		
		simulate(); 
		System.out.println(fish.size());
		
	}//end of main
	private static void simulate() throws CloneNotSupportedException {
		for(int practice=0; practice<S; practice++) {
			//1.현재 물고기를 복제, 이후에 물고기가 다 이동한 map에 뿌리기 
			ArrayList<Fish> copyFish= copy(fish);  
			
			//2.물고기가 이동 , 기존의 값 새로운 위치로 변경하기 
			for(int i=0; i<fish.size(); i++) {
				Fish f= fish.get(i);
				fish.set(i, moveFish(f)); 
//				f= moveFish(f);
			}

			//현재 물고기 상태 map에 추가 
			drawMap(fish); 
			
			//3.상어가 이동 자리 찾기 -3칸 (dfs) 
			eatFish = Integer.MIN_VALUE; //상어가 이동하며 먹는 물고기 갯수 
			visited=new boolean[4][4];
			moveShark(sharkr, sharkc, 0, 0); 
			//상어 최종 이동 위치 result에 담겨있음 
			
			//3-1. 상어실제 이동, 움직인 칸에 있는 물고기 사라지고 물고기 냄새만 남김 
			dieFish(result); 
			
			//4.냄새 지우기 
			smellRemove(); 
			
			//5.복제 성공 
			drawMap(copyFish); 
			
			//전체 fish 리스트 에 복제 된 거 더하기
			reset(); 
//			fish.addAll(copyFish); 
			
		}//one-time practice end 
		
	}//simulate 
	
	/*현재 물고기 복제 > copy리스트*/
	private static ArrayList<Fish> copy(ArrayList<Fish> fish) throws CloneNotSupportedException {
		ArrayList<Fish> copy = new ArrayList<Fish>();
		for(int i=0; i<fish.size(); i++) {
			Fish f = fish.get(i); //각 리스트에 있는 물고기 정보 
			copy.add((Fish) f.clone()); 
		}
		return copy;
	}
	
	/*물고기가 이동*/
	private static Fish moveFish(Fish f) {
		//물고기 현재 위치 
		int r= f.r;
		int c= f.c;
		//물고기 방향 
		int d= f.dir;
			
		//한바퀴 돌기 (8번 돌면 제자리)
		for(int dir=0; dir<8; dir++) {
			int nr = r+dx[d]; 
			int nc = c+dy[d]; 
			//상어가 있는칸, 물고기의 냄새가 있는칸, 격자의 범위를 벗어나는 칸 
			if(isOut(nr,nc) || (nr==sharkr && nc==sharkc) || smell[nr][nc]>0) {
				//회전해서 다시 가능한 곳 찾기 
				if(d==0) d=7;
				else d-=1; 
				continue; 
			}
				
			//이동이 가능한 경우, 기존의 값 변경  
			f=new Fish(nr,nc,d);
			break; 
		}
		return f; 
	}
	
	/*현재 물고기 상태 map 에 추가 */
	private static void drawMap(ArrayList<Fish> fish) {
		for(int i=0; i<fish.size(); i++) {
			Fish f= fish.get(i);
			map[f.r][f.c].add(f); 
		}
	}
	
	static int eatFish; //상어가 먹는 물고기 갯수 
	static boolean[][] visited; //이동하면서 방문체크 (같은 곳에서 물고기 두번 못먹음)
	static int[] moving = new int[3]; //상어의 이동 위치 (중간 과정) 
	static int[] result = new int[3]; //상어의 이동 위치(최종) 
	
	/*상어 이동 자리 찾기-3번*/
	private static void moveShark(int r, int c, int cnt, int fish_sum) {
		if(cnt==3) { 
			if(fish_sum > eatFish) {
				eatFish = fish_sum; //가장 많이 먹을 수 있는 위치로 이동 
				//상어 이동 위치 갱신 (최종 위치 : sharkMoving) 
				for(int i=0; i<3; i++) {
					result[i] = moving[i]; 
				}
			}
			return;
		}
		
		for(int dir=0; dir<4; dir++) {
			//사전순이니까 이동 
			int nr= r+sx[dir];
			int nc= c+sy[dir]; 
			
			if(isOut(nr,nc)) continue; 
			
			moving[cnt]=dir; 
			//이미 물고기 먹은곳 
			if(visited[nr][nc]) 
				moveShark(nr,nc,cnt+1, fish_sum);
			
			//아직 물고기 안먹은 곳 
			else {
				visited[nr][nc]=true;
				moveShark(nr,nc,cnt+1,fish_sum+map[nr][nc].size()); 
				visited[nr][nc]=false;
			}
			
		}
	}
	
	/*상어실제 이동, 움직인 칸에 있는 물고기 사라지고 물고기 냄새만 남김  */
	private static void dieFish(int[] result) {
		for(int i=0; i<result.length; i++) {
			sharkr += sx[result[i]];
			sharkc += sy[result[i]]; 
			
			if(map[sharkr][sharkc].size() > 0) {
				map[sharkr][sharkc].clear(); //격자에 표시되어 있는 물고기 다 죽이고 
				smell[sharkr][sharkc] =3; //물고기 냄새 남기기
			}
		}
	}
	
	/*4. 냄새지우기, 냄새 있는 것들 하나씩 줄이기 결국 0되면 사라짐*/
	private static void smellRemove() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++ ) {
				if(smell[i][j] >0) smell[i][j]--; 
			}
		}
	}
	
	/*복제된거랑, 남아있는 물고기 현재map에 표시되있음 다시 fish에 넣기 */
	private static void reset() {
		fish.clear();
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(map[i][j].size() >0) {
					fish.addAll(map[i][j]); 
					map[i][j].clear();
				}
			}
		}
//		
//		//물고기 표시 
//		for(int i=0; i<fish.size(); i++) {
//			Fish f= fish.get(i);
//			System.out.println(f.r+" "+f.c+" "+f.dir);
//		}
	}
	
	/*격자 밖으로 벗어나는지 확인*/
	private static boolean isOut(int r, int c) {
		return r<0 || r>=4 || c<0 || c>=4; 
	}
}//end of class 
/*
 * */
 
