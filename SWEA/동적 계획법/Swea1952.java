import java.util.Scanner;

public class Swea1952 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int D = sc.nextInt();
            int M1 = sc.nextInt();
            int M3 = sc.nextInt();
            int Y = sc.nextInt();
            int YEAR = 12;
            int[] month = new int[YEAR+1];
            for (int m=1; m<=YEAR; m++) {
                int num = sc.nextInt();
                month[m] = num;
            }
            for (int i=1; i<YEAR+1; i++) {
                month[i] = month[i-1] + Math.min(month[i]*D, M1);
                if (i>=3) {
                    month[i] = Math.min(month[i], month[i-3] + M3);
                }
                if (i==YEAR) {
                    month[i] = Math.min(month[i], Y);
                }
            }
            System.out.println("#"+tc+" "+month[YEAR]);
        }
    }
}

