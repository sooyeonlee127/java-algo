package 시뮬레이션;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


public class Swea5644 {
    static int[][] direct = {{0,0},{0,-1},{1,0},{0,1},{-1,0}};
    static int L = 11;
    static Spot[][] MAP;
    static class BC {
        int y,x,c,p;
        boolean used;
        public BC(int x, int y, int c, int p) {
            this.y = y;
            this.x = x;
            this.c = c;
            this.p = p;
            this.used = false;
        }

        public void useBC() {
            this.used = true;
        }
    }
    static class Spot {
        boolean one;
        int[] check;
        public Spot() {
            this.one = true;
            this.check = new int[N];
        }
        public void visit(int idx) {
            this.check[idx] = 1;
            int cnt = 0;
            for (int i=0; i<N; i++) {
                if (this.check[i] == 1) {
                    cnt += 1;
                }
            }
            if (cnt > 1) this.one = false;
        }
        public int oneIdx() {
            int idx = 0;
            for (int i=0; i<N; i++) {
                if (this.check[i] == 1) {
                    idx = i;
                    break;
                }
            }
            return idx;
        }
    }
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int M = sc.nextInt();
            N = sc.nextInt();
            int[][] user = new int[2][M];
            MAP = new Spot[L][L];
            for (int i=0; i<2; i++) {
                for (int j=0; j<M; j++) {
                    user[i][j] = sc.nextInt();
                }
            }
            BC[] station = new BC[N];
            for (int i=0; i<N; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                int p = sc.nextInt();
                station[i] = new BC(x,y,c,p);
                bfs(x,y,c,i);
            }
            for (int i=0; i<2; i++) {
                int y = 1;
                int x = 1;
                if (i==1) {
                    y = 10;
                    x = 10;
                }
                if (MAP[x][y] != null) {
                    if (MAP[x][y].one) {
                        station[MAP[x][y].oneIdx()].useBC();
                    }
                }
                for (int j=0; j<M; j++) {
                    int idx = user[i][j];
                    x += direct[idx][0];
                    y += direct[idx][1];
                    if (MAP[x][y] != null) {
                        if (MAP[x][y].one) {
                            station[MAP[x][y].oneIdx()].useBC();
                        }
                    }
                }
            }
            int answer = 0;
            for (int i=0; i<N; i++) {
                if (station[i].used) {
                    answer += station[i].p;
                }
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
    static void bfs(int x, int y, int c, int idx) {
        Queue<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[]{y,x});
        int depth = 0;
        int[][] visited = new int[L][L];
        visited[x][y] = 1;
        if (MAP[x][y] == null) {
            MAP[x][y] = new Spot();
        }
        MAP[x][y].visit(idx);
        while (!q.isEmpty()) {
            int len = q.size();
            if (depth == c) {
                return;
            }
            for (int k=0; k<len; k++) {
                Integer[] now = q.poll();
                for (int i=1; i<=4; i++) {
                    int dx = direct[i][0] + now[0];
                    int dy = direct[i][1] + now[1];
                    if (dy < 1 || dx < 1 || dy >= L || dx >= L) continue;
                    if (visited[dx][dy] == 1) continue;
                    visited[dx][dy] = 1;
                    if (MAP[dx][dy] == null) {
                        MAP[dx][dy] = new Spot();
                    }
                    MAP[dx][dy].visit(idx);
                    q.add(new Integer[]{dx,dy});
                }
            }
            depth ++;
        }
    }
}

