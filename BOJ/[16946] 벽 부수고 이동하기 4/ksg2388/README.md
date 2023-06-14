# [16946] 벽 부수고 이동하기 4

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```javascript
// 0을 구역별로 나누어 개수 저장
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (map[i][j] === 0) {
      map[i][j] = curNum;
      makeGroup(i, j);
      curNum++;
    }
  }
}

function makeGroup(x, y) {
  let queue = [];
  let count = 1;
  queue.push([x, y]);

  while (queue.length) {
    let cur = queue.shift();

    for (let i = 0; i < 4; i++) {
      let nx = cur[0] + dx[i];
      let ny = cur[1] + dy[i];

      if (isMapIn(nx, ny) && map[nx][ny] === 0) {
        queue.push([nx, ny]);
        map[nx][ny] = curNum;
        count++;
      }
    }
  }
  zeroCount.push(count);
}
```

- BFS를 이용해 탐색하면서 0을 만난 경우 그 0의 그룹을 묶어 개수를 저장해둔다.

```javascript
function checkMove(x, y) {
  let set = new Set();
  let cnt = 1;

  // 상, 하, 좌, 우 방향에 0 그룹이 있는지 확인
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    // 0 그룹이 존재하는 경우
    if (isMapIn(nx, ny) && map[nx][ny] > 1) {
      const id = map[nx][ny] - 1;
      if (!set.has(id)) {
        set.add(id);
        cnt += zeroCount[id];
      }
    }
  }

  return cnt;
}
```

- 다시 BFS를 돌려 벽을 만난 경우 상, 하, 좌, 우를 살펴 0 그룹이 있는지 확인하고 0 그룹이 존재하는 경우 그 그룹의 0의 개수만큼 더해준다.

## :black_nib: **Review**

- 처음에는 벽을 만날 때 마다 BFS를 돌려서 문제를 해결하려고 했는데 그 경우 시간 초과가 날 거 같아서 다른 방법을 생각해봤다.
- 방법이 쉽게 떠오르지 않아서 풀이 방법을 참고했다...
