# [삼성 SW 역량테스트] 꼬리잡기놀이

## :pushpin: **Algorithm**

구현, 시뮬레이션, 덱

## :round_pushpin: **Logic**

```java
// 경로를 Deque으로 저장
boolean[][] isVisited = new boolean[n][n];
for (index = 0; index < m; index++) {
  Location head = teams.get(index).members.getFirst();
  Queue<Location> locations = new LinkedList<>();
  locations.add(head);
  isVisited[head.x][head.y] = true;

  while (!locations.isEmpty()) {
    Location current = locations.poll();

    for (int[] move : moves) {
      int nx = current.x + move[0];
      int ny = current.y + move[1];

      if (!isIn(nx, ny) || isVisited[nx][ny] || map[nx][ny] == 0 || map[nx][ny] == 1) {
        continue;
      }

      Location nL = new Location(nx, ny);
      if (map[nx][ny] <= map[current.x][current.y] + 1) {
        teams.get(index).members.addLast(nL);
        locations.add(nL);
        isVisited[nx][ny] = true;
      }
    }
  }
}
```

- 1,2,3 뿐만 아니라 4까지 즉, 팀의 경로 전체를 Deque으로 저장한다.

```java
for (int round = 0; round < k; round++) {
  // 1. 각 팀별 한칸이동
  moveTeams();
  // 2. 공 던지고, 점수 계산
  computeScore(throwBall(round));
}
```

- 각 팀별로 한 칸씩 이동한다.
  - 이동 시, 머리 사람 방향인지, 꼬리 사람 방향인지를 구분해 이동한다.
- 라운드에 따라 공의 시작점을 정하고, 공을 던진다.
- 공에 맞는 사람이 있는 지점을 먼저 구한 후, 해당 지점이 어느 팀의 몇 번째 사람인지 찾아 점수를 계산한다.

## :black_nib: **Review**
- 처음에는 팀별 멤버들만 Deque으로 저장했는데, 이렇게 하면 방향에 따른 이동 시에 너무 구현이 지저분해지는 것 같았다. 또한 팀별 경로에 4가 없이 사람으로만 채워져있는 경우가 반례였다.
  - 따라서 경로 모두 Deque으로 저장함으로써 이동을 좀 더 수월하게 구현할 수 있었다.
- 만약 팀별 점수를 계산하는 문제였다면 좀 더 까다로워졌을 것 같다.