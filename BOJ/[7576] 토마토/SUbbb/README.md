# [7576] 토마토

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private static int searchAllDirection(Tomato current) {
    int day = current.day + 1;
    for (int dir = 0; dir < 4; dir++) {
        int nx = current.x + directions[dir][0];
        int ny = current.y + directions[dir][1];

        // 범위 밖이거나, 토마토가 없거나, 이미 익은 토마토라면 패스
        if (!isIn(nx, ny) || map[nx][ny] == -1 || map[nx][ny] > 0) {
            continue;
        }

        // 토마토가 익게 되는 날짜 갱신하고 큐에 삽입
        ripeTomatoes.add(new Tomato(nx, ny, day));
        map[nx][ny] = day;
        zeroCount--;
    }
    return day;
}
```

- 주어진 토마토의 위치로부터 사방에 있는 아직 익지 않은 토마토를 숙성시킨다.
- 이와 동시에 익지 않은 토마토의 개수를 감소시킨다.

```java
private static int proceedRiping() {
    int day = 0;
    // 토마토 숙성 시작
    while (!ripeTomatoes.isEmpty()) {
        day = searchAllDirection(ripeTomatoes.poll());
    }
    return day - 1;
}
```

- day는 특정 토마토로부터 영향을 끼칠 수 있는 모든 토마토를 숙성시킬 수 있는 일자이다.
- 마지막에 -1을 하는 이유는 마지막으로 숙성된 토마토의 좌표로 while문을 한 번 더 돌고 +1된 값이 반환되기 때문이다.

## :black_nib: **Review**
- 생각보다 어렵지 않은 BFS 탐색이었는데, 하나의 토마토로부터 숙성을 시작하고 그 다음에 다른 토마토의 숙성을 수행해야 된다고 착각해 오래 걸렸다.