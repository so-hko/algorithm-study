import java.util.*;
import java.io.*;

public class Main {
	static int N,M,H; 
	static int tomato[][][];
	static Queue<Tomato> start= new LinkedList<>(); 
	
	static class Tomato{
		int h; int x; int y;
		public Tomato(int h, int x, int y) {
			this.h=h;
			this.x=x;
			this.y=y; 
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		 
		st = new StringTokenizer(br.readLine(), " "); 
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		tomato= new int[H][N][M]; 
		
		for(int a=0; a<H; a++) {
			for(int b=0; b<N; b++) {
				st= new StringTokenizer(br.readLine(), " "); 
				for(int c=0; c<M; c++) {
					tomato[a][b][c]= Integer.parseInt(st.nextToken());
					if(tomato[a][b][c]==1) {
						start.add(new Tomato(a, b, c)); 
					}
				}
			}
		}
		System.out.println(riped());		
	}//end of main 
	
	private static int dx[]= {-1,1,0,0,0,0};
	private static int dy[]= {0,0,-1,1,0,0};
	private static int dz[]= {0,0,0,0,-1,1}; 
	
	//토마토 익기 
	private static int riped() {
		int time=0; 
		while(true) {
//			System.out.println(time+"초");
//			for(int a=0; a<H; a++) {
//				for(int b=0; b<N; b++) {
//					for(int c=0; c<M; c++){
//						System.out.print(tomato[a][b][c]+" ");
//					}
//					System.out.println();
//				}
//			}
//			System.out.println();
			if(isEnd()) return time; 
			int size= start.size(); 
			if(size ==0 ) break; 
			for(int i=0; i<size; i++) {
				Tomato t= start.poll(); 
				int h= t.h;
				int x= t.x;
				int y= t.y;
				
				for(int dir= 0; dir<6; dir++) {
					int nh= h+dz[dir];
					int nx= x+dx[dir];
					int ny= y+dy[dir];
					
					if(isOut(nh, nx, ny) || tomato[nh][nx][ny] != 0) continue; 
					
					tomato[nh][nx][ny] = 1;
					start.add(new Tomato(nh, nx, ny)); 
				}
				
			}
			time++;
		}
		return -1; 
	}
	private static boolean isEnd() {
		for(int a=0; a<H; a++) {
			for(int b=0; b<N; b++) {
				for(int c=0; c<M; c++) {
					if(tomato[a][b][c] == 0) return false; //안익은 토마토 있음 
				}
			}
		}
		return true; 
	}
	
	private static boolean isOut(int h ,int x, int y) {
		return h<0 || h>=H || x<0 || x>=N || y<0 || y>=M; 
	}
	
}//end of class 
