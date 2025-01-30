import java.util.HashSet;
import java.util.Scanner;

public class Swea7465 {
     static int[] p; // 부모의 배열 = 트리
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc=1; tc<=T; tc++) {
            N = sc.nextInt(); // 노드의 수
            M = sc.nextInt(); // 관계 수
            makeSet();
            for (int i=0; i<M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int aRoot = find(a);
                int bRoot = find(b);
                if (aRoot != bRoot) {
                    p[bRoot] = aRoot; 
                }
            }
             HashSet<Integer> set = new HashSet<>();
             for (int i=1; i<=N; i++) {
                set.add(find(i));
             }
             System.out.println("#"+tc+" "+ set.size());
        }   
    }

        static int find(int a) {
            if (p[a] == a) {
                return a;
            }
            return find(p[a]);
        }
        private static void makeSet() {
            p = new int[N+1];
            for (int i=1; i<=N; i++) 
                p[i] = i;
    }
}
