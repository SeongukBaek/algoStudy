import java.io.*;
import java.util.*;

public class Main {

    static int N, result = Integer.MAX_VALUE, population;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                population += num;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d1 = 0; d1 < N; d1++) {
                    for (int d2 = 0; d2 < N; d2++) {
                        if (i + d1 + d2 >= N) {
                            continue;
                        }
                        if (j - d1 < 0 || j + d2 >= N) {
                            continue;
                        }
                        divideArea(i, j, d1, d2);
                    }
                }
            }
        }
        System.out.println(result);
    }

    /**
     * 선거구를 5개의 구역으로 나눈다.
     */
    public static void divideArea(int x, int y, int d1, int d2) {
        int[] ward = new int[5];
        boolean[][] visited = new boolean[N][N];

        // 경계선 설정
        for (int i = 0; i <= d1; i++) {
            visited[x + i][y - i] = true;
            visited[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            visited[x + i][y + i] = true;
            visited[x + d1 + i][y - d1 + i] = true;
        }

        // 1번구역 인구 계산
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (visited[i][j]) {
                    break;
                }
                ward[0] += map[i][j];
            }
        }

        // 2번구역 인구 계산
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j >= y + 1; j--) {
                if (visited[i][j]) {
                    break;
                }
                ward[1] += map[i][j];
            }
        }

        // 3번구역 인구 계산
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (visited[i][j]) {
                    break;
                }
                ward[2] += map[i][j];
            }
        }

        // 4번구역 인구 계산
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (visited[i][j]) {
                    break;
                }
                ward[3] += map[i][j];
            }
        }

        // 5번구역 인구 계산
        ward[4] = population - ward[0] - ward[1] - ward[2] - ward[3];

        result = Math.min(findMinDifference(ward), result);
    }

    public static int findMinDifference(int[] ward) {
        Arrays.sort(ward);

        return ward[4] - ward[0];
    }
}
