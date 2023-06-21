# [154540] 무인도 여행

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
void bfs(int i, int j) {
    Queue<int[]> que = new LinkedList<>();
    int countFood = map[i][j];
    visited[i][j] = true;
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, -1, 0, 1 };

    que.add(new int[] {i, j});

    while(!que.isEmpty()) {
        int[] cur = que.poll();

        for (int dir = 0; dir < 4; dir++) {
            int nx = cur[0] + dx[dir];
            int ny = cur[1] + dy[dir];

            if(!isIn(nx, ny)) {
                continue;
            }

            if(map[nx][ny] != 0 && !visited[nx][ny]) {
                countFood += map[nx][ny];
                visited[nx][ny] = true;
                que.add(new int[] {nx, ny});
            }
        }
    }
    answer.add(countFood);
}
```

- 방문하지 않은 칸에 도착 했다면, 아직 계산을 하지 않은 무인도다.
- 그렇다면 bfs를 돌려 계산을 하고 방문처리를 해주었다.

## :black_nib: **Review**

- 단순한 bfs 문제였다!
