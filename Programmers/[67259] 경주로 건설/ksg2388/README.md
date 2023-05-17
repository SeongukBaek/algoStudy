# [67259] 경주로 건설

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```javascript
if (board[0][1] === 0) {
  queue.push({ x: 0, y: 1, dir: 2, price: 100 });
  visited[0][1][2] = 100;
}
if (board[1][0] === 0) {
  queue.push({ x: 1, y: 0, dir: 3, price: 100 });
  visited[1][0][3] = 100;
}
```

- 처음 움직일 수 있는 오른쪽, 아래쪽 방향에 대한 정보를 `queue`에 넣어두고 시작한다.

```javascript
while (queue.length) {
  let qSize = queue.length;
  for (let i = 0; i < qSize; i++) {
    let { x, y, dir, price } = queue.pop();
    // 갈 수 있는 방향 확인
    for (let j = 0; j < 4; j++) {
      let nx = x + dx[j];
      let ny = y + dy[j];

      // 맵밖이거나 벽인 경우 무시
      if (isMapOut(nx, ny) || board[nx][ny] === 1) {
        continue;
      }
      // 방향 그대로 갈 수 있는 경우
      if (eqlDir(dir, j)) {
        if (visited[nx][ny][j] >= price + 100) {
          queue.push({ x: nx, y: ny, dir: j, price: price + 100 });
          visited[nx][ny][j] = price + 100;
          continue;
        }
      }
      // 방향을 틀어야하는 경우
      if (visited[nx][ny][j] >= price + 600) {
        queue.push({ x: nx, y: ny, dir: j, price: price + 600 });
        visited[nx][ny][j] = price + 600;
      }
    }
  }
}
```

- 이후 4방향 탐색을 하며 갈 수 있는 위치로 움직인다.
- 가는 곳이 현재 내가 가진 비용보다 작은 경우는 이동하지 못하는 것으로 간주한다.

## :black_nib: **Review**

- BFS를 이용하여 푸는 문제라는 것은 바로 떠올랐지만 `visited`배열을 처음에 2차원으로만 사용하려고 하는 과정에서 문제가 발생해 오래걸린 문제였다.
- 2차원으로만 해결하려고 했는데 예외가 있다는 것을 찾아내지 못하고 결국 조언을 받았다...
