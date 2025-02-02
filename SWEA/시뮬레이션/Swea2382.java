package 시뮬레이션;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Swea2382 {
    static class Micro {
        int y;
        int x;
        int distance;
        int value;
        public Micro(int y, int x, int distance, int value) {
            this.y = y;
            this.x = x;
            this.distance = distance;
            this.value = value;
        }
        public void kill() {
            this.value = Math.round(this.value / 2);
            if (this.value > 0) {
                if (this.distance == 1) this.distance = 2;
                else if(this.distance == 2) this.distance = 1;
                else if (this.distance == 3) this.distance = 4;
                else this.distance = 3;
            }
        }
        public void killCheck() {
            if (y==0 || y == N-1 || x == 0 || x == N-1) {
                kill();
                return;
            }
            return;
        }
        public boolean go() {
            if (this.value <= 0) {
                return false;
            }
            this.y += direct[distance][0];
            this.x += direct[distance][1];
            killCheck();
            if (this.value <= 0) {
                return false;
            }
            return true;
        }
        public void finish() {
            this.value = 0;
        }
    }
    static int[][] MAP;
    static int N;
    static int[][] direct = {{0,0},{-1,0}, {1,0},{0,-1},{0,1}};
    static ArrayList<Integer[]> check;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            MAP = new int[N][N];
            ArrayList<Micro> mircoes = new ArrayList<>();
            for (int i=0; i<K; i++) {
                int Y = sc.nextInt();
                int X = sc.nextInt();
                int V = sc.nextInt();
                int D = sc.nextInt();
                mircoes.add(new Micro(Y, X, D, V));
            }
            for (int i=0; i<M;i++) {
                Map<String, List<Integer>> dict = new HashMap<>();
                int len = mircoes.size();
                for (int j=0; j<len; j++) {
                    Micro a = mircoes.get(j);
                    if (a.go()) {
                        String key = Integer.toString(a.y)+"/"+Integer.toString(a.x);
                        if (dict.containsKey(key)){
                            dict.get(key).add(j);
                        } else {
                            dict.put(key, new ArrayList<>(Collections.singletonList(j)));
                        }
                    }
                }
                Set<String> keys = dict.keySet();
                check = new ArrayList<Integer[]>();
                for (String k : keys) {
                    if (dict.get(k).size() > 1) {
                        int v = 0;
                        int my = 0;
                        int mx = 0;
                        int max = 0;
                        int d = 0;
                        for (int s : dict.get(k)) {
                            Micro a = mircoes.get(s);
                            if (max < a.value) {
                                max = a.value;
                                d = a.distance;
                            }
                            v += a.value;
                            a.finish();
                            my = a.y;
                            mx = a.x;
                        }
                        check.add(new Integer[] {my, mx, d, v});
                    }
                }
                for (Integer[] a: check) {
                    mircoes.add(new Micro(a[0], a[1], a[2], a[3]));
                }
            }
            int answer = 0;
            for (Micro m : mircoes) {
                answer += m.value;
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
}

