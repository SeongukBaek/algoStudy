# [154540] 무인도 여행

## :pushpin: **Algorithm**

union-find, 구현

## :round_pushpin: **Logic**

```java
void calcCanStayDate() {
    for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[0].length; j++) {
            if(map[i][j] != 0 && !visited[i][j]) {
                bfs(i, j);
            }
        }
    }
}

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

- map 전체를 돌면서 식량이 있고 방문하지 않은 곳인 새로운 무인도를 찾는다.
- 해당 좌표에서 BFS를 돌면서 무인도의 총 식량을 구하여 answer에 넣어준다.
- answer를 sort해서 출력한다.

## :black_nib: **Review**

- 간단한 BFS 문제였다.
