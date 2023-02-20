import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(movePipes());
    }

    // 0 : 가로, 1 : 대각선, 2 : 세로
    private static int movePipes() {
        int[][][] cases = new int[N][N][3];
        cases[0][1][0] = 1;

        for (int x = 0; x < N; x++) {
            for (int y = 2; y < N; y++) {
                if (map[x][y] == 1) {
                    continue;
                }

                // 다음 좌표에 파이프가 가로로 놓일 경우는 이전 좌표에서 파이프가 가로였거나, 대각선인 경우
                cases[x][y][0] = cases[x][y - 1][0] + cases[x][y - 1][1];

                if (x == 0) {
                    continue;
                }

                // 다음 좌표에 파이프가 세로로 놓일 경우는 이전 좌표에서 파이프가 세로였거나, 대각선인 경우
                cases[x][y][2] = cases[x - 1][y][1] + cases[x - 1][y][2];

                // 파이프의 대각선 이동을 위해 벽 체크, x, y에 대해서는 위에서 걸러짐
                if (map[x - 1][y] == 1 || map[x][y - 1] == 1) {
                    continue;
                }

                // 다음 좌표에 파이프가 대각선으로 놓일 경우는 이전 좌표에서 파이프가 가로였거나, 세로였거나, 대각선인 경우
                cases[x][y][1] = cases[x - 1][y - 1][0] + cases[x - 1][y - 1][1] + cases[x - 1][y - 1][2];
            }
        }
        return cases[N - 1][N - 1][0] + cases[N - 1][N - 1][1] + cases[N - 1][N - 1][2];
    }
}