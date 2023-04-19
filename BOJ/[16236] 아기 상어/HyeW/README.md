# [16236] 아기 상어

## :pushpin: **Algorithm**

구현, 그래프 이론, 그래프 탐색, BFS, 시뮬레이션

## :round_pushpin: **Logic**

```java
while (!path.isEmpty()) {
    Position cur = path.poll();
    // 물고기를 잡아 먹을 수 있을 경우
    if (sea[cur.x][cur.y] < shark.w && sea[cur.x][cur.y] > 0) {
        eatenFish++;
        // 먹은 물고기 수가 자신의 크기와 같으면 상어 크기 커진다.
        if (shark.w == eatenFish) {
            shark.w++;
            eatenFish = 0;
        }
        shark.x = cur.x;
        shark.y = cur.y;
        sea[cur.x][cur.y] = 0;
        return cur.w;
    }

    for (int i = 0; i < 4; i++) {
        int dx = cur.x + dr[i];
        int dy = cur.y + dc[i];

        if (isMapOut(dx, dy)) {
            continue;
        }
        // 방문을 했거나 물고기가 상어보다 클때 
        if (visited[dx][dy] || sea[dx][dy] > shark.w) {
            continue;
        }

        // 상어가 해당 좌표로 이동할 수 있는 경우
        path.add(new Position(dx, dy, cur.w + 1));
        visited[dx][dy] = true;
    }
}
```

- BFS와 우선순위 큐를 이용하여 구현했다.
- 상어가 좌표에 이동 가능한 경우 큐에 넣고 먹을 수 있는 물고기가 나올 때까지 좌표를 찾는다.

## :black_nib: **Review**

- 우선 순위를 정하는게 어려웠다.
    - 방향 순서를 정해주는 것만으로 우선순위가 구현되는 것이 아니었다.
    - 이전에 팩맨같은 경우는 내 기준에서 방향에 대한 우선 순위가 있는 거였고 이번 상어는 전체 좌표배열에서 우선순위가 있어서 방향순서만으로 우선순위가 되지않았다.