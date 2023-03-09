import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[10][10];
    static int[] confetti = { 5, 5, 5, 5, 5 };
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        glueConfetti(0, 0, 0, map);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void glueConfetti(int x, int y, int count, int[][] map) {
        // 최소값보다 색종이 붙이는 횟수가 많은 경우
        if (min < count) {
            return;
        }

        if (y >= 10) {
            x++;
            y = 0;
        }

        // 좌표 끝까지 검사가 끝난 경우
        if (x == 10) {
            min = Math.min(min, count);
            return;
        }

        if (map[x][y] == 0) {
            glueConfetti(x, y + 1, count, map);
            return;
        }

        // 큰 색종이부터 순서대로 붙일 수 있나 확인
        for (int i = 4; i >= 0; i--) {
            if (checkConfetti(x, y, i + 1, map)) {
                int[][] newMap = new int[10][10];
                copyArray(newMap, map);
                if (confetti[i] == 0) {
                    return;
                }
                confetti[i]--;
                glueConfetti(x, y + i + 1, count + 1, paste(x, y, i + 1, newMap));
                confetti[i]++;
            }
        }
    }

    public static boolean checkConfetti(int x, int y, int size, int[][] map) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                // 색종이를 붙일 수 없는 경우
                if (ArrayOutOfMap(i, j) || map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void copyArray(int[][] newMap, int[][] map) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                newMap[i][j] = map[i][j];
            }
        }
    }

    public static int[][] paste(int x, int y, int size, int[][] newMap) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                newMap[i][j] = 0;
            }
        }
        return newMap;
    }

    public static boolean ArrayOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= 10 || y >= 10;
    }
}