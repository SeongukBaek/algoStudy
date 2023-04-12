import java.io.*;
import java.util.*;

class Main {
  static int[][] map;
  static int R, C, T, bottom;
  static int top = -1;
  static int[] dx = { -1, 0, 0, 1 };
  static int[] dy = { 0, -1, 1, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());
    map = new int[R][C];

    for (int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < C; j++) {
        int num = Integer.parseInt(st.nextToken());
        map[i][j] = num;
        if (num == -1) {
          if (top == -1) {
            top = i;
          } else {
            bottom = i;
          }
        }
      }
    }

    for (int i = 0; i < T; i++) {
      spread();
      airClean();
    }

    System.out.println(countRemindDust());
  }

  static void spread() {
    int[][] newMap = new int[R][C];

    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        // 공기청정기인 경우 무시
        if (map[i][j] == -1) {
          newMap[i][j] = -1;
          continue;
        }
        // 확산되지 않는 경우 무시
        if (map[i][j] < 5) {
          newMap[i][j] += map[i][j];
          continue;
        }

        int originNum = map[i][j];
        int spreadNum = map[i][j] / 5;

        for (int k = 0; k < 4; k++) {
          int nx = i + dx[k];
          int ny = j + dy[k];

          if (isSpread(nx, ny)) {
            newMap[nx][ny] += spreadNum;
            originNum -= spreadNum;
          }
        }
        newMap[i][j] += originNum;
      }
    }
    map = newMap;
  }

  static void airClean() {
    // 위 부분
    // 위 -> 아래
    for (int i = top - 1; i > 0; i--) {
      map[i][0] = map[i - 1][0];
    }
    // 오른쪽 -> 왼쪽
    for (int i = 0; i < C - 1; i++) {
      map[0][i] = map[0][i + 1];
    }
    // 아래 -> 위
    for (int i = 0; i < top; i++) {
      map[i][C - 1] = map[i + 1][C - 1];
    }
    // 왼쪽 -> 오른쪽
    for (int i = C - 1; i > 1; i--) {
      map[top][i] = map[top][i - 1];
    }
    map[top][1] = 0;

    // 아래 부분
    // 아래 -> 위
    for (int i = bottom + 1; i < R - 1; i++) {
      map[i][0] = map[i + 1][0];
    }
    // 오른쪽 -> 왼쪽
    for (int i = 0; i < C - 1; i++) {
      map[R - 1][i] = map[R - 1][i + 1];
    }
    // 위 -> 아래
    for (int i = R - 1; i > bottom; i--) {
      map[i][C - 1] = map[i - 1][C - 1];
    }
    // 왼쪽 -> 오른쪽
    for (int i = C - 1; i > 1; i--) {
      map[bottom][i] = map[bottom][i - 1];
    }
    map[bottom][1] = 0;
  }

  static int countRemindDust() {
    int result = 0;

    for (int[] items : map) {
      for (int item : items) {
        if (item > 0) {
          result += item;
        }
      }
    }
    return result;
  }

  static boolean isSpread(int x, int y) {
    return x >= 0 && y >= 0 && x < R && y < C && map[x][y] != -1;
  }
}