import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        for (int test = 1; test <= 10; test++) {
            answer.append("#").append(test).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            answer.append(removePairs(st.nextToken())).append("\n");
        }
        System.out.println(answer);
    }

    private static String removePairs(String word) {
        Stack<Character> words = new Stack<>();
        for (int index = 0; index < word.length(); index++) {
            char current = word.charAt(index);

            if (!words.empty() && words.peek() == current) {
                words.pop();
                continue;
            }
            words.push(current);
        }
        return stackToString(words);
    }

    private static String stackToString(Stack<Character> words) {
        StringBuilder result = new StringBuilder();
        for (Character current : words) {
            result.append(current);
        }
        return result.toString();
    }
}