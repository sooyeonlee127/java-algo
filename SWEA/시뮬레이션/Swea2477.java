package 시뮬레이션;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Swea2477 {

    static class Person {
        boolean receipt_Yn, done_Yn;
        int depart_time, receipt_num, oper_num;

        public Person(int depart_time) {
            this.depart_time = depart_time;
            this.receipt_Yn = false;
            this.done_Yn = false;
        }
        void wait_receipt() {
            this.receipt_Yn = true;
        }
        void register_receipt(int num) {
            this.receipt_num = num;
        }
        void register_oper(int num) {
            this.oper_num = num;
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();
            int answer = 0;
            int[] receipt_time = new int[N];
            int[] oper_time = new int[M];
            for (int i=0; i<N; i++) {
                receipt_time[i] = sc.nextInt();
            }
            for (int i=0; i<M; i++) {
                oper_time[i] = sc.nextInt();
            }
            int[][] ing_A = new int[N][2];
            Person[] users = new Person[K];
            for (int i=0; i<K; i++) {
                users[i] = new Person(sc.nextInt());
            }
            Queue<Integer> wait_receipt_lst = new ArrayDeque<>();
            Queue<Integer> wait_oper_lst = new ArrayDeque<>();

            int time = 0;
            for (int t=0; t<1001; t++) {
                // 도착한 사람들 추가
                for (int i=0; i<K; i++) {
                    if (!users[i].receipt_Yn && users[i].depart_time <= t) {
                        users[i].wait_receipt();
                        wait_receipt_lst.add(i);
                    }
                }
                // 접수 대기중인 사람들 추가할 수 있으면 하기
                while (!wait_receipt_lst.isEmpty()) {
                    int len = wait_receipt_lst.size();
                    for (int i=0; i<N; i++) {
                        if (ing_A[i][0] == 0) {
                            ing_A[i][0] = receipt_time[i];
                            int now = wait_receipt_lst.poll();
                            users[now].receipt_num = i;
                            ing_A[i][1] = now;
                        }
                    }
                    if (len == wait_receipt_lst.size()) break;
                }
            }
            System.out.println("#"+tc+answer);
        }
    }
}

