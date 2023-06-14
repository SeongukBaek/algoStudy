# [16724] 피리 부는 사나이

## :pushpin: **Algorithm**

분리 집합, DFS

## :round_pushpin: **Logic**

```javascript
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    // 방문한 경우 무시
    if (visited[i][j] > 0) {
      continue;
    }
    turn++;
    findCycle(i, j);
  }
}
```

- (0, 0) 부터 시작해서 방문하지 않은 곳이라면 `turn`의 수를 늘리고 cycle을 찾는다.

```javascript
// 출발 지점에서 방향을 따라서 이동
function findCycle(x, y) {
  // 맵 밖인 경우 종료
  if (x < 0 || y < 0 || x >= n || y >= m) {
    count++;
    return;
  }

  // 방문한 경우 종료
  if (visited[x][y] > 0) {
    // 이전에 방문한 곳이라면
    if (visited[x][y] < turn) {
      return;
    }
    count++;
    return;
  }

  visited[x][y] = turn;

  if (map[x][y] === 'D') {
    findCycle(x + 1, y);
  }
  if (map[x][y] === 'U') {
    findCycle(x - 1, y);
  }
  if (map[x][y] === 'L') {
    findCycle(x, y - 1);
  }
  if (map[x][y] === 'R') {
    findCycle(x, y + 1);
  }
}
```

- map적혀있는 방향대로 움직이는데 map 밖으로 나가는 경우는 종료시킨다.
- 이미 방문한 곳에 도착한다면 그 위치가 이번 cycle에 방문한 곳이라면 count를 증가시키고 이전에 방문한 곳이라면 count를 증가시키지 않는다.

## :black_nib: **Review**

- 문제를 어떻게 푸는지는 쉽게 생각해냈지만 그 방법을 코드로 어떻게 옮겨야 할지 생각하는데 오래 걸렸다..
- 다음에 비슷한 유형의 문제가 나온다면 쉽게 해결할 수 있을 것 같다!
