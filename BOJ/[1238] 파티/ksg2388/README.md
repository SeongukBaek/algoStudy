# [1238] 파티

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```javascript
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
```

- X에서 다른 마을로 가는 거리 배열을 만든다.
- 그 배열을 뒤집은 값인 다른 마을에서 X로 가는 거리 배열을 만든다.
- 각각의 배열을 다익스트라 알고리즘을 통해 최단거리를 구한다.
- 두 거리를 더한 값이 최대가 되는 값을 구한다.

## :black_nib: **Review**

- 처음에는 모든 점에 대해서 다익스트라 알고리즘을 사용해 모든 거리를 다 구하고 문제를 해결하려고 했다.
- 하지만 생각해보니 기존 배열의 정보를 뒤집어 2번만 다익스트라 알고리즘을 사용하면 되는 문제였다.
