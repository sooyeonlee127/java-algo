import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int INF = (int)21e8;
        int V = sc.nextInt();
        int E = sc.nextInt();
        int[][] MAP = new int[V+1][V+1];
        for (int i=0; i<=V; i++) {
            for (int j=0; j<=V; j++) {
                MAP[i][j] = INF;
            }
        }
        for (int i=0; i<E; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int cost = sc.nextInt();
            MAP[s][e] = cost;
        }
        int answer = INF;
        for (int k=1; k<=V; k++) {
            for (int s=1; s<=V; s++) {
                for (int d=1; d<=V; d++) {
                    if (MAP[s][k] != INF && MAP[k][d] != INF) {
                        MAP[s][d] = Math.min(MAP[s][k] + MAP[k][d], MAP[s][d]);
                    }
                    if (s == d) {
                        answer = Math.min(answer, MAP[s][d]);
                    }
                }
            }
        }
        if (answer == INF) answer = -1;
        System.out.println(answer);
    }
}