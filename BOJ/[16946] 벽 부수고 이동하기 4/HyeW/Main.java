import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int[][] map;
    static int[][] mark;
    static Map<Integer, Integer> group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        mark = new int[N][M];
        group = new HashMap<>();
        int num = 2;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                // 벽이고 이미 확인한 칸은 확인하지 않는다
                if (map[x][y] != 0) {
                    continue;
                }
                // 그룹 번호가 가진 칸의 개수 저장하기
                group.put(num, markRoad(x, y, num));
                num++;
            }
        }

        // 답 출력하기
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                sb.append(countMap(x, y, num));
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int markRoad(int x, int y, int num) {
        Deque<Node> roads = new ArrayDeque<>();

        roads.offer(new Node(x, y));
        map[x][y] = num;

        int cnt = 1;
        while (!roads.isEmpty()) {
            Node cur = roads.poll();

            for (int i = 0; i < 4; i++) {
                int dx = cur.x + dr[i];
                int dy = cur.y + dc[i];

                if (isMapOut(dx, dy) || map[dx][dy] != 0) {
                    continue;
                }

                map[dx][dy] = num;
                roads.offer(new Node(dx, dy));
                cnt++;
            }
        }
        return cnt;
    }

    private static int countMap(int x, int y, int num) {
        // 벽인 칸만 확인하기
        if (map[x][y] != 1) {
            return 0;
        }

        // 벽을 부쉈을 때 갈수있는 칸 개수 세기
        Set<Integer> checked = new HashSet<>();
        int cnt = 1; // 칸 개수
        for (int i = 0; i < 4; i++) {
            int dx = x + dr[i];
            int dy = y + dc[i];

            if (isMapOut(dx, dy)) {
                continue;
            }
            // 현재 칸과 인접한 그룹 번호
            int groupNum = map[dx][dy];

            if (checked.contains(groupNum) || groupNum < 2 ) {
                continue;
            }
            cnt += group.get(groupNum);
            checked.add(groupNum);
        }

        return cnt % 10;
    }

    private static boolean isMapOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
