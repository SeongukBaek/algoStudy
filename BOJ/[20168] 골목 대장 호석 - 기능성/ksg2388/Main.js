const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const [N, M, A, B, C] = input.shift().split(' ').map(Number);
const alley = Array.from({ length: N + 1 }, () => []);
const visited = Array.from({ length: N + 1 }, () => false);
let minCost = Infinity;

for (let i = 0; i < M; i++) {
  const [to, from, cost] = input[i].split(' ').map(Number);
  alley[to].push([from, cost]);
  alley[from].push([to, cost]);
}

visited[A] = true;
dfs(A, 0, 0);
console.log(minCost === Infinity ? -1 : minCost);

function dfs(p, totalCost, maxCost) {
  // 가진 돈보다 많이 사용한 경우 종료
  if (totalCost > C) {
    return;
  }
  if (minCost <= maxCost) {
    return;
  }
  // 도착지에 도착한 경우 종료
  if (p === B) {
    minCost = maxCost;
    return;
  }

  for (let next of alley[p]) {
    const [from, cost] = next;
    // 방문한 경우 무시
    if (visited[from]) {
      continue;
    }
    visited[from] = true;
    dfs(from, totalCost + cost, Math.max(maxCost, cost));
    visited[from] = false;
  }
}
