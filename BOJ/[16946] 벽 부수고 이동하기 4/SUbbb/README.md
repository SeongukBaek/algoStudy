# [16946] 벽 부수고 이동하기 4

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
private static void computeMovableCount(int x, int y) {
  Stack<int[]> locations = new Stack<>();
  List<int[]> zeros = new ArrayList<>();

  locations.add(new int[] {x, y});
  zeros.add(new int[] {x, y});

  isVisited[x][y] = true;

  while (!locations.isEmpty()) {
      int[] current = locations.pop();

      for (int[] direction : directions) {
          int nx = current[0] + direction[0];
          int ny = current[1] + direction[1];

          // 다음 좌표가 맵 밖이거나, 벽이거나, 이미 방문한 좌표라면 패스
          if (!isIn(nx, ny) || map[nx][ny] == 1 || isVisited[nx][ny]) {
              continue;
          }

          zeros.add(new int[] {nx, ny});
          locations.add(new int[] {nx, ny});
          isVisited[nx][ny] = true;
      }
  }

  // 지역별 이동할 수 있는 칸의 개수 저장 및 지역 정보 저장
  areaSize.add(zeros.size());
  for (int[] zero : zeros) {
      areas[zero[0]][zero[1]] = area;
  }
}
```

- 0인 좌표들을 대상으로, 연결된 칸의 개수(이동할 수 있는 칸의 개수)를 구하고, 지역별로 저장한다.

```java
private static int countEWSN(int x, int y) {
  int count = 1;
  boolean[] isVisitedArea = new boolean[area];

  for (int[] direction : directions) {
      int nx = x + direction[0];
      int ny = y + direction[1];

      // 다음 좌표가 맵 밖이거나, 벽이거나, 이미 방문한 지역이라면 패스
      if (!isIn(nx, ny) || map[nx][ny] == 1 || isVisitedArea[areas[nx][ny]]) {
          continue;
      }

      count += areaSize.get(areas[nx][ny]);
      isVisitedArea[areas[nx][ny]] = true;
  }
  return count % 10;
}
```

- 벽 좌표에서, 동서남북을 확인하고 이동할 수 있는 칸의 개수를 누적한다.

## :black_nib: **Review**

- 벽 좌표로부터 시작하는 것이 아니라, 0인 좌표들을 먼저 연결영역으로 저장해두는 것이 주요한 문제였다.