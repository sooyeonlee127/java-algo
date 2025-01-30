import java.util.Scanner;

public class Swea4013 {

    static class Magnet {
        int[] arr;
        int head; // 빨간색 화살표 위치

        Magnet() {
            arr = new int[8];
            head = 0;
        }
        public void turn(int d) {
            if (d == -1) {
                head += 1;
            } else {
                head -= 1;
                if (head < 0) {
                    head += 8; // 마이너스 되는 경우 방지
                }
            }
        }
        public int left() {
            return arr[(head+6)%8]; // 좌측 붙어있는 날 자성
        }
        public int right() {
            return arr[(head+2)%8]; // 우측 붙어있는 날 자성
        }

    }
    static Magnet[] m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t=1; t<=T; t++) {
            int K = sc.nextInt();
            m = new Magnet[5];
            for (int i=1; i <= 4; i++) {
                m[i] = new Magnet();
                for (int j=0; j<8; j++) {
                    m[i].arr[j] = sc.nextInt();
                }
            }

            for (int i=0; i<K; i++) {
                int idx = sc.nextInt();
                int d = sc.nextInt();
                leftCheck(d,idx); // 기준 자석 왼쪽으로 회전할 자석 있는지 확인
                rightCheck(d,idx); // 기준 자석 오른쪽으로 회전할 자석 있는지 확인
                m[idx].turn(d); // 기준 자석 회전
            }
            int answer = 0;
            for (int i=1; i<5; i++) {
                if (m[i].arr[m[i].head % 8] == 1) { // 점수 계산
                    answer += Math.pow(2, i-1);
                }
            }
            System.out.println("#"+t+" "+answer);
        }
    }
    static void leftCheck(int d, int idx) {
        int[] check = new int[8];
        for (int i=idx-1; i>0; i--) {
            d *= (-1);
            if (m[i].right() != m[i+1].left()) {
                check[i] = d;
            } else {
                break;
            }
        }
        for (int i=1; i<=4;i++) {
            if (check[i] != 0) {
                m[i].turn(check[i]);
            }
        }
    }
    static void rightCheck(int d, int idx) {
        int[] check = new int[8];
        for (int i=idx+1; i<5; i++) {
            d *= (-1);
            if (m[i].left() != m[i-1].right()) {
                check[i] = d;
            } else {
                break;
            }
        }
        for (int i=1; i<5;i++) {
            if (check[i] != 0) {
                m[i].turn(check[i]);
            }
        }
    }
}

