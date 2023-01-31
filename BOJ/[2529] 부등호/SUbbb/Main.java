import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static String maxValue;
    private static String minValue;
    private static String inequalitySign;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        inequalitySign = br.readLine().replace(" ", "");

        findNumbers(new ArrayList<>(), new boolean[10]);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    /**
     * 부등호식을 만족하는 숫자 순서를 생성
     *
     * @param 생성되는 숫자를 저장할 List, 각 숫자들이 이미 List에 저장되었는지를 알려줄 boolean[]
     * */
    private static void findNumbers(List<Integer> order, boolean[] isVisited) {
        if (order.size() == k + 1) {
            String result = listToString(order);
            if (maxValue == null) {
                maxValue = result;
            }
            minValue = result;
            return;
        }

        for (int n = 9; n >= 0; n--) {
            if (isVisited[n] ||
                    !order.isEmpty() &&
                            !isSatisfied(order.get(order.size() - 1), n, inequalitySign.charAt(order.size() - 1))) {
                continue;
            }
            isVisited[n] = true;
            order.add(n);
            findNumbers(order, isVisited);
            order.remove(order.size() - 1);
            isVisited[n] = false;
        }
    }

    /**
     * 주어진 숫자들의 부등호 만족 여부를 반환
     *
     * @param 왼쪽 수, 오른쪽 수, 부등호
     * @return boolean
     * */
    private static boolean isSatisfied(int left, int right, char inequality) {
        if ((inequality == '<' && left >= right) ||
                (inequality == '>' && left <= right)) {
            return false;
        }
        return true;
    }

    /**
    * 주어진 List를 String으로 변환하여 반환
     *
    * @param 문자열로 만들 List
    * @return String
    * */
    private static String listToString(List<Integer> order) {
        StringBuilder result = new StringBuilder();
        for (int number : order) {
            result.append(number);
        }
        return result.toString();
    }
}