import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Solution {
    static int N;
    static int[] score;
    static Set<Integer> possibleScore;
    //이전까지 만들어진 점수들에 문제의 배점들을 하나씩 더하여 새로운 점수를 만드는 method
    static void calScore() {
        for (int i = 0; i < N; i++) {
            Set<Integer> tmp = new HashSet<>();
            tmp.addAll(possibleScore);
            for (int s : tmp) {
                possibleScore.add(s + score[i]);
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            possibleScore = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            N = Integer.parseInt(br.readLine());
            score = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                score[i] = Integer.parseInt(st.nextToken());
            }
            possibleScore.add(0);
            calScore();
            sb.append("#").append(tc).append(" ").append(possibleScore.size());
            System.out.println(sb);
        }
    }
}