import java.util.*;
import java.io.*;
/*
 * 가장 많은 점수를 받을려면 .. 
 * 높은 점수 위주로 풀어야 한다.
 * 점수 순으로 내림차순정렬 
 * 점수가 같으면 ? 날짜 내림차순
 * 점수가 높은걸 먼저 풀테니까 해당 마감날짜에 맞춰 최대한 늦게 풀기(그래야 앞에 풀수 있는 날이 많음) */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		int N= Integer.parseInt(br.readLine()); 
		int[][] work = new int[N][2]; //마감일, 과제점수 
		int max_day=0; //마감일 최대 
		
		for(int i=0; i<work.length; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			work[i][0] = Integer.parseInt(st.nextToken());
			work[i][1] = Integer.parseInt(st.nextToken()); 
			max_day= Math.max(max_day, work[i][0]); 
		}
		
		boolean[] isDay= new boolean[max_day+1]; //해당 날짜에 마감한 과제가 있는지 여부 
		
		Arrays.sort(work, (o1,o2)-> {
			if(o2[1]==o1[1]) return o2[0]-o1[0]; 
			return o2[1]-o1[1]; 
		});
		
		int res=0; 
		for(int i=0; i<work.length; i++) {
			int day= work[i][0]; //과제의 마감날짜 
			int score= work[i][1]; //과제 점수 
			
			while(day>0) {
				//마감할 수 있는 날짜가 있으면 (과제의 마감날짜보다 전에 끝내야함) 
				if(!isDay[day]) {
					isDay[day]= true;
					res+=score; 
					break; 
				}
				day--; 
			}
		}
		
		System.out.println(res);
		
	}//end of main 
}//end of class 
