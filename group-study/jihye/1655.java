import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder(); 
		int N= Integer.parseInt(br.readLine()); //정수의 갯수
		
		//중간값보다 더 작은값 (내림차순 정렬)
		PriorityQueue<Integer> small = new PriorityQueue<>((o1,o2)-> o2-o1); 
		//중간값보다 더 큰값 (오름차순 정렬)
		PriorityQueue<Integer> big = new PriorityQueue<>(); 
		int mid = Integer.parseInt(br.readLine()); //첫번째 값 mid로 
		while(--N>0) {		
			sb.append(mid).append("\n"); 
			int num = Integer.parseInt(br.readLine()); //입력받은값
			
			//중간값보다 크거나 같을 경우 
			if(mid<=num) {
				big.add(num);
//				int index= big.size()-1; //방금 넣은 수의 인덱스 
//				if(index>1) heapSort(big, index); 
			}
			else {
				small.add(num); 
			}
			
			//하나 스몰로 보내기 
			if(big.size() - small.size() > 1) {
				small.add(mid);
				mid= big.poll();
			}
			
			else if(small.size() > big.size()) {
				big.add(mid);
				mid=small.poll(); 
			}
		}
		sb.append(mid);

		System.out.println(sb);
	}//end of main 
}//end of class 
