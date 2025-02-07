package DFS;

import java.util.Scanner;


public class Swea4008 {
    static int N, answer, MAX, MIN;
    static int[] operator, num;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        operator = new int[4];
        for (int tc = 1; tc <= T; tc++) {
            MIN = (int) 21e8;
            MAX = (int) -21e8;
            N = sc.nextInt();
            num = new int[N]; 
            for (int i=0; i<4; i++) {
                operator[i] = sc.nextInt();
            }
            for (int i=0; i<N; i++) {
                num[i] = sc.nextInt();
            }
            dfs(0, num[0]);
            System.out.println("#"+tc+" "+(MAX-MIN));
        }
    }
    static void dfs(int level, int total) {
        if (level == N-1) {
            MAX = Math.max(total, MAX);
            MIN = Math.min(total, MIN);
            return;
        }
        for (int i=0; i<4; i++) {
            if (operator[i] > 0) {
                operator[i] --;
                int result = cal(total, num[level+1], i);
                dfs(level+1, result);
                operator[i] ++;
            }
        }
    }
    static int cal(int num1, int num2, int oper) {
        if (oper == 0) {
            return num1 + num2; 
        } else if (oper == 1) {
            return num1 - num2;
        } else if (oper == 2) {
            return num1 * num2;
        } else {
            return (int) num1 / num2;
        }
    } 
}

