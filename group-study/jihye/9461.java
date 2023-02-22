import java.util.*;
import java.io.*;
/*
 * N 이 주어지면 N번 삼각형을 추가했을 때 
 * 나선 중 가장 긴거 리턴 
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		// 1<=N<=100 
    // 100 일 경우, 21억 훌쩍 넘음 long 으로 
		long[] p = new long[101];
		p[1]=p[2]=p[3]=1; 
	
		for(int i=4; i<p.length; i++) {
			p[i] = p[i-2] + p[i-3]; 
		}
		
		int tc= sc.nextInt(); // 테스트 케이스 갯수 
		while(tc-- > 0) {
			int N= sc.nextInt();
			System.out.println(p[N]);
		}	
	}// end of main 
}// end of class 
