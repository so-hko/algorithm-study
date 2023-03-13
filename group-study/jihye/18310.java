import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int N= sc.nextInt();
		int house[] = new int[N];
		
		for(int i=0; i<house.length; i++) {
			house[i]= sc.nextInt();
		}
		
		Arrays.sort(house);
		int mid = (N%2==0)?house[N/2-1] : house[N/2]; 
		
		System.out.println(mid);
		
	}//end of main 
}//end of class
