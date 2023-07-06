# [154540] 무인도 여행

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
List<Integer> list = new ArrayList<>();
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        if (!visited[i][j] && map[i][j] != 'X') {
            list.add(searchIsland(i, j));
        }
    }
}


public int searchIsland(int x, int y) {
    Deque<int[]> q = new ArrayDeque<>();
    q.offer(new int[] {x, y});
    visited[x][y] = true;
    int count = 0;
    while (!q.isEmpty()) {
        int[] curr = q.poll();
        
        count += map[curr[0]][curr[1]] - '0';
        
        for (int i = 0; i < 4; i++) {
            int nx = curr[0] + dx[i];
            int ny = curr[1] + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] == 'X') continue;
            visited[nx][ny] = true;
            q.offer(new int[] {nx, ny});
        }        
    }
    return count;
}
```

- 연결된 구역의 개수를 반환한다.
- 반환된 값을 list에 넣어서 오름차순으로 출력한다

## :black_nib: **Review**

- 오랜만에 이런 bfs 문제 쉽고 재밌었다!
