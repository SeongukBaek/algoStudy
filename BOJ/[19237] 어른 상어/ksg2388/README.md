# [19237] 어른 상어

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

1. 맨 처음 모든 상어는 자신의 위치에서 자신의 냄새를 뿌린다.
2. 이후 1초마다 모든 상어가 이동조건에 따라 인접한 칸 중 하나로 이동하고 자신의 냄새를 그 칸에 뿌린다.
3. 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 제거한다.
4. 1번 상어가 남을때까지 반복한다.

- 각 상어가 이동하는 우선순위
  1.  먼저 이동한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
  2.  그런 칸이 없는 경우 자신의 냄새가 있는 칸으로 방향을 잡는다.

```java
public static void sharksMove() {
    Board[][] newMap = new Board[N][N];

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (map[i][j] != null && map[i][j].time == k) {
                sharkMove(i, j, map[i][j], newMap);
            }
        }
    }

    // 맵 업데이트
    mapUpdate(newMap);
}

```

- 맵에 상어가 존재하는 경우 (현재 냄새의 지속 시간이 최대인 경우) 상어를 움직여서 새로운 배열에 추가한다.
- 모든 상어가 이동하고나면 기존의 배열을 업데이트 시켜준다.

```java
public static int checkEmptyPlace(int x, int y, int sharkNum) {
    // 상어의 방향 우선순위 순서에 따라 돌면서 빈 공간 확인
    for (int i = 0; i < 4; i++) {
        int d = sharkPriorityDirection[sharkNum][sharkDirection[sharkNum]][i];
        int nx = x + dx[d];
        int ny = y + dy[d];
        // 상어가 격자 밖으로 나가는 경우
        if (sharkOutMap(nx, ny)) {
            continue;
        }
        if (map[nx][ny] == null) {
            return d;
        }
    }
    return -1;
}
```

- 3차원 배열을 이용해 현재 상어가 보고있는 방향의 우선순위대로 주변을 확인하면서 빈 공간이 있는 경우 이동한다.

```java
public static void mapUpdate(Board[][] newMap) {
    // 냄새 남기기
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            // 냄새가 존재하는 경우
            if (map[i][j] != null) {
                map[i][j] = new Board(map[i][j].sharkNum, map[i][j].time - 1);
                if (map[i][j].time == 0) {
                    map[i][j] = null;
                }
            }
        }
    }

    // 상어들을 추가
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            // 상어인 경우
            if (newMap[i][j] != null && newMap[i][j].time == k) {
                map[i][j] = newMap[i][j];
            }
        }
    }
}
```

- 기존에 냄새 남아있는 존재하는 경우는 모두 지속시간을 1씩 낮춰주고 0이되는 경우 맵에서 삭제해준다.
- 냄새의 지속시간을 모두 업데이트 해준뒤 이동한 상어들을 맵에 추가해준다.

## :black_nib: **Review**

- 풀기전에 어떤 방식으로 구현하고 어떤 자료구조를 이용할지 잘 설정해두고 문제를 풀어보니 훨씬 수월했다.
- 기능별로 구현을하면서 바로바로 테스트를 하면서 구현을 해보니 훨씬 시간이 적게 들었다.
