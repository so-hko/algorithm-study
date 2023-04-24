import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		int n = Integer.parseInt(br.readLine()); 
		int a[]= new int[n];
		int b[]= new int[n];
		int c[]= new int[n];
		int d[]= new int[n]; 
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine(), " "); 
			a[i]= Integer.parseInt(st.nextToken());
			b[i]= Integer.parseInt(st.nextToken());
			c[i]= Integer.parseInt(st.nextToken());
			d[i]= Integer.parseInt(st.nextToken());
		}
		
		//a와 b를 더하고 c와 d를 더하기 
		int[] ab= new int[n*n];
		int[] cd= new int[n*n];
		int index=0; 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				ab[index] = a[i]+b[j]; 
				cd[index++] = c[i]+d[j];
			}
		}
	
		Arrays.sort(ab);
		Arrays.sort(cd);
//
//		for(int i=0; i<ab.length; i++) {
//			System.out.print(ab[i]+" ");
//		}
//		System.out.println();
//		for(int i=0; i<ab.length; i++) {
//			System.out.print(cd[i]+" ");
//		}
//		System.out.println();
		long count=0; //합이 0이 되는 쌍의 갯수 
		for(int i=0; i<ab.length; i++) {
			int find = ab[i] * (-1); //cd에서 찾아야 하는 값 

			int upper = UpperBound(cd, find);
			int lower = LowerBound(cd, find);
			count+=(upper-lower); 

		}
		
		System.out.println(count);
		
	}//end of main

	private static int UpperBound(int[] cd, int find) {
		int left=0; 
		int right=cd.length;
		while(left<right) {
			int mid=(left+right)/2;
			
			if(cd[mid] <= find) left=mid+1;
			else right=mid; 
		}
		return right;
	}

	private static int LowerBound(int [] cd, int find) {
		int left=0;
		int right=cd.length;
		
		while(left<right) {
			int mid=(left+right)/2;
			if(cd[mid] < find) left=mid+1; 
			else right=mid; 
		}
		return right; 
	}
}//end of cl

