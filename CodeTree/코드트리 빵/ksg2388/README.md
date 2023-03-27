# [삼성 SW 역량테스트] 코드트리 빵

## :pushpin: **Algorithm**

구현, BFS

## :round_pushpin: **Logic**

```java
while(!next.isEmpty()) {
  Node cur = next.poll();

  // 베이스 캠프에 도착한 경우
  if (map[cur.x][cur.y] == 1) {
    people[num - 1] = new Person(cur.x, cur.y, false);
    // 베이스 캠프 방문 처리
    visited[cur.x][cur.y] = true;
    return;
  }

  for (int i = 0; i < 4; i++) {
    int nx = cur.x + dx[i];
    int ny = cur.y + dy[i];

    if (isOutMap(nx, ny) || newVisited[nx][ny]) {
      continue;
    }

    next.offer(new Node(nx, ny));
    newVisited[nx][ny] = true;
  }
}
```

- 편의점에서 가장 가까운 베이스 캠프를 찾아 사람을 배정해준다.

```java
static int choiceOptimalPath(int idx, boolean[][] visited) {
  boolean[][] newVisited = arrayCopy(visited);
  Deque<Node> next = new LinkedList<>();
  boolean isFirst = true;

  next.offer(new Node(people[idx].x, people[idx].y));

  while (!next.isEmpty()) {
    Node cur = next.poll();

    if (cur.x == store[idx].x && cur.y == store[idx].y) {
      return cur.startDirection;
    }

    for (int i = 0; i < 4; i++) {
      int nx = cur.x + dx[i];
      int ny = cur.y + dy[i];

      if (isOutMap(nx, ny) || newVisited[nx][ny]) {
        continue;
      }
      Node nextPoint = new Node(nx, ny);
      // 초기 이동의 경우 방향을 넣어둔다
      if (isFirst) {
        nextPoint.startDirection = i;
      } else {
        nextPoint.startDirection = cur.startDirection;
      }
      next.offer(nextPoint);
      newVisited[nx][ny] = true;
    }
    isFirst = false;
  }
  return -1;
}
```

- 처음 움직이는 경우 그 이동 방향을 따로 저장해둔다.
- 편의점에 도착하는 경우 시작 방향을 반환해준다.

## :black_nib: **Review**

- 최단 경로로 어떻게 이동할지 구현하는 것이 중요한 문제였다.
- BFS를 이용하는 문제도 종류가 엄청 다양해서 많이 풀어보는 것이 중요한 것 같다.
