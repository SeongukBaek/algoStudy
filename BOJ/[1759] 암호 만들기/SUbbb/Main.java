import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static final Set<String> vowelSet = new HashSet<>(Arrays.asList("a", "e", "i", "o", "u"));
    private static final int LEAST_VOWEL_COUNT = 1;
    private static final int LEAST_CONSONANT_COUNT = 2;
    private static int L;
    private static int C;
    private static List<String> words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        words = Arrays.stream(br.readLine().split(" ")).sorted().collect(Collectors.toList());

        makePossiblePasswords(new StringBuilder(), new boolean[C], 0, 0, 0);
    }

    private static void makePossiblePasswords(StringBuilder password, boolean[] isVisited, int vowelCnt, int consonantCnt, int now) {
        if (password.length() == L && vowelCnt >= LEAST_VOWEL_COUNT && consonantCnt >= LEAST_CONSONANT_COUNT) {
            print(password);
            return;
        }

        for (int index = now; index < C; index++) {
            if (isVisited[index]) {
                continue;
            }

            String current = words.get(index);
            password.append(current);
            isVisited[index] = true;
            if (vowelSet.contains(current)) {
                makePossiblePasswords(password, isVisited, vowelCnt + 1, consonantCnt, index + 1);
            } else {
                makePossiblePasswords(password, isVisited, vowelCnt, consonantCnt + 1, index + 1);
            }
            isVisited[index] = false;
            password.deleteCharAt(password.length() - 1);
        }
    }

    private static void print(StringBuilder password) {
        System.out.println(password);
    }
}