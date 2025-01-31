import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea3289 {
    static int[] parents;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            makeSet();
            StringBuilder sb = new StringBuilder();
            for (int m=0; m<M; m++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int aRoot = find(a);
                int bRoot = find(b);
                if (num == 0) {
                    if (aRoot != bRoot) {
                        parents[bRoot] = aRoot;
                    }
                } else {
                    if (aRoot == bRoot) {
                        sb.append("1");
                    } else {
                        sb.append('0');
                    }
                }
            }
            System.out.println("#"+tc+" "+sb.toString());
        } 
    }
    private static void makeSet() {
        parents = new int[N+1];
        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }
    }
    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        parents[a] = find(parents[a]);
        return parents[a];
    }
    
}
