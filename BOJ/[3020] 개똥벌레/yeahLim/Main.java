import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int n = Integer.parseInt(info[0]);
        int h = Integer.parseInt(info[1]);
        int[] floor = new int[h+1];
        int[] ceil = new int[h+1];

        // 구간 개수 세기
        for (int i = 0; i < n / 2; i++) {
            floor[Integer.parseInt(br.readLine())]++;
            ceil[Integer.parseInt(br.readLine())]++;
        }

        // 누적합
        for (int i = h-1; i > 0; i--) {
            floor[i] += floor[i+1];
            ceil[i] += ceil[i+1];
        }

        int[] bar = new int[h+1];
        int min = n + 1;
        int cnt = 0;


        for (int i = 1; i <= h; i++) {
            bar[i] = floor[i] + ceil[h-i+1];  // 전체 누적합
            if (min > bar[i]) {
                min = bar[i];
                cnt = 1;
            }
            else if (min == bar[i]) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }
}