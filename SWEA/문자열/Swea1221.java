package 문자열;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Swea1221 {
    static String[] words = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"
};
    static Map<String, Integer> dict = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<10; i++) {
            dict.put(words[i], i);
        }
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            String tmp = sc.next();
            int N = sc.nextInt();
            int[] arr = new int[N];
            for (int i=0; i<N; i++) {
                arr[i] = dict.get(sc.next());
            }
            Arrays.sort(arr);
            String answer = "";
            for (int i=0; i<N; i++) {
                answer += words[arr[i]];
                if (i!=N-1) answer += " ";
            }
            System.out.println("#"+tc);
            System.out.println(answer);
        }
    }
}

