import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Integer> q = new ArrayDeque<>();
        int[] MAP = new int[100001];
        int[] visited = new int[100001];
        int N = sc.nextInt();
        int K = sc.nextInt();
        q.offerFirst(N);
        while (!q.isEmpty()) {
            int now = q.pollFirst();
            if (now == K) {
                System.out.println(MAP[now]);
                int [] answer = new int[MAP[now]+1];
                answer[MAP[now]] = now;
                int tmp = now;
                for (int i = MAP[now]-1; i >= 0; i--) {
                    answer[i] = visited[tmp];
                    tmp = visited[tmp];
                }
                for (int a : answer) {
                    System.out.print(a);
                    System.out.print(' ');
                }
                break;
            }
            int[] nextPositions = {now - 1, now + 1, now * 2};
            for (int i : nextPositions) {
                if (i >= 0 && i <= 100000 && MAP[i] == 0) {
                    q.offerLast(i);
                    MAP[i] = MAP[now] + 1;
                    visited[i] = now;
                }
            }
        }
    }
}