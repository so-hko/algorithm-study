import java.util.Scanner;

public class Main {

    public static Long[] arr = new Long[101];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i=0; i<arr.length; i++) {
            arr[i] = -1L;
        }
        arr[0] = 0L;
        arr[1] = arr[2] = arr[3] = 1L;
        int T = scan.nextInt();
        for (int i=0; i<T; i++) {
            int N = scan.nextInt();
            System.out.println(padovan(N));
        }

    }

    public static Long padovan(int N) {
        if (arr[N] == -1) {
            arr[N] = padovan(N-2) + padovan(N-3);
        }
        return arr[N];
    }

}
