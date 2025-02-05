package BFS;

import java.util.ArrayList;
import java.util.Scanner;

public class Swea1949 {
    static int N;
    static class Spot {
        int y, x, height, cnt, result;
        int[][] used;
        public Spot(int y, int x, int height, int cnt, int[][] used, int result) {
            this.y = y;
            this.x = x;
            this.height = height;
            this.cnt = cnt;
            this.used = used;
            this.used[y][x] = result;
            this.result = result;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            int K = sc.nextInt();
            int [][] MAP = new int[N][N];
            int max = 0;
            ArrayList<Spot> lst = new ArrayList<>();

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    int n = sc.nextInt();
                    MAP[i][j] = n;
                    // 봉우리 저장
                    if (n>max) {
                        max = n;
                        lst = new ArrayList<>();
                        int[][] used = new int[N][N];
                        lst.add(new Spot(i,j,n,1,used,1));
                    } else if (n == max) {
                        int[][] used = new int[N][N];
                        lst.add(new Spot(i,j,n,1,used, 1));
                    }
                }
            }
            int[][] direct = {{-1,0},{1,0},{0,-1},{0,1}};
            int answer = 0;
            while (!lst.isEmpty()) {
                Spot now = lst.remove(0);
                answer = Math.max(answer, now.result);
                for (int i=0; i<4; i++) {
                    int dy = direct[i][0] + now.y;
                    int dx = direct[i][1] + now.x;
                    if (dy >= 0 && dy < N && dx >= 0 && dx < N && now.used[dy][dx] == 0) {
                        if (MAP[dy][dx] < now.height) {
                            int[][] visited = new int[N][N];
                            for (int q=0; q<N; q++) {
                                visited[q] = now.used[q].clone();
                            }
                            lst.add(new Spot(dy,dx,MAP[dy][dx], now.cnt, visited, now.result+1));
                        } else if (now.cnt > 0 && now.height > MAP[dy][dx] - K) {
                            int[][] visited = new int[N][N];
                            for (int q=0; q<N; q++) {
                                visited[q] = now.used[q].clone();
                            }
                            lst.add(new Spot(dy,dx,now.height-1, 0, visited, now.result+1));
                        }
                    }
                }     
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
}

