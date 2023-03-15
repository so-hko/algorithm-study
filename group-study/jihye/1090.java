import java.util.*;
import java.io.*;

public class Main{
	private static List<int[]> list; 
	private static int min[]; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		int N= sc.nextInt(); //체커의 갯수 
		min = new int[N]; //체커가 같은 칸에 모일떄의 최소 갯수 
		
		Arrays.fill(min, Integer.MAX_VALUE);
		
		list= new ArrayList<>(); 
		while(N-->0) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			
			list.add(new int[] {x,y}); 
		}
		
		/*
		 * 범위 내에 있는 점들에 대해서 각 좌표까지의 거리 
		 * 가장 가까운 n(1~4)개의 합 */
		for(int i=0; i<list.size(); i++) {
			int x = list.get(i)[0];
			for(int j=0; j<list.size(); j++) {
				int y = list.get(j)[1];
				
				checkDis(x,y); 
			}
		}
		
		//1개가 모이는건 0 (자연수니까 무조건 1이상) 
		min[0]=0; 
		System.out.print(min[0]);

		for(int i=1; i<min.length; i++) {
			System.out.print(" "+min[i]);
		}
		
		
	}//end of main
	
	/*
	 * (x,y)에서 각 좌표까지의 거리를 구해서, 가장 작은거 n개 더하기 */
	private static void checkDis(int x, int y) {
		PriorityQueue<Integer> que= new PriorityQueue<>();
		
		for(int i=0; i<list.size(); i++) {
			//주어져있었던 4개의 좌표들 
			int lx = list.get(i)[0];
			int ly = list.get(i)[1]; 
			
			que.add(calDis(x,y,lx,ly)); 
		}
		int sum = que.poll();
		int index =1;
		while(!que.isEmpty()) {
			sum+=que.poll(); 
			if(sum < min[index]) min[index] = sum;
			index++;
		}
	
	}//end of checkDis
	
	/*
	 * 현재 탐색 중인 점으로부터 각 좌표까지의 거리 계산 */
	private static int calDis(int x, int y, int lx, int ly) {
		return Math.abs(x-lx) + Math.abs(y-ly); 
	}
	
}//end of class 
