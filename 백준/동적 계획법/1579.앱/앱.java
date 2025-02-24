import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int [] bytes = new int[(N+1)];
        int [] costs = new int[(N+1)];
        for (int i=1; i<=N; i++) {
            bytes[i] = sc.nextInt();
        }
        for (int i=1; i<=N; i++) {
            costs[i] = sc.nextInt();
        }
        int totalCosts = Arrays.stream(costs).sum();
        int result = totalCosts;
        int [][] dp = new int[N+1][totalCosts+1];
        for (int i=1; i<=N; i++) {
            int curr_cost = costs[i]; // 현재 앱 끄는 비용
            int curr_byte = bytes[i]; // 현재 앱 끄면 얻는 크기
            for (int use_cost = 0; use_cost<=totalCosts; use_cost++) { // 사용가능한 비용
                if (use_cost < curr_cost) {
                    dp[i][use_cost] = dp[i-1][use_cost];
                } else {
                    dp[i][use_cost] = Math.max(dp[i-1][use_cost-curr_cost]+curr_byte, dp[i-1][use_cost]);
                }
                if (dp[i][use_cost] >= M) {
                    result = Math.min(result, use_cost);
                }
            }
        }
        System.out.println(result);
    }
}