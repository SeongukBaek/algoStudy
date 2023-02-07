import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static Queue<Integer> numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        for (int test = 1; test <= 10; test++) {
            answer.append("#" + test + " ");
            int N = Integer.parseInt(br.readLine());

            numbers = new LinkedList<>();
            Arrays.stream(br.readLine().split(" "))
                    .forEach(d -> numbers.add(Integer.parseInt(d)));

            answer.append(generatePwd() + "\n");
        }
        System.out.println(answer);
    }

    private static String generatePwd() {
        int minus = 1;
        while (true) {
            int number = numbers.poll();
            number -= minus++;

            if (minus == 6) {
                minus = 1;
            }

            if (number <= 0) {
                number = 0;
                numbers.add(number);
                break;
            }

            numbers.add(number);
        }

        return queueToString();
    }

    private static String queueToString() {
        StringBuilder result = new StringBuilder();
        numbers.forEach(n -> result.append(n + " "));
        return result.toString();
    }
}