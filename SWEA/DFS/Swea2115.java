package DFS;

import java.util.Arrays;
import java.util.Scanner;

public class Swea2115 {
    static int N, M, C, MAX;
    static int[][] MAP;
    static int[] arr, used;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();
            MAP = new int[N][N];
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    MAP[i][j] = sc.nextInt();
                }
            }
            int[] result = new int[N];
            for (int i=0; i<N; i++) {
                MAX = 0;
                for (int j=0; j<=N-M; j++) {
                    get(j,i);
                }
                result[i] = MAX;
            }
            Arrays.sort(result);
            int answer = result[N-2] + result[N-1];
            System.out.println("#"+tc+" "+answer);
        }
    }
    static void get(int start, int y) {
        arr = new int[M];
        used = new int[M];
        for (int i=0; i<M; i++) {
            arr[i] = MAP[y][i+start];
        }
        dfs(0,0, 0);
    }
    static void dfs(int level, int total, int totalpow) {
        if (total <= C) {
            MAX = Math.max(totalpow, MAX);
        }
        if (level == M) {
            return;
        }
        for (int i=0; i<M; i++) {
            if (used[i] == 1) continue;
            used[i] = 1;
            dfs(level+1, total+arr[i], totalpow+(int)Math.pow(arr[i],2));
            used[i] = 0;
        }
        
    }
}

