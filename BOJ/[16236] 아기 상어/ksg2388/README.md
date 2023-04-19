# [16236] 아기 상어

## :pushpin: **Algorithm**

구현, 그래프 이론, 그래프 탐색, BFS, 시뮬레이션

## :round_pushpin: **Logic**

```javascript
while (q.length) {
  const [y, x, cnt] = q.shift();

  // 먹이 발견
  if (map[y][x] < lv && map[y][x] !== 0) {
    smallq.push([y, x, cnt]);
  }

  for (let i = 0; i < 4; i++) {
    const ny = y + dy[i];
    const nx = x + dx[i];

    // 범위 체크
    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

    // 물고기 레벨 체크
    if (map[ny][nx] > lv) continue;

    // 방문 체크
    if (visited[ny][nx]) continue;

    q.push([ny, nx, cnt + 1]);
    visited[ny][nx] = true;
  }
}
```

- 이동할 수 있는 방향으로 이동하면서 물고기가 존재하는 경우 잡아먹는다.

```javascript
if (smallq.length) {
  // 거리 => 위쪽 => 왼쪽으로 정렬.
  smallq.sort((a, b) => {
    if (a[2] !== b[2]) {
      return a[2] - b[2];
    } else {
      if (a[0] !== b[0]) {
        return a[0] - b[0];
      } else {
        return a[1] - b[1];
      }
    }
  });

  // feed의 위치와 거리
  const [fy, fx, fCnt] = smallq[0];

  map[fy][fx] = 0;
  eats[lv]++;

  // 레벨 업
  if (eats[lv] === lv) {
    lv++;
  }
  cur = [fy, fx];
  movCnt = fCnt;
}
```

- 잡아먹을수 있는 물고기 중 가장 거리, 위쪽, 왼쪽을 우선순위로 정하여 물고기를 잡아먹는다.
- 잡아먹은 물고기의 수가 자기 몸집과 같아진다면 크기를 올려준다.

## :black_nib: **Review**

- 주어진 조건대로만 진행하면 되는 간단한 구현문제였다.
- 자바스크립트를 이용하다보니 우선순위큐를 이용하기 어려웠다.
