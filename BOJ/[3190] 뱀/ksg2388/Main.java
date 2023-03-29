import java.util.*;
import java.io.*;

public class Main {
  static class Node {
    int x;
    int y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class Command {
    int t;
    char d;

    Command(int t, char d) {
      this.t = t;
      this.d = d;
    }
  }

  static int N, K, L, time, direction;
  static int[][] map;
  static int[] dx = { 0, 1, 0, -1 };
  static int[] dy = { 1, 0, -1, 0 };
  static Deque<Command> command = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    // 맵에 사과 추가
    K = Integer.parseInt(br.readLine());
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken()) - 1;
      int y = Integer.parseInt(st.nextToken()) - 1;
      map[x][y] = 2;
    }
    L = Integer.parseInt(br.readLine());
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      char d = st.nextToken().charAt(0);
      command.offer(new Command(t, d));
    }

    gameStart();
    System.out.println(time);
  }

  private static void gameStart() {
    // 맵에 뱀 추가
    map[0][0] = 1;
    Node head = new Node(0, 0);
    Deque<Node> baaam = new LinkedList<>();
    baaam.offer(new Node(0, 0));

    // 뱀이 벽에 부딪히거나 벽에 부딪히면 게임 종료
    while (true) {
      time++;
      // 방향을 바꿔야하는 시간이지 확인
      if (!command.isEmpty() && time - 1 == command.peek().t) {
        Command cmd = command.poll();
        if (cmd.d == 'D') {
          direction = direction + 1 > 3 ? 0 : direction + 1;
        } else {
          direction = direction - 1 < 0 ? 3 : direction - 1;
        }
      }
      // 머리를 늘려 다음칸으로 이동한다.
      head.x += dx[direction];
      head.y += dy[direction];

      // 이동한 칸이 벽이나 자기 몸통인 경우 게임 종료
      if (gameEnd(head.x, head.y)) {
        return;
      }
      // 이동한 칸에 사과가 있는 경우
      if (map[head.x][head.y] == 2) {
        map[head.x][head.y] = 1;
        baaam.offer(new Node(head.x, head.y));
        continue;
      }

      // 이동한 칸에 사과가 없는 경우
      map[head.x][head.y] = 1;
      baaam.offer(new Node(head.x, head.y));
      Node tail = baaam.poll();
      map[tail.x][tail.y] = 0;
    }
  }

  private static boolean gameEnd(int x, int y) {
    return x < 0 || y < 0 || x >= N || y >= N || map[x][y] == 1;
  }
}