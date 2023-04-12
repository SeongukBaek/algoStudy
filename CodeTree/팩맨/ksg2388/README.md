# [삼성 SW 역량테스트] 팩맨

## :pushpin: **Algorithm**

구현, 시뮬레이션, 중복 순열, DFS

## :round_pushpin: **Logic**

1.  newMonsterMap에 몬스터들을 이동
2.  팩맨을 이동하면서 monster 처치
3.  몬스터 시체 시간 -1
4.  newMonsterMap을 map에 붙이기
    순서로 로직을 구현하였다.

```java
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
```

- 몬스터를 이동할 수 있는 방향으로 이동시켜준다.

```java
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
```

- 움직일 수 있는 모든 조합을 다 찾아보고 몬스터를 가장 많이 먹을 수 있는 방향으로 움직인다.

```java
private static void killMonster(int x, int y) {
  if (newMonsterMap[x][y].isEmpty()) {
    return;
  }
  newMonsterMap[x][y] = new ArrayList<>();
  map[x][y] = 3;
}
```

- 몬스터가 죽는 경우 그 칸을 비우고 맵에 시체 시간을 3으로 해준다.

```java
private static void decay() {
  for (int i = 1; i <= 4; i++) {
    for (int j = 1; j <= 4; j++) {
      if (map[i][j] > 0 && map[i][j] < 4) {
        map[i][j]--;
      }
    }
  }
}
```

- 시체의 시간을 1씩 줄여준다.

## :black_nib: **Review**

- 주어진대로만 해서는 시간초과가 날 수도 있는 문제여서 많은 생각이 필요했다.
- 초기에 예외 사항들을 제대로 생각하지 않고 문제를 풀어서 디버깅 시간이 오래걸렸다.
- 풀고나니 더 쉽게 풀 수 있는 방법이 있었다... 똑똑한 사람들..
