import java.io.*;
import java.util.*;

public class Main {
  static class Node {
    int x;
    int y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int N, L, R;
  static boolean isUnion;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = { -1, 0, 0, 1 };
  static int[] dy = { 0, -1, 1, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    map = new int[N][N];
    int moveCount = 0;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    while (true) {
      if (!populationMovement()) {
        break;
      }
      moveCount++;
    }

    System.out.println(moveCount);
  }

  private static boolean populationMovement() {
    isUnion = false;
    visited = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!visited[i][j]) {
          visited[i][j] = true;
          union(i, j);
        }
      }
    }
    return isUnion;
  }

  private static void union(int x, int y) {
    List<Node> unionList = new ArrayList<>();
    int sum = 0;
    Deque<Node> next = new ArrayDeque<>();
    next.offer(new Node(x, y));

    while (!next.isEmpty()) {
      Node cur = next.poll();
      unionList.add(new Node(cur.x, cur.y));
      sum += map[cur.x][cur.y];

      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];
        // 맵 밖으로 나가거나 방문 한 경우는 무시
        if (isOutMap(nx, ny) || visited[nx][ny]) {
          continue;
        }

        int diff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);

        // L이상 R이하인지 확인
        if (diff >= L && diff <= R) {
          isUnion = true;
          next.offer(new Node(nx, ny));
          visited[nx][ny] = true;
        }
      }
    }
    // 연합된 국가의 인구를 재분배
    sum /= unionList.size();
    for (Node node : unionList) {
      map[node.x][node.y] = sum;
    }
  }

  private static boolean isOutMap(int x, int y) {
    return x < 0 || y < 0 || x >= N || y >= N;
  }
}
