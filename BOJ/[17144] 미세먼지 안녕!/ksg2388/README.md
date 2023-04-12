# [17144] 미세먼지 안녕!

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
static void spread() {
  int[][] newMap = new int[R][C];

  for (int i = 0; i < R; i++) {
    for (int j = 0; j < C; j++) {
      // 공기청정기인 경우 무시
      if (map[i][j] == -1) {
        newMap[i][j] = -1;
        continue;
      }
      // 확산되지 않는 경우 무시
      if (map[i][j] < 5) {
        newMap[i][j] += map[i][j];
        continue;
      }

      int originNum = map[i][j];
      int spreadNum = map[i][j] / 5;

      for (int k = 0; k < 4; k++) {
        int nx = i + dx[k];
        int ny = j + dy[k];

        if (isSpread(nx, ny)) {
          newMap[nx][ny] += spreadNum;
          originNum -= spreadNum;
        }
      }
      newMap[i][j] += originNum;
    }
  }
  map = newMap;
}
```

- 새로운 배열을 만들어서 공기청정기인 경우와 미세먼지가 5보다 작은 경우(확산이 되지 않는 경우)는 그대로 기존의 배열에서 옮겨온다.
- 공기가 확산되는 경우는 확산되는 양 만큼 기존 미세먼지에서 빼준 뒤 남은 숫자를 새로운 배열에 옮겨준다.

```java
static void airClean() {...}
```

- 공기청정기를 통해 미세먼지를 순환시킨다.

## :black_nib: **Review**

- 배열 돌리기 문제와 비슷한 문제였다.
- 항상 배열 돌리기는 헷갈린다...
