import java.io.*;
import java.util.*;

public class Solution {
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int t=1; t<=tc; t++) {
            int n = Integer.parseInt(br.readLine());
            set = new HashSet<>();
            set.add(0);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                /* 입력된 점수를 매개변수로 넘겨 점수의 경우의 수 구하기 */
                getScoreCases(Integer.parseInt(st.nextToken()));
            }

            /* 출력 */
            System.out.println("#" + t + " " + set.size());
        }
    }

    static void getScoreCases(int score) {
        Set<Integer> tmp = new HashSet<>();
        tmp.addAll(set);
        for(int s : tmp) {
            set.add(s + score);
        }
    }
}
