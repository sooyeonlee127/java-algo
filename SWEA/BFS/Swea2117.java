package BFS;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


public class Swea2117 {
    static int[][] MAP;
    static int N, M, home, answer;
    static int[][] direct = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // MAP 넓이
            M = sc.nextInt(); // 한집당 수익
            answer = 0;
            home = 0; // 전체 집 수
            MAP = new int[N][N];
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    MAP[i][j] = sc.nextInt();
                    if (MAP[i][j]==1) home++;
                }
            }
            for (int k=1; k<=N+2; k++) {
                if (k*k+(k-1)*(k-1) > home*M) break;
                for (int i=0; i<N; i++) {
                    for (int j=0; j<N; j++) {
                        bfs(i,j,k);
                    }
                }
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
    static void bfs(int y, int x, int k) {
        int depth = 0;
        int cnt = 0;
        int[][] visited = new int[N][N];
        Queue<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[]{y,x});
        visited[y][x] = 1;
        while (!q.isEmpty()) {
            int len = q.size();
            if (depth == k) {
                break;
            }
            for (int i=0; i<len; i++) {
                Integer[] now = q.poll();
                if (MAP[now[0]][now[1]] == 1) cnt++;
                for (int j=0; j<4; j++) {
                    int dy = direct[j][0] + now[0];
                    int dx = direct[j][1] + now[1];
                    if (dy < 0 || dy >= N || dx < 0 || dx >= N) continue;
                    if (visited[dy][dx] == 1) continue;
                    visited[dy][dx] = 1;
                    q.add(new Integer[]{dy,dx});
                }
            }
            depth ++;
        }
        int fee = k*k+(k-1)*(k-1);
        int profit = cnt*M;
        if (profit >= fee) {
            answer = Math.max(cnt, answer);
        }
    }

}

