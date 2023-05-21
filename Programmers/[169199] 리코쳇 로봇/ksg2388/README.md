# [169199] 리코쳇 로봇

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```javascript
// 벽에 부딛힐 때까지 이동
while (true) {
  if (
    isMapOut(nx + dx[i], ny + dy[i]) ||
    board[nx + dx[i]][ny + dy[i]] === 'D'
  ) {
    break;
  }
  nx += dx[i];
  ny += dy[i];
}
// 방문한 적이 있는 경우 무시
if (visited[nx][ny]) {
  continue;
}
if (board[nx][ny] === 'G') {
  return;
}
isMove = true;
visited[nx][ny] = true;
queue.push([nx, ny]);
```

- 벽을 만나거나 맵 밖으로 나갈때까지 이동한다.
- 이동이 끝나면 그 위치에 방문한 적이 있는지 확인하고 있는 경우는 무시한다.
- 도착점에 도착하면 종료하고 아닌 경우는 계속해서 탐색한다.

## :black_nib: **Review**

- 기존 BFS에서 변형된 문제였는데 생각보다 쉽지 않았다.
- 인덱스 접근 에러가 많이나서 고생했다...
