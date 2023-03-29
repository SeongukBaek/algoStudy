# [16234] 인구 이동

## :pushpin: **Algorithm**

BFS, 구현, 그래프 이론, 그래프 탐색

## :round_pushpin: **Logic**

```java
private static boolean populationMovement() {
  isUnion = false;
  visited = new boolean[N][N];

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (!visited[i][j]) {
        visited[i][j] = true;
        union(i, j);
      }
    }
  }
  return isUnion;
}
```

- 모든 국가를 확인하여 연합 여부를 확인한다.
- 연합된 국가가 하나라도 있는 경우 isUnion은 true를 반환한다.

```java
private static void union(int x, int y) {
  List<Node> unionList = new ArrayList<>();
  int sum = 0;
  Deque<Node> next = new ArrayDeque<>();
  next.offer(new Node(x, y));

  while (!next.isEmpty()) {
    Node cur = next.poll();
    unionList.add(new Node(cur.x, cur.y));
    sum += map[cur.x][cur.y];

    for (int i = 0; i < 4; i++) {
      int nx = cur.x + dx[i];
      int ny = cur.y + dy[i];
      // 맵 밖으로 나가거나 방문 한 경우는 무시
      if (isOutMap(nx, ny) || visited[nx][ny]) {
        continue;
      }

      int diff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);

      // L이상 R이하인지 확인
      if (diff >= L && diff <= R) {
        isUnion = true;
        next.offer(new Node(nx, ny));
        visited[nx][ny] = true;
      }
    }
  }
  // 연합된 국가의 인구를 재분배
  sum /= unionList.size();
  for (Node node : unionList) {
    map[node.x][node.y] = sum;
  }
}
```

- BFS를 이용하여 연합된 국가를 확인하고 탐색이 끝난 후에는 연합된 국가들에 인구를 재분배해준다.

## :black_nib: **Review**

- 일반적인 그래프 탐색 문제에 다른 조건이 추가 된 문제였다.
- 구현문제는 항상 초반 설계가 중요한 것 같다.
