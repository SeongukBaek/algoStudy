import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static BufferedReader br;
    private static List<StringBuilder> gears;
    private static final int GEARS = 4;
    private static boolean[] hasRotated;

    public static void main(String[] args) throws IOException {
        // 톱니 정보 저장
        saveGears();

        // 회전 수행
        rotateGears();

        // 톱니 상태 확인 후 출력
        System.out.println(computePoint());
    }

    private static void saveGears() throws IOException {
        gears = new ArrayList<>();
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int index = 0; index < GEARS; index++) {
            gears.add(new StringBuilder(br.readLine()));
        }
    }

    private static void rotateGears() throws IOException {
        int rotates = Integer.parseInt(br.readLine());

        for (int rotate = 0; rotate < rotates; rotate++) {
            hasRotated = new boolean[GEARS];
            String[] info = br.readLine().split(" ");
            int gear = Integer.parseInt(info[0]) - 1;
            boolean isClockwise = Integer.parseInt(info[1]) == 1;

            rotateGear(gear, isClockwise);
        }
    }

    /**
     * gear는 1 ~ 4까지
     * */
    private static void rotateGear(int gear, boolean isClockwise) {
        boolean oppositeDirection = !isClockwise;
        hasRotated[gear] = true;

        // 왼쪽 확인, 이미 회전한 톱니바퀴가 아닌 경우
        if (gear > 0 && !hasRotated[gear - 1]) {
            // 왼쪽 톱니바퀴의 2번째 값과 현재 톱니바퀴의 6번째 값을 비교
            // 만약 다르다면 왼쪽 톱니바퀴를 반대 방향으로 회전
            if (gears.get(gear - 1).charAt(2) != gears.get(gear).charAt(6)) {
                rotateGear(gear - 1, oppositeDirection);
            }
        }

        // 오른쪽 확인, 이미 회전한 톱니바퀴가 아닌 경우
        if (gear < 3 && !hasRotated[gear + 1]) {
            if (gears.get(gear).charAt(2) != gears.get(gear + 1).charAt(6)) {
                rotateGear(gear + 1, oppositeDirection);
            }
        }

        StringBuilder directions = gears.get(gear);
        // 톱니 회전
        if (isClockwise) {
            // 시계방향이면 맨 뒤 요소를 빼서 맨 앞으로
            char end = directions.charAt(directions.length() - 1);
            directions.deleteCharAt(directions.length() - 1);
            directions.insert(0, end);
        } else {
            // 반시계방향이면 맨 앞 요소를 빼서 맨 뒤로
            char first = directions.charAt(0);
            directions.deleteCharAt(0);
            directions.append(first);
        }
    }

    private static int computePoint() {
        int point = 0;

        for (int score = 0; score < 4; score++) {
            if (gears.get(score).charAt(0) == '1') {
                point += (int) Math.pow(2, score);
            }
        }

        return point;
    }
}