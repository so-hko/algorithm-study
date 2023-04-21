import java.util.*;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node>{
		int node_num;
		int weight; 
		
		public Node(int node_num, int weight) {
			this.node_num = node_num;
			this.weight = weight; 
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight; 
		}
		
	}
	
	static int map[][]; 
	static int v ;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " "); 
		v= Integer.parseInt(st.nextToken()); //정점의 갯수
		int e= Integer.parseInt(st.nextToken()); //간선의 갯수
		int p= Integer.parseInt(st.nextToken()); //건우의 위치 
		
		
		
		//간선 연결 정보 구현 
		map = new int[v][v];
		while(e-->0) {
			st= new StringTokenizer(br.readLine(), " "); 
			int a= Integer.parseInt(st.nextToken())-1; 
			int b= Integer.parseInt(st.nextToken())-1;
			int c= Integer.parseInt(st.nextToken()); 
			map[a][b]=map[b][a]=c;
		}
		int masan = dijkstra(0, v-1); //출발지 → 마산 (민준) 
		int gunwoo = dijkstra(0, p-1); //출발지 → 건우 
		int gunwoo_masan = dijkstra(p-1, v-1); //건우 → 마산
//		System.out.println(masan+" "+gunwoo +" "+gunwoo_masan);
		if(masan == gunwoo+gunwoo_masan) System.out.println("SAVE HIM");
		else System.out.println("GOOD BYE");

	}//end of main 

	/*다익스트라 구현하기*/
	private static int dijkstra(int start, int end) {
		int dis[] = new int[v]; // 출발지로부터 도착지까지의 최단 거리 구하기 
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0; 
		
		boolean check[] = new boolean[v]; //해당 정점을 거쳐가는 경로 탐색했는지 확인 여부 
		PriorityQueue<Node> que = new PriorityQueue<>(); 
		
		que.add(new Node(start, 0)); 
		while(!que.isEmpty()) {
			Node now= que.poll(); 
			int num = now.node_num;
			int weight = now.weight; 
			
			if(check[num]) continue; 
			check[num] = true;
			
			for(int i=0; i<v; i++) {
				//now 정점과 인접해 있는 정점
				if(map[num][i]!=0) {
					//새로운 값으로 갱신되면 , 
					if(dis[i] > weight+map[num][i]) {
						dis[i] = weight+map[num][i];
						que.add(new Node(i, dis[i])); 
					}
				}
			}
		}
		return dis[end]; 
	}
}//end of class
