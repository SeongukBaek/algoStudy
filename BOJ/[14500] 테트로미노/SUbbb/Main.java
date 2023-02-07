import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int[][] map;
    private static List<List<Node>> tetrominos;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tetrominos = new ArrayList<>();
        init();

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                for (List<Node> tetromino : tetrominos) {
                    max = Math.max(max, placeTetromino(tetromino, x, y));
                }
            }
        }
        System.out.println(max);
    }

    private static void init() {
        for (int index = 0; index < 19; index++) {
            tetrominos.add(new ArrayList<>());
        }

        tetrominos.get(0).add(new Node(0, 0));
        tetrominos.get(0).add(new Node(0, 1));
        tetrominos.get(0).add(new Node(0, 2));
        tetrominos.get(0).add(new Node(0, 3));

        tetrominos.get(1).add(new Node(0, 0));
        tetrominos.get(1).add(new Node(1, 0));
        tetrominos.get(1).add(new Node(2, 0));
        tetrominos.get(1).add(new Node(3, 0));

        tetrominos.get(2).add(new Node(0, 0));
        tetrominos.get(2).add(new Node(1, 0));
        tetrominos.get(2).add(new Node(0, 1));
        tetrominos.get(2).add(new Node(1, 1));

        tetrominos.get(3).add(new Node(0, 0));
        tetrominos.get(3).add(new Node(1, 0));
        tetrominos.get(3).add(new Node(2, 0));
        tetrominos.get(3).add(new Node(2, 1));

        tetrominos.get(4).add(new Node(0, 1));
        tetrominos.get(4).add(new Node(1, 1));
        tetrominos.get(4).add(new Node(2, 1));
        tetrominos.get(4).add(new Node(2, 0));

        tetrominos.get(5).add(new Node(0, 0));
        tetrominos.get(5).add(new Node(0, 1));
        tetrominos.get(5).add(new Node(1, 1));
        tetrominos.get(5).add(new Node(2, 1));

        tetrominos.get(6).add(new Node(0, 0));
        tetrominos.get(6).add(new Node(0, 1));
        tetrominos.get(6).add(new Node(1, 0));
        tetrominos.get(6).add(new Node(2, 0));

        tetrominos.get(7).add(new Node(0, 0));
        tetrominos.get(7).add(new Node(1, 0));
        tetrominos.get(7).add(new Node(1, 1));
        tetrominos.get(7).add(new Node(1, 2));

        tetrominos.get(8).add(new Node(0, 2));
        tetrominos.get(8).add(new Node(1, 1));
        tetrominos.get(8).add(new Node(1, 2));
        tetrominos.get(8).add(new Node(1, 0));

        tetrominos.get(9).add(new Node(0, 0));
        tetrominos.get(9).add(new Node(0, 1));
        tetrominos.get(9).add(new Node(0, 2));
        tetrominos.get(9).add(new Node(1, 2));

        tetrominos.get(10).add(new Node(0, 0));
        tetrominos.get(10).add(new Node(1, 0));
        tetrominos.get(10).add(new Node(0, 1));
        tetrominos.get(10).add(new Node(0, 2));

        tetrominos.get(11).add(new Node(0, 0));
        tetrominos.get(11).add(new Node(1, 0));
        tetrominos.get(11).add(new Node(1, 1));
        tetrominos.get(11).add(new Node(2, 1));

        tetrominos.get(12).add(new Node(0, 1));
        tetrominos.get(12).add(new Node(1, 1));
        tetrominos.get(12).add(new Node(1, 0));
        tetrominos.get(12).add(new Node(2, 0));

        tetrominos.get(13).add(new Node(1, 0));
        tetrominos.get(13).add(new Node(1, 1));
        tetrominos.get(13).add(new Node(0, 1));
        tetrominos.get(13).add(new Node(0, 2));

        tetrominos.get(14).add(new Node(0, 0));
        tetrominos.get(14).add(new Node(0, 1));
        tetrominos.get(14).add(new Node(1, 1));
        tetrominos.get(14).add(new Node(1, 2));

        tetrominos.get(15).add(new Node(0, 1));
        tetrominos.get(15).add(new Node(1, 0));
        tetrominos.get(15).add(new Node(1, 1));
        tetrominos.get(15).add(new Node(1, 2));

        tetrominos.get(16).add(new Node(0, 0));
        tetrominos.get(16).add(new Node(0, 1));
        tetrominos.get(16).add(new Node(0, 2));
        tetrominos.get(16).add(new Node(1, 1));

        tetrominos.get(17).add(new Node(0, 0));
        tetrominos.get(17).add(new Node(1, 0));
        tetrominos.get(17).add(new Node(1, 1));
        tetrominos.get(17).add(new Node(2, 0));

        tetrominos.get(18).add(new Node(0, 1));
        tetrominos.get(18).add(new Node(1, 1));
        tetrominos.get(18).add(new Node(1, 0));
        tetrominos.get(18).add(new Node(2, 1));
    }

    private static int placeTetromino(List<Node> squares, int x, int y) {
        int sum = 0;
        for (Node square : squares) {
            int nx = square.x + x;
            int ny = square.y + y;

            if (!isIn(nx, ny)) {
                return -1;
            }
            sum += map[nx][ny];
        }
        return sum;
    }

    private static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}