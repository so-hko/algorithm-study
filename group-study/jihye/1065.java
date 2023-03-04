package study_17;

import java.util.Scanner;

/*
 * 1~99 까지의 수는 모두 등차수열 : 99이하의 수가 주어졌을 때는 해당 수 출력 
 * 3자리수 : a-b = b-c (=> 2b = a+c ) */

public class Solution_BaekJoon_1065 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int N = sc.nextInt();
		if(N<100) System.out.println(N);
		else {
			int cnt =99 ; 
			for(int i=111; i<=N; i++) {
				if(isOneNum(i+""))cnt++; 
			}
			System.out.println(cnt);
		}
		
	}// end of main 
	
	private static boolean isOneNum(String num) {
		int[] numbers = new int[3]; 
		for(int i=0; i<3; i++) {
			numbers[i] = num.charAt(i)-'0'; 
		}
		// 세자리수의 각자리수가 a-b = b-c , 즉 2b = a+c
		return (numbers[1]*2 == numbers[0] + numbers[2]); 
		
	}
}// end of class 
