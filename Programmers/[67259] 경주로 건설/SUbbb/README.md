# [67259] 경주로 건설

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
private void dfs(int prevDir, int x, int y) {
    // 경주로 건설 완료
    if (x == size - 1 && y == size - 1) {
        minCost = Math.min(costs[x][y], minCost);
        return;
    }

    for (int direction = 0; direction < 4; direction++) {
        int nx = x + directions[direction][0];
        int ny = y + directions[direction][1];

        // 다시 원점이거나, 맵 밖이거나, 벽인 경우 패스
        if (nx == 0 && ny == 0 || !isIn(nx, ny) || board[nx][ny] == 1) {
            continue;
        }

        // 건설 비용 계산
        int tmpCost = costs[x][y];
        
        // 방향에 따라 코너 계산
        if (prevDir != -1 && Math.abs(prevDir - direction) % 2 == 1) {
            tmpCost += 500;
        }
        
        // 두 좌표를 잇기 위해서는 직선 도로 하나가 사용됨
        tmpCost += 100;

        // 새로운 건설 비용보다 크거나, 해당 좌표까지의 건설 비용이 아직 갱신되지 않았다면 갱신
        if (costs[nx][ny] >= tmpCost || costs[nx][ny] == 0) {
            costs[nx][ny] = tmpCost;
            dfs(direction, nx, ny);
        }
    }
}
```

- `prevDir` 을 사용해 이전 방향을 저장하고, 직선인지 코너인지 판단한다.
- 모든 경우를 수행해보고, 최소 비용을 갱신한다.

## :black_nib: **Review**

- 생각 외로, 간단한 `DFS` 문제였다. 이전 방향을 DFS의 인자로 전달해주는 것이 중요했던 것 같다.
