import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int L;
    private static int maxScore;
    private static int[] scores;
    private static int[] calories;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        StringTokenizer st;
        for (int test = 1; test <= tests; test++) {
            answer.append("#" + test + " ");
            maxScore = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            scores = new int[N];
            calories = new int[N];

            for (int index = 0; index < N; index++) {
                st = new StringTokenizer(br.readLine());
                scores[index] = Integer.parseInt(st.nextToken());
                calories[index] = Integer.parseInt(st.nextToken());
            }

            findMaxScore(0, 0, 0);

            answer.append(maxScore + "\n");
        }
        System.out.println(answer);
    }

    /**
     * 정렬된 음식들을 탐색하면서, 제한 칼로리 내에서의 조합을 모두 구하고, 최대 점수를 갱신한다.
     * 특정 음식을 넣거나, 넣지 않는 경우로 나눠 탐색한다.
     *
     * @param index 현재 인덱스
     * @param score 점수
     * @param calorie 칼로리
     * */
    private static void findMaxScore(int index, int score, int calorie) {
        if (calorie > L) {
            return;
        }

        if (index == N) {
            maxScore = Math.max(maxScore, score);
            return;
        }

        findMaxScore(index + 1, score + scores[index], calorie + calories[index]);
        findMaxScore(index + 1, score, calorie);
    }
}