const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const bellmanFord = (edge) => {
  const n = edge.length;
  const INF = (edge.length - 1) * 10000;
  const dist = Array(n).fill(INF);
  dist[1] = 0;
  let isUpdated;
  for (let i = 0; i < n; i++) {
    isUpdated = false;
    for (let j = 1; j < n; j++) {
      for (const { to, distance } of edge[j]) {
        if (dist[to] > dist[j] + distance) {
          if (i === n - 1) return 'YES';
          dist[to] = dist[j] + distance;
          isUpdated = true;
        }
      }
    }
    if (!isUpdated) return 'NO';
  }
};

const solution = (input) => {
  let idx = 0;
  let TC = +input[idx++];
  let answer = '';
  while (TC--) {
    let [N, M, W] = input[idx++].split(' ').map(Number);
    const edge = Array.from({ length: N + 1 }, () => []);
    while (M--) {
      const [from, to, distance] = input[idx++].split(' ').map(Number);
      edge[from].push({ to, distance });
      edge[to].push({ to: from, distance });
    }
    while (W--) {
      const [from, to, distance] = input[idx++].split(' ').map(Number);
      edge[from].push({ to, distance: distance * -1 });
    }
    const isBack = bellmanFord(edge);
    answer += `${isBack}\n`;
  }
  console.log(answer.trim());
};
solution(input);
