import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static int[] map;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new int[101];
    visited = new boolean[101];

    for (int i = 1; i < 101; i++) {
      map[i] = i;
    }

    // 사다리 입력 받아서 맵 수정
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      map[from] = to;
    }

    // 뱀 입력 받아서 맵 수정
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      map[from] = to;
    }

    System.out.println(findMinimumCount());
  }

  static int findMinimumCount() {
    Queue<Integer> nextPoint = new LinkedList<>();
    int count = 0;
    nextPoint.add(1);

    while (!nextPoint.isEmpty()) {
      int size = nextPoint.size();
      count++;
      for (int q = 0; q < size; q++) {
        int cur = nextPoint.poll();

        for (int i = 1; i <= 6; i++) {
          int nextPosition = map[cur] + i;

          if (nextPosition >= 100) {
            return count;
          }

          if (visited[nextPosition]) {
            continue;
          }

          visited[nextPosition] = true;
          nextPoint.offer(nextPosition);
        }
      }
    }
    return -1;
  }
}
