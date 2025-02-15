import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean find_num(int n, int[] nums, int N) {
        int start = 0;
        int end = N-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == n) {
                return true;
            } else if (nums[mid] > n) {
                end = mid -1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] nums = new int[N];
        for (int i=0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        int M = sc.nextInt();
        int [] x_nums = new int[M];
        for (int i=0; i < M; i++) {
            x_nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        for (int x : x_nums) {
            boolean answer = find_num(x, nums, N);
            if (answer) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}