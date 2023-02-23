import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class Main {
    private static Stack<Character> words;
    private static String boom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        boom = br.readLine();

        int boomLength = boom.length();
        char lastBoomWord = boom.charAt(boomLength - 1);

        int index = 0;
        words = new Stack<>();
        while (index < input.length()) {
            char current = input.charAt(index++);

            // 스택의 크기가 폭발 문자열의 길이 - 1보다 크거나 같은 경우, 폭발 문자열의 마지막 문자와 현재 문자가 같은 경우 문자열로 생성
            if (words.size() >= boomLength - 1 && lastBoomWord == current) {
                if (isSame(words.subList(words.size() - (boomLength - 1), words.size()))) {
                    pop(boomLength - 1);
                    continue;
                }
            }

            words.push(current);
        }

        System.out.println(getAnswer());
    }

    /**
     * 스택의 내용을 순서대로 문자열로 만들어 반환
     * 
     * @return String
     */
    private static String stackToString() {
        StringBuilder result = new StringBuilder();
        for (Character word : words) {
            result.append(word);
        }
        return result.toString();
    }

    /**
     * 리스트의 내용과 폭발 문자열의 내용을 비교해 동일성 여부 반환
     * 
     * @param extractedWords 내용의 동일 여부를 확인할 리스트
     * @return boolean
     */
    private static boolean isSame(List<Character> extractedWords) {
        for (int index = 0; index < extractedWords.size(); index++) {
            if (boom.charAt(index) != extractedWords.get(index)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 주어진 횟수만큼 스택의 내용을 삭제
     * 
     * @param count 삭제할 횟수
     */
    private static void pop(int count) {
        while (count > 0) {
            words.pop();
            count--;
        }
    }

    /**
     * 스택의 내용에 따른 답 반환
     * 
     * @return String
     */
    private static String getAnswer() {
        if (words.empty()) {
            return "FRULA";
        }
        return stackToString();
    }
}