const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [n, m] = input.shift().split(' ').map(Number);
const map = input.map((line) => line.split(''));
const visited = Array.from(new Array(n), () => new Array(m).fill(0));
let turn = 0;
let count = 0;

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

console.log(count);

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
