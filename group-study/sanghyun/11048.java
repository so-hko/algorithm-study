import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] map = new int[N+1][M+1];

        int max = 0;

        for (int i=1; i<N+1; i++) {
            for (int j=1; j<M+1; j++) {
                map[i][j] = scan.nextInt();

                max = Math.max(map[i-1][j-1], Math.max(map[i-1][j], map[i][j-1]));
                map[i][j] += max;
            }
        }

        System.out.println(map[N][M]);
    }


}
