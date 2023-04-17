import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int test = 0; test < T; test++) {
            isVisited = new boolean[10000];
            st = new StringTokenizer(br.readLine());

            String A = st.nextToken();
            int B = Integer.parseInt(st.nextToken());

            isVisited[Integer.parseInt(A)] = true;

            answer.append(findOperations(convertToFourDigit(A), B)).append("\n");
        }

        System.out.println(answer);
    }

    /**
     * 주어진 문자열을 앞이 0으로 채워진 4자리 숫자로 반환
     * */
    private static StringBuilder convertToFourDigit(String string) {
        return new StringBuilder("0".repeat(4 - string.length()) + string);
    }

    /**
     * from에서 4가지 연산을 수행하면서 to를 만드는 최소한의 명령어 나열을 반환
     * */
    private static String findOperations(StringBuilder from, int to) {
        Queue<StringBuilder[]> numbers = new ArrayDeque<>();
        numbers.add(new StringBuilder[] {from, new StringBuilder()});
        String operations = "";

        while (!numbers.isEmpty()) {
            StringBuilder[] current = numbers.poll();
            int number = Integer.parseInt(current[0].toString());
            StringBuilder operation;

            // 4가지 연산 수행
            // D 연산
            int nextNumber = number * 2;
            if (nextNumber > 9999) {
                nextNumber %= 10000;
            }
            if (!isVisited[nextNumber]) {
                operation = new StringBuilder(current[1]);
                operation.append("D");

                if (nextNumber == to) {
                    operations = operation.toString();
                    break;
                }

                isVisited[nextNumber] = true;

                numbers.add(new StringBuilder[] {convertToFourDigit(String.valueOf(nextNumber)), operation});
            }

            // S 연산
            nextNumber = number - 1;
            if (nextNumber == -1) {
                nextNumber = 9999;
            }
            if (!isVisited[nextNumber]) {
                operation = new StringBuilder(current[1]);
                operation.append("S");

                if (nextNumber == to) {
                    operations = operation.toString();
                    break;
                }

                isVisited[nextNumber] = true;
                numbers.add(new StringBuilder[] {convertToFourDigit(String.valueOf(nextNumber)), operation});
            }

            // L 연산
            StringBuilder newNumber = new StringBuilder(current[0]);
            newNumber.append(newNumber.charAt(0));
            newNumber.deleteCharAt(0);

            nextNumber = Integer.parseInt(newNumber.toString());
            if (!isVisited[nextNumber]) {
                operation = new StringBuilder(current[1]);
                operation.append("L");

                if (nextNumber == to) {
                    operations = operation.toString();
                    break;
                }

                isVisited[nextNumber] = true;
                numbers.add(new StringBuilder[] {convertToFourDigit(newNumber.toString()), operation});
            }

            // R 연산
            newNumber = new StringBuilder(current[0]);
            newNumber.insert(0, newNumber.charAt(3));
            newNumber.deleteCharAt(4);

            nextNumber = Integer.parseInt(newNumber.toString());
            if (!isVisited[nextNumber]) {
                operation = new StringBuilder(current[1]);
                operation.append("R");

                if (nextNumber == to) {
                    operations = operation.toString();
                    break;
                }

                isVisited[nextNumber] = true;
                numbers.add(new StringBuilder[] {convertToFourDigit(newNumber.toString()), operation});
            }
        }

        return operations;
    }
}