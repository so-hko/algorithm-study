import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N= Integer.parseInt(br.readLine());//센서의 갯수 
		int K= Integer.parseInt(br.readLine());//집중국의 갯수 
		
		int[] sensor= new int[N]; 
		st= new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++) {
			sensor[i]= Integer.parseInt(st.nextToken()); 
		}
		
		//센서보다 집중국이 더 많거나 같으면, 집중국 하나가 센서 하나씩 맡으면 됨 
		if(K>=N) {
			System.out.println(0);
			System.exit(0);
		}
		
		
		Arrays.sort(sensor);
		
		int[] diff= new int[N-1]; //각 센서간의 거리 차이 
		for(int i=0; i<diff.length; i++) {
			diff[i] = sensor[i+1]-sensor[i]; 
		}
		
		Arrays.sort(diff);
		int sum=0; 
		for(int i=0; i<diff.length-(K-1); i++) {
			sum+=diff[i];
		}
		System.out.println(sum);
		
		
		
	}//end of main 
}//end of class 
