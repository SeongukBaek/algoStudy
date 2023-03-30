import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Location {
        int x;
        int y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N;
    private static int K;
    // 사과는 1로 표시, 뱀은 -1로 표시
    private static int[][] map;
    private static char[] directions;
    private static Deque<Location> snake;
    private static int direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int index = 0; index < K; index++) {
            st = new StringTokenizer(br.readLine());
            // 사과 : 1로 표시
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        directions = new char[10001];
        for (int index = 0; index < L; index++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);

            directions[time] = direction;
        }

        // 초기 뱀은 오른쪽 방향
        snake = new ArrayDeque<>();
        snake.add(new Location(0, 0));
        direction = 1;
        map[0][0] = -1;

        System.out.print(moveSnake() + 1);
    }

    // 상, 우, 하, 좌
    private static final int[][] moves = {{-1, 0},{0, 1}, {1, 0}, {0, -1}};
    private static int moveSnake() {
        int second = 0;

        while (true) {
            Location currentHead = snake.getFirst();

            // 방향 이동
            if (directions[second] == 'L') {
                direction = rotateLeft(direction);
            }
            if (directions[second] == 'D') {
                direction = rotateRight(direction);
            }

            int newX = currentHead.x + moves[direction][0];
            int newY = currentHead.y + moves[direction][1];

            // 벽 또는 자기자신의 몸이라면 종료
            if (!isIn(newX, newY) || map[newX][newY] == -1) {
                break;
            }

            // 일단 머리는 옮기기
            snake.addFirst(new Location(newX, newY));

            // 사과가 있어도 처리할 것 없음 -> 어차피 뱀 머리 이동 처리를 밑에서 해줄 것

            // 사과가 없다면
            if (map[newX][newY] == 0) {
                Location tail = snake.pollLast();
                map[tail.x][tail.y] = 0;
            }

            // 뱀 머리 이동 처리
            map[newX][newY] = -1;

            second++;
        }

        return second;
    }

    // 0 -> 3, 1 -> 0, 2 -> 1, 3 -> 2
    private static int rotateLeft(int direction) {
        if (direction == 0) {
            return 3;
        }
        return direction - 1;
    }

    // 0 -> 1, 1 -> 2, 2 -> 3, 3 -> 0
    private static int rotateRight(int direction) {
        if (direction == 3) {
            return 0;
        }
        return direction + 1;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
