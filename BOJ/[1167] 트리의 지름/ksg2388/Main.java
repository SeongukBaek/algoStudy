import java.io.*;
import java.util.*;

class Main {
  static class Node {
    int value;
    int w;

    Node(int value, int w) {
      this.value = value;
      this.w = w;
    }
  }

  static int v, maxLength, nextPoint;
  static List<Node>[] tree;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int start = -1;
    v = Integer.parseInt(br.readLine());
    tree = new ArrayList[v + 1];
    for (int i = 1; i <= v; i++) {
      tree[i] = new ArrayList<>();
    }

    for (int i = 0; i < v; i++) {
      st = new StringTokenizer(br.readLine());
      int cur = Integer.parseInt(st.nextToken());
      int depth = 0;
      while (true) {
        int nodeNum = Integer.parseInt(st.nextToken());

        if (nodeNum == -1) {
          break;
        }

        depth++;
        int w = Integer.parseInt(st.nextToken());
        tree[cur].add(new Node(nodeNum, w));
      }

      if (depth == 1) {
        start = cur;
      }
    }
    visited = new boolean[v + 1];
    visited[start] = true;
    dfs(start, 0);

    visited = new boolean[v + 1];
    visited[nextPoint] = true;
    dfs(nextPoint, 0);

    System.out.println(maxLength);
  }

  static void dfs(int v, int length) {
    if (maxLength < length) {
      nextPoint = v;
      maxLength = length;
    }

    for (Node next : tree[v]) {
      if (!visited[next.value]) {
        visited[next.value] = true;
        dfs(next.value, length + next.w);
      }
    }
  }
}