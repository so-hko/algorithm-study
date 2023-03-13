import java.util.*;
/*
 * 좌표들의 중간값을 해보자 
 * (x좌표 중 중간, y 좌표 중 중간 )
 * 버퍼리더로 하니까 5넣으니까 5+" " 일케 읽는다 . scanner 로 
*/public class Main {
	static int[] xloc;
	static int[] yloc;
	public static void main(String[] args) throws Exception  {
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); //고객의 수 
		
		xloc=new int[n]; //고객들의 x좌표 
		yloc=new int[n]; //y좌표
		
		for(int i=0; i<n; i++) {
			xloc[i]=sc.nextInt(); 
			yloc[i]=sc.nextInt(); 
		}
		
		Arrays.sort(xloc);
		Arrays.sort(yloc);
		
		System.out.println(getDist(xloc[n/2], yloc[n/2]));
		
		
	}//end of main 
	private static long getDist(int x, int y) {
		long dis=0;
		for(int i=0; i<xloc.length; i++) {
			dis+=(Math.abs(xloc[i]-x) + Math.abs(yloc[i]-y));
		}
		return dis;
	}
}//end of class 
