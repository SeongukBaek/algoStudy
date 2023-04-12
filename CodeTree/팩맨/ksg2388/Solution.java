import java.io.*;
import java.util.*;

/*
 * 1. newMonsterMap에 몬스터들을 이동
 * 2. 팩맨을 이동하면서 monster 처치
 * 3. 몬스터 시체 시간 -1
 * 4. newMonsterMap을 map에 붙이기
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

  static int[][] map = new int[5][5]; // 팩맨과 시체의 정보를 나타내는 맵
  static List<Integer>[][] monsterMap = new ArrayList[5][5]; // 몬스터들의 정보를 나타내는 맵
  static List<Integer>[][] newMonsterMap;
  static Node pacManPosition;
  static int[] pacManMove = new int[3]; // 팩맨의 이동 위치 저장
  static int m, t, eatCount, totalMonsterCount;
  static int[][] visited;
  static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
  static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

  public static void main(String[] args) throws IOException {
    init();
    gameStart();
    System.out.println(totalMonsterCount);
  }

  public static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());
    t = Integer.parseInt(st.nextToken());
    totalMonsterCount = m;

    st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    // 팩맨 추가
    pacManPosition = new Node(r, c);

    // 몬스터 정보 받기
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        monsterMap[i][j] = new ArrayList<>();
      }
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      monsterMap[x][y].add(d);
    }
  }

  public static void gameStart() {
    for (int i = 0; i < t; i++) {
      roundProgress();
    }
  }

  private static void roundProgress() {
		monstersMove();
		eatCount = -1;
		visited = new int[5][5];
		findPacManOptimalPath(pacManPosition.x, pacManPosition.y, 0, 0, new int[3]);
		pacManMove();
		decay();	// 시체 부패
		eggHatching();	// 알 부화

  private static void monstersMove() {
    newMonsterMap = new ArrayList[5][5];
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        newMonsterMap[i][j] = new ArrayList<>();
      }
    }

    // 몬스터들 전부 이동 시켜주기
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        for (int monster : monsterMap[i][j]) {
          monsterMove(i, j, monster);
        }
      }
    }
  }

  // 몬스터 1마리 이동
  private static void monsterMove(int x, int y, int monster) {
    for (int i = 0; i < 8; i++) {
      int next = monster + i;
      if (next >= 9) {
        next -= 8;
      }
      int nx = x + dx[next];
      int ny = y + dy[next];
      if (isMoveable(nx, ny)) {
        newMonsterMap[nx][ny].add(next);
        return;
      }
    }

    // 움직일 수 있는 방향이 없는 경우
    newMonsterMap[x][y].add(monster);
  }

  private static void findPacManOptimalPath(int x, int y, int depth, int count, int[] move) {
    if (depth == 3) {
      if (eatCount < count) {
        eatCount = count;
        for (int i = 0; i < 3; i++) {
          pacManMove[i] = move[i];
        }
      }
      return;
    }

    for (int i = 1; i <= 8; i += 2) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (isOutMap(nx, ny)) {
        continue;
      }

      // 이동위치의 몬스터를 먹는다.
      if (visited[nx][ny] == 0) {
        count += newMonsterMap[nx][ny].size();
      }
      visited[nx][ny]++;
      move[depth] = i;
      findPacManOptimalPath(nx, ny, depth + 1, count, move);
      visited[nx][ny]--;
      if (visited[nx][ny] == 0) {
        count -= newMonsterMap[nx][ny].size();
      }
    }
  }

  private static void pacManMove() {
    int x = pacManPosition.x;
    int y = pacManPosition.y;

    for (int d : pacManMove) {
      x += dx[d];
      y += dy[d];
      killMonster(x, y);
    }

    pacManPosition = new Node(x, y);
  }

  private static void killMonster(int x, int y) {
    if (newMonsterMap[x][y].isEmpty()) {
      return;
    }
    newMonsterMap[x][y] = new ArrayList<>();
    map[x][y] = 3;
  }

  private static void decay() {
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (map[i][j] > 0 && map[i][j] < 4) {
          map[i][j]--;
        }
      }
    }
  }

  private static void eggHatching() {
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (!newMonsterMap[i][j].isEmpty()) {
          totalMonsterCount += newMonsterMap[i][j].size();
          monsterMap[i][j].addAll(newMonsterMap[i][j]);
        }
      }
    }
  }

  private static boolean isOutMap(int x, int y) {
    return x < 1 || y < 1 || x > 4 || y > 4;
  }

  private static boolean isMoveable(int x, int y) {
    return x > 0 && y > 0 && x < 5 && y < 5 && map[x][y] == 0 && (pacManPosition.x != x || pacManPosition.y != y);
  }

  private static int checkMonsterCount(int nx, int ny) {
    int count = 0;

    for (int num : newMonsterMap[nx][ny]) {
      count++;
    }
    return count;
  }
}