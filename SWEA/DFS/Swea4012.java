package DFS;

import java.util.Scanner;

public class Swea4012 {
    static int MIN, N;
    static int[] used;
    static int[][] food;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            food = new int[N][N];
            MIN = (int) 21e5;
            used = new int[N];
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    food[i][j] = sc.nextInt();
                }
            }
            dfs(0, 0);

            System.out.println("#"+tc+" "+MIN);
        }


    }
    static void dfs(int level, int start) {
        if (level == (int) N/2) {
            int sum = 0;
            int sum2 = 0;
            for (int i=0; i<N; i++) {
                if (used[i] == 1) {
                    for (int j=i+1; j<N; j++) {
                        if (used[j] == 1) {
                            sum += food[i][j];
                            sum += food[j][i];
                        }
                    } 
                } else {
                    for (int j=i+1; j<N; j++) {
                        if (used[j] == 0) {
                            sum2 += food[i][j];
                            sum2 += food[j][i];
                        }
                    }
                }
            }
            MIN = Math.min(Math.abs(sum-sum2), MIN);
        }
        for (int i=start; i<N; i++) {
            if (used[i] == 0) {
                used[i] = 1;
                dfs(level+1, i+1);
                used[i] = 0;
            }
        }
    }
}


