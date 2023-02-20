import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static Set<Integer> scoreSet;
    private static int[] scores;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            scoreSet = new HashSet<>();
            br.readLine();
            scores = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            scoreSet.add(0);
            makeScore();

            answer.append("#").append(test).append(" ").append(scoreSet.size()).append("\n");
        }

        System.out.print(answer);
    }

    /**
     * `scoreSet`에 있는 점수들에 현재 점수를 모두 더해 다시 `scoreSet` 추가
     * */
    private static void makeScore() {
        for (int score : scores) {
            Set<Integer> tempScores = new HashSet<>();
            for (int setScore : scoreSet) {
                tempScores.add(setScore + score);
            }
            scoreSet.addAll(tempScores);
        }
    }
}