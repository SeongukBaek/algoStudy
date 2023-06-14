# [10282] 해킹

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
function bfs(startNode, graph, distances) {
  // node 정보, 해당 노드까지의 최단거리를 q에 넣는다.
  // 우선순위 큐 : 무조건 FIFO이 아니라 우선순위가 높은 순부터 (여기서는 최단거리순으로)
  const q = [[0, startNode]];
  q.sort((a, b) => a[0][0] - b[0][0]);
  distances[startNode] = 0;
  while (q.length > 0) {
    const [dist, node] = q.shift();
    // 최단거리보다 현재 dist가 크면 무시
    if (distances[node] < dist) continue;

    const new_nodes = graph.get(node);
    for (let i in new_nodes) {
      const element = new_nodes[i];
      const cost = dist + element[1];
      // 새로운 최단거리가 나타나면
      if (distances[element[0]] > cost) {
        distances[element[0]] = cost;
        q.push([cost, element[0]]);
      }
    }
  }
}
```

- 기존에 연결되어있는 경로보다 경유해서 이동하는 경로가 더 짧아지는 경우 거리 정보를 업데이트 해준다.
- 조건 만족을 위해, 첫번째 집을 칠할 색을 고정해두고, 다른 색을 칠하는 비용은 최댓값으로 초기화한다.

## :black_nib: **Review**

- 문제를 읽자마자 최단경로를 이용하는 문제라는 것을 바로 알았다.
- 하지만 자바스크립트에서는 직접 힙을 구현하는게 쉽지 않아서 일단 풀이방법을 참고했지만 여기도 힙을 사용하지 않고 정렬을 이용하였다.
- 힙을 직접 구현해서 최적화 시켜 다시 풀어봐야겠다.
