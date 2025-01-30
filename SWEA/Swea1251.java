import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Swea1251 {
    static int[] parents;
    static int N;

    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;
        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight; 
        }
        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); 
        for (int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][N+1];
            for (int i=0; i<2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=1; j<=N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 리스트 생성
            ArrayList<Edge> list = new ArrayList<>();
            for (int i=1; i<=N; i++) {
                for (int j=i+1; j<=N; j++) {
                    double distance = Math.pow(arr[0][i]-arr[0][j],2) + Math.pow(arr[1][i]-arr[1][j],2);
                    list.add(new Edge(i,j,distance));
                }
            }
            Collections.sort(list);
            parents = new int[N+1];
            makeSet();
            int cnt = 0;
            double result = 0;
            double E = Double.parseDouble(br.readLine());
            while (cnt < N-1) {
                Edge tmp = list.remove(0);
                if (union(tmp.from, tmp.to)) {
                    cnt += 1;
                    result += E * tmp.weight;
                }
            }
            System.out.println("#"+tc+" "+Math.round(result));
        }
    }
    static void makeSet() {
        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }
    }
    static int find(int a) {
        if (a != parents[a]) {
            parents[a] = find(parents[a]);
        }
        return parents[a];
    }
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;
            return true;
        }
        return false;
    }
}