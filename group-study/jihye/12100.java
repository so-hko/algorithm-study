package study_17;

import java.io.*;
import java.util.*;

public class Solution_BaekJoon_12100 {
	private static int N;
	private static int[][] game; 
	private static int max= Integer.MIN_VALUE; 
	private static Queue<Integer> que=new LinkedList<>(); 
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N= Integer.parseInt(br.readLine());
		game= new int[N][N]; 
		
		for(int i=0; i<N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				game[i][j]= Integer.parseInt(st.nextToken()); 
			}
		}//input 
		
		
		startGame(0); 
	
		System.out.println(max);
	}//end of main
	
	private static void startGame(int count) { //count :몇번 옮겼는지, 
		int copy[][] = new int[N][N]; 
		copyMap(game, copy);
		
		if(count == 5) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					max = Math.max(game[i][j], max); 
				}
			}
			return; 
		}
		
		for(int dir=0; dir<4; dir++) {
			if(dir==0) {
				goRight();
			}else if(dir==1) {
				goLeft();
			}
			else if(dir==2) {
				goUp();
			}else {
				goDown(); 
			}
			
			startGame(count+1); 
			copyMap(copy, game); 
				
		}
		
		
	}//end of startgame
	
	/*다 오른쪽으로 밀기 */
	private static void goRight() {
		for(int x=0; x<N; x++) {
			//일단 큐에 오른쪽부터 다 넣어줘 (0아닐경우) 
			for(int y=N-1; y>=0; y--) {
				if(game[x][y]!=0) {
					que.add(game[x][y]); 
					game[x][y] =0; 
				}
			}
			
			int y=N-1;
			//오른쪽으로 옮긴거 next에 저장 
			while(!que.isEmpty()) {
				int now = que.poll();
				if(!que.isEmpty() && now==que.peek()) {
					now+=que.poll();
				}
				game[x][y--] = now; 
			}
			
		}
	}//end of goRight
	
	/*다 왼쪽으로 밀기 */
	private static void goLeft() {
		for(int x=0; x<N; x++) {
			//일단 큐에 왼쪽부터 다 넣어줘 (0아닐경우) 
			for(int y=0; y<N; y++) {
				if(game[x][y]!=0) {
					que.add(game[x][y]); 
					game[x][y] =0; 
				}
			}
			
			int y=0;
			//왼쪽으로 옮긴거 next에 저장 
			while(!que.isEmpty()) {
				int now = que.poll();
				if(!que.isEmpty() && now==que.peek()) {
					now+=que.poll();
				}
				game[x][y++] = now; 
			}
			
		}
	}//end of goLeft
	
	/*다 위쪽으로 밀기 */
	private static void goUp() {
		for(int y=0; y<N; y++) {
			//일단 큐에 위부터 다 넣어줘 (0아닐경우) 
			for(int x=0; x<N; x++) {
				if(game[x][y]!=0) {
					que.add(game[x][y]); 
					game[x][y] =0; 
				}
			}
			
			int x=0;
			//위으로 옮긴거 next에 저장 
			while(!que.isEmpty()) {
				int now = que.poll();
				if(!que.isEmpty() && now==que.peek()) {
					now+=que.poll();
				}
				game[x++][y] = now; 
			}
			
		} 
	}//end of goUp
	
	/*다 아래쪽으로 밀기 */
	private static void goDown() {
		for(int y=0; y<N; y++) {
			//일단 큐에 아래부터 다 넣어줘 (0아닐경우) 
			for(int x=N-1; x>=0; x--) {
				if(game[x][y]!=0) {
					que.add(game[x][y]); 
					game[x][y] =0; 
				}
			}
			
			int x=N-1;
			//아래으로 옮긴거 next에 저장 
			while(!que.isEmpty()) {
				int now = que.poll();
				if(!que.isEmpty() && now==que.peek()) {
					now+=que.poll();
				}
				game[x--][y] = now; 
			}
			
		}
	}//end of goUp
	
	// a배열값을 b 에복사 
	private static void copyMap(int[][] a , int [][] b) {
		for(int i=0; i<N; i++) {
			b[i] = a[i].clone();
		}
	}
}
