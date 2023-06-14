const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let inputs = fs.readFileSync(filePath).toString().trim().split('\n');

const [n, m, x] = inputs[0].split(' ').map(Number);
const forwardGraph = Array.from({ length: n + 1 }, () => Array()); // X에서 모든 마을로 가는 거리 배열
const backGraph = Array.from({ length: n + 1 }, () => Array()); // 모든 마을에서 X로 가는 거리 배열

for (let i = 1; i <= m; i++) {
  const [from, to, time] = inputs[i].split(' ').map(Number);
  forwardGraph[from].push([to, time]);
  backGraph[to].push([from, time]);
}

const dijkstra = (graph) => {
  const times = Array(n + 1).fill(Infinity);
  times[x] = 0;

  const queue = [[x, 0]];
  while (queue.length) {
    const [from, minTimeToHere] = queue.shift();
    for (const [to, time] of graph[from]) {
      if (times[to] > minTimeToHere + time) {
        times[to] = minTimeToHere + time;
        queue.push([to, times[to]]);
      }
    }
  }
  return times;
};

const forwardTimes = dijkstra(forwardGraph);
const backTimes = dijkstra(backGraph);

let max = 0;
for (let i = 1; i <= n; i++) {
  max = Math.max(max, forwardTimes[i] + backTimes[i]);
}
console.log(max);
