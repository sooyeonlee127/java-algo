package 시뮬레이션;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;


public class Swea5644 {
    static int[][] direct = {{0,0},{-1,0},{0,1},{1,0},{0, -1}};
    static int[][][] MAP;
    static int[][] user;
    static int M, N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            M = sc.nextInt();
            N = sc.nextInt();
            user = new int[2][M];
            MAP = new int[11][11][N];
            int[][] station = new int[N][4];
            for (int i=0; i<2; i++) {
                for (int j=0; j<M; j++) {
                    user[i][j] = sc.nextInt();
                }
            }       
            for (int i=0; i<N; i++) {
                for (int j=0; j<4; j++) {
                    station[i][j] = sc.nextInt();
                }
            }
            Arrays.sort(station, (a,b) -> Integer.compare(b[3], a[3]));
            for (int i=0; i<N; i++) {
                bfs(station[i][0], station[i][1], station[i][2], i);
            }
            int[] A = new int[] {1,1};
            int[] B = new int[] {10,10};
            int answer = 0;
            if (check(1,1)) answer += station[check_lst(1,1).get(0)][3];
            if (check(10,10)) answer += station[check_lst(10,10).get(0)][3];
            int[][] used = new int[11][11];
            for (int i=0; i<M; i++) {
                int[] da = direct[user[0][i]];
                int[] db = direct[user[1][i]];
                used[A[0]][A[1]] = 1;
                A[0] += da[0];
                A[1] += da[1];
                B[0] += db[0];
                B[1] += db[1];
                if (A[0] == B[0] && A[1] == B[1]) {
                    if (!check(A[0], A[1])) continue; 
                    ArrayList<Integer> result = check_lst(A[0], A[1]);
                    if (result.size() == 1) {
                        answer += station[result.get(0)][3];
                    } else {
                        answer += station[result.get(0)][3];
                        answer += station[result.get(1)][3];
                    }
                } else {
                    boolean check_a = check(A[0], A[1]);
                    boolean check_b = check(B[0], B[1]);
                    if (check_a && check_b) {
                        ArrayList<Integer> a_result = check_lst(A[0], A[1]);
                        ArrayList<Integer> b_result = check_lst(B[0], B[1]);
                        int MAX = 0;
                        for (Integer a: a_result) {
                            for (Integer b: b_result) {
                                int tmp = station[a][3];
                                if (a != b) tmp += station[b][3];
                                MAX = Math.max(MAX, tmp);
                            }
                        }
                        answer += MAX;
                    } else if (check_a) {
                        ArrayList<Integer> a_result = check_lst(A[0], A[1]);
                        answer += station[a_result.get(0)][3];
                    } else if (check_b) {
                        ArrayList<Integer> b_result = check_lst(B[0], B[1]);
                        answer += station[b_result.get(0)][3];  
                    }
                }
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
    static ArrayList<Integer> check_lst(int x, int y) {
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i=0; i<N; i++) {
            if (MAP[x][y][i] == 1) lst.add(i);
        }
        return lst;
    }
    static boolean check(int x, int y) {
        boolean flag = false;
        for (int i=0; i<N; i++) {
            if (MAP[x][y][i] == 1) flag = true;
        }
        return flag;
    }
    static void bfs(int x, int y, int d, int idx) {
        Queue<Integer[]> q = new ArrayDeque<>();
        int[][] visitied = new int[11][11];
        q.add(new Integer[] {y,x});
        visitied[y][x] = 7;
        int depth = 0;
        MAP[y][x][idx] = 1;
        while (!q.isEmpty()) {
            if (depth == d) return;
            int len = q.size();
            for (int i=0; i<len; i++) {
                Integer[] now = q.poll();
                for (int j=1; j<5;j++) {
                    int dy = direct[j][0]+now[0];
                    int dx = direct[j][1]+now[1];
                    if (dx <1 || dy < 1 || dx >= 11 || dy >= 11) continue;
                    if (visitied[dy][dx]!=0)  continue;
                    visitied[dy][dx] = 1;
                    q.add(new Integer[] {dy,dx});
                    MAP[dy][dx][idx] = 1;
                }
            }
            depth ++;
        }
    }
}

