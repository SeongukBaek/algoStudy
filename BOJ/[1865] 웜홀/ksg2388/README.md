# [1865] 웜홀

## :pushpin: **Algorithm**

벨만 포드

## :round_pushpin: **Logic**

```javascript
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
```

- 모든 정점을 돌면서 최단경로를 갱신하면서 음수 사이클이 있는지 존재한다.
- 모든 정점을 다 검사한 후에 한번 더 갱신을 한 경우 갱신이 가능하다면 그 경우는 음수 사이클이 존재하는 경우이다.

## :black_nib: **Review**

- 벨만 포드 알고리즘에 대해 잘 몰라서 답을 참고했다..ㅎ ㅎ
- 이 문제는 기본 벨만포드 문제와 다르게 음수사이클이 존재 유무만 확인하면 되는 문제였기때문에 노드 간의 단절을 확인해줄 필요가 없었다.
