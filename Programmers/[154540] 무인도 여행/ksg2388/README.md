# [154540] 무인도 여행

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```javascript
function findIsLand(map, x, y) {
  const queue = [];
  let count = +map[x][y];
  map[x][y] = 'X';
  queue.push([x, y]);

  while (queue.length) {
    const [cx, cy] = queue.shift();

    for (let i = 0; i < 4; i++) {
      const nx = cx + dx[i];
      const ny = cy + dy[i];

      // 주변에 무인도가 없는 경우 무시
      if (isMapOut(nx, ny) || map[nx][ny] === 'X') continue;
      // 무인도가 있는 경우 큐에 추가
      count += +map[nx][ny];
      map[nx][ny] = 'X';
      queue.push([nx, ny]);
    }
  }
  return count;
}
```

- BFS를 이용하여 주변 무인도의 식량의 합을 카운트한다.

## :black_nib: **Review**

- 그냥 간단한 기본 BFS 문제였다.
- BFS Master ~~
