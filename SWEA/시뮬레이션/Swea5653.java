package 시뮬레이션;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Swea5653 {
    static int N,M;
    static int[][] direct = {{-1,0}, {1,0}, {0,-1},{0,1}};
    static int[][] MAP;
    static class Cell {
        boolean active, death;
        int wait_time, active_time, y,x, X;
        public Cell(int X, int a, int b) {
            this.active = false;
            this.death = false;
            this.wait_time = X;
            this.X = X;
            this.active_time = X;
            this.y = a;
            this.x = b;
        }
        public ArrayList<String> go() {
            ArrayList<String> direct_lst = new ArrayList<>();
            for (int i=0; i<4; i++) {
                int dy = y + direct[i][0];
                int dx = x + direct[i][1];
                if (MAP[dy][dx] == 0) {
                direct_lst.add(dy+"/"+dx);
                }
            }
            return direct_lst;
        }
        public void time() {
            if (!this.active) {
                this.wait_time -= 1;
                if (this.wait_time <= 0) {
                    this.active = true;
                }
            } else {
                this.active_time -= 1;
                if (this.active_time <= 0) {
                    this.death = true;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            int K = sc.nextInt();
            MAP = new int[N+2*K][M+2*K];
            ArrayList<Cell> lst = new ArrayList<>();
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    int X = sc.nextInt();
                    if (X != 0) {
                        lst.add(new Cell(X,i+K,j+K));
                        MAP[i+K][j+K] = X;
                    }
                }
            }
            for (int k=0; k<K; k++) {
                Map<String, Integer> newCell = new HashMap<>();
                for (Cell c: lst) {
                    if (c.death) continue;
                    if (c.active) {
                        ArrayList<String> tmp = c.go();
                        c.time();
                        for (String t: tmp) {
                            if (newCell.containsKey(t)) {
                                if (newCell.get(t) < c.X) {
                                    newCell.replace(t,c.X);
                                }
                            } else {
                                newCell.put(t, c.X);
                            }
                        }
                    } else {
                        c.time();
                    }
                }
                Set<String> keys = newCell.keySet();
                for (String key: keys) {
                    String[] yx = key.split("/");
                    int a = Integer.parseInt(yx[0]);
                    int b = Integer.parseInt(yx[1]);
                    int X = newCell.get(key);
                    if (MAP[a][b] == 0) {
                        lst.add(new Cell(X, a, b));
                        MAP[a][b] = X;
                    } 
                }
            }
            int answer = 0;
            for (Cell c: lst) {
                if (!c.death) {
                    answer += 1;
                }
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
}


