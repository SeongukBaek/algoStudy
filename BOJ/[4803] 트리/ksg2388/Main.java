import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static boolean isTree;
  static List<HashSet<Integer>> node;
  static boolean[] visited;
  static StringBuilder result = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    int count = 0;

    while (true) {
      count++;

      int sum = 0;
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      visited = new boolean[n + 1];

      if (n == 0 && m == 0) {
        break;
      }
      node = new ArrayList<>();

      for (int i = 0; i <= n; i++) {
        node.add(new HashSet<>());
      }

      // 트리 생성
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        node.get(from).add(to);
        node.get(to).add(from);
      }

      // 트리 개수 확인
      for (int i = 1; i <= n; i++) {
        if (visited[i]) {
          continue;
        }
        visited[i] = true;
        isTree = true;
        dfs(i);
        if (isTree) {
          sum += 1;
        }
      }

      printResult(count, sum);
    }
    System.out.println(result);
  }

  static void dfs(int idx) {
    for (int next : node.get(idx)) {
      // 방문했던 곳을 다시 방문시 사이클이 형성되므로 트리 X
      if (visited[next]) {
        isTree = false;
        return;
      }
      node.get(next).remove(idx);
      visited[next] = true;
      dfs(next);
    }
    return;
  }

  static void printResult(int count, int sum) {
    result.append("Case ").append(count).append(": ");
    if (sum == 0) {
      result.append("No trees.").append("\n");
      return;
    }

    if (sum == 1) {
      result.append("There is one tree.").append("\n");
      return;
    } else {
      result.append("A forest of ").append(sum).append(" trees.").append("\n");
    }
  }
}