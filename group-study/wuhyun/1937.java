import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] bamboo, check, move=new int[][] {{-1,0},{0,-1},{0,1},{1,0}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int max=1, distance;
		
		n=Integer.parseInt(br.readLine());
		bamboo=new int[n][n];
		check=new int[n][n];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				bamboo[i][j]=Integer.parseInt(st.nextToken());
				check[i][j]=-1;
			}
		}
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) {
				distance=check[i][j]>-1?check[i][j]:simulate(i,j,1);
				max=distance>max?distance:max;
			}
		
		System.out.println(max);
	}
	
	static int simulate(int y,int x,int moved) {
		int max=moved;
		int[] next=new int[4];
		
		for(int i=0;i<4;i++) {
			if(y+move[i][0]>=0 && y+move[i][0]<n && x+move[i][1]>=0 && x+move[i][1]<n && bamboo[y+move[i][0]][x+move[i][1]]>bamboo[y][x])
				next[i]=check[y+move[i][0]][x+move[i][1]]>-1?moved+check[y+move[i][0]][x+move[i][1]]+1:simulate(y+move[i][0],x+move[i][1],moved+1);
				max=next[i]>max?next[i]:max;
		}
		
		check[y][x]=max-moved;
		return max;
	}
}