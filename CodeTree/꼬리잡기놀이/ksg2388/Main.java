import java.io.*;
import java.util.*;

/*
 * 각 팀을 배열 arrayList에 저장
 */

public class Main {
  static class Node {
    int x;
    int y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class Place {
    int x;
    int y;
    int prevX;
    int prevY;
    int dist;

    Place(int x, int y, int prevX, int prevY, int dist) {
      this.x = x;
      this.y = y;
      this.prevX = prevX;
      this.prevY = prevY;
      this.dist = dist;
    }
  }

  static int n, m, k, result; // 격자의 크기, 팀의 개수, 라운드 수
  static int map[][];
  static int[] dx = { 1, 0, -1, 0 };
  static int[] dy = { 0, -1, 0, 1 };
  static Node[] headPosion;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    headPosion = new Node[m];
    map = new int[n][n];
    int idx = 0;

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        int num = Integer.parseInt(st.nextToken());
        // 머리인 경우 따로 위치를 저장
        if (num == 1) {
          headPosion[idx++] = new Node(i, j);
        }
        map[i][j] = num;
      }
    }

    for (int i = 1; i <= k; i++) {
      playRound(i);
    }
    System.out.println(result);
  }

  // 라운드를 진행
  public static void playRound(int round) {
    // 머리 사람이 먼저 이동 후 팀 이동
    for (int i = 0; i < m; i++) {
      moveTeam(headPosion[i], i);
    }
    // 공 던지기
    tossBall(round);
  }

  // 팀이 이동
  public static void moveTeam(Node head, int idx) {
    Node start = new Node(head.x, head.y);
    Node prev = new Node(start.x, start.y);
    Node next = null;
    // 머리가 우선 이동
    for (int i = 0; i < 4; i++) {
      int nx = start.x + dx[i];
      int ny = start.y + dy[i];
      // 맵 밖으로 나가는 경우 무시
      if (isOutMap(nx, ny)) {
        continue;
      }
      // 이동선이나 꼬리를 만날 경우 이동
      if (map[nx][ny] == 4 || map[nx][ny] == 3) {
        map[nx][ny] = 1;
        headPosion[idx].x = nx;
        headPosion[idx].y = ny;
      }
      // 다음 이동 위치 저장
      if (map[nx][ny] == 2) {
        next = new Node(nx, ny);
      }
    }

    boolean isBody = true;
    // 몸통들 이동
    while (isBody) {
      Node cur = new Node(next.x, next.y);
      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];
        // 이동할 수 없는 위치거나 이전에 왔던 방향이라면 무시
        if (isOutMap(nx, ny) || (nx == prev.x && ny == prev.y)) {
          continue;
        }
        // 몸통인 경우 계속 반복
        if (map[nx][ny] == 2) {
          map[prev.x][prev.y] = 2;
          prev = new Node(cur.x, cur.y);
          next = new Node(nx, ny);
          break;
        }
        // 꼬리나 머리를 만나는 경우 종료 (1까지 검사하는 이유는 꼬리가 머리로 바뀐 경우)
        if (map[nx][ny] == 3 || map[nx][ny] == 1) {
          map[prev.x][prev.y] = 2;
          prev = new Node(cur.x, cur.y);
          next = new Node(nx, ny);
          isBody = false;
          break;
        }
      }
    }
    // 꼬리 이동
    map[prev.x][prev.y] = 3;
    // 꼬리가 있던 곳이 값이 바뀌지 않았으면
    if (map[next.x][next.y] == 3) {
      map[next.x][next.y] = 4;
    }
  }

  public static boolean isOutMap(int x, int y) {
    return x < 0 || y < 0 || x >= n || y >= n || map[x][y] == 0;
  }

  public static void tossBall(int round) {
    int type = (round - 1) / n % 4;
    int turn = (round - 1) % n;

    if (type == 0) {
      for (int i = 0; i < n; i++) {
        // 공을 사람이 받은 경우
        if (isPerson(map[turn][i])) {
          plusPoint(turn, i);
          changeHeadTail(turn, i);
          return;
        }
      }
      return;
    }

    if (type == 1) {
      for (int i = n - 1; i >= 0; i--) {
        if (isPerson(map[i][turn])) {
          plusPoint(i, turn);
          changeHeadTail(i, turn);
          return;
        }
      }
      return;
    }

    if (type == 2) {
      for (int i = n - 1; i >= 0; i--) {
        if (isPerson(map[n - turn - 1][i])) {
          plusPoint(n - turn - 1, i);
          changeHeadTail(n - turn - 1, i);
          return;
        }
      }
      return;
    }

    if (type == 3) {
      for (int i = 0; i < n; i++) {
        if (isPerson(map[i][n - turn - 1])) {
          plusPoint(i, n - turn - 1);
          changeHeadTail(i, n - turn - 1);
          return;
        }
      }
      return;
    }
  }

  public static boolean isPerson(int point) {
    return point == 1 || point == 2 || point == 3;
  }

  // 현재 지점에서 머리와의 거리를 알아내 포인트 계산
  public static void plusPoint(int x, int y) {
    // 머리인 경우 종료
    if (map[x][y] == 1) {
      result += 1;
      return;
    }
    Deque<Place> next = new LinkedList<>();
    next.offer(new Place(x, y, -1, -1, 1));

    while (!next.isEmpty()) {
      Place cur = next.poll();
      // 꼬리인 경우 포인트 계산 후 종료
      if (map[cur.x][cur.y] == 1) {
        result += Math.pow(cur.dist, 2);
        return;
      }

      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];

        // 맵 밖으로 나가거나 이전에 왔던 방향으로 가는 경우 무시
        if (isOutMap(nx, ny) || (nx == cur.prevX && ny == cur.prevY)) {
          continue;
        }

        // 현재 꼬리인 경우 머리는 무시
        if (map[cur.x][cur.y] == 3 && map[nx][ny] == 1) {
          continue;
        }
        // 머리나 몸통인 경우
        if (map[nx][ny] == 1 || map[nx][ny] == 2) {
          next.offer(new Place(nx, ny, cur.x, cur.y, cur.dist + 1));
        }
      }
    }
  }

  // 머리와 꼬리를 찾아 서로 교환
  public static void changeHeadTail(int x, int y) {
    Node tail = null, head = null;
    if (map[x][y] == 1) {
      head = new Node(x, y);
    }
    if (map[x][y] == 3) {
      tail = new Node(x, y);
    }
    Deque<Place> next = new LinkedList<>();
    next.offer(new Place(x, y, -1, -1, 0));

    // 꼬리와 머리 찾기
    while (!next.isEmpty()) {
      Place cur = next.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];

        // 이동할 수 없는 위치거나 이전에 왔던 방향으로 가는 경우 무시
        if (isOutMap(nx, ny) || (nx == cur.prevX && ny == cur.prevY)) {
          continue;
        }
        // 머리인 경우
        if (map[nx][ny] == 1) {
          head = new Node(nx, ny);
          continue;
        }
        // 몸통인 경우
        if (map[nx][ny] == 2) {
          next.offer(new Place(nx, ny, cur.x, cur.y, 0));
          continue;
        }
        // 꼬리인 경우
        if (map[nx][ny] == 3) {
          tail = new Node(nx, ny);
          continue;
        }
      }
    }
    map[head.x][head.y] = 3;
    map[tail.x][tail.y] = 1;
    for (int i = 0; i < m; i++) {
      if (headPosion[i].x == head.x && headPosion[i].y == head.y) {
        headPosion[i].x = tail.x;
        headPosion[i].y = tail.y;
        return;
      }
    }
    return;
  }
}