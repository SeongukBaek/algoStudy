# [169199] 리코쳇 로봇

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
while (!q.isEmpty()) {
    Point point = q.poll();

    if (point.x == goalX && point.y == goalY) {
        return point.count;
    }

    // 상
    int i = point.x;
    while (i >= 0 && board[i][point.y] == '.') {
        i--;
    }
    if (!visited[++i][point.y]) {
        visited[i][point.y] = true;
        q.offer(new Point(i, point.y, point.count+1));
    }
```

- BFS를 이용해 최소 이동 거리를 구했다.
- 상/하/좌/우 움직임을 각각 따로 구했다.
- 범위 끝에 달하거나, 장애물을 만날때까지 상/하/좌/우로 이동한다.
- 이동한 위치에 처음 방문했다면 queue에 넣어준다.

## :black_nib: **Review**

- 모든 방향을 각각 구현해주었더니 더 쉽게 풀린 것 같다.
