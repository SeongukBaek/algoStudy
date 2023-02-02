import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_HOUSE_COUNT = 2;
    private static final int ONE_DAY = 1440;
    private static final Queue<Integer> snows = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            snows.add(Integer.parseInt(st.nextToken()));
        }

        if (snows.peek() > ONE_DAY) {
            System.out.println(-1);
            return;
        }

        int result = clearAllSnows();

        System.out.println(result > ONE_DAY ? -1 : result);
    }

    /**
     * 치워야 하는 눈을 저장한 큐가 빌 때까지 눈을 제거하는 함수
     * @return int
     * */
    private static int clearAllSnows() {
        int minute = 0;
        while (!snows.isEmpty()) {
            minute++;
            snows.addAll(cleanTwoHouses());
        }
        return minute;
    }

    /**
     * 2집에 대해 눈을 치운다.
     * 만약 눈이 저장된 큐가 비었다면 종료
     * @return List
     * */
    private static List<Integer> cleanTwoHouses() {
        List<Integer> temp = new ArrayList<>();
        for (int index = 0; index < MAX_HOUSE_COUNT; index++) {
            if (snows.isEmpty()) {
                break;
            }
            int snow = snows.poll() - 1;
            if (snow == 0) {
                continue;
            }
            temp.add(snow);
        }
        return temp;
    }
}