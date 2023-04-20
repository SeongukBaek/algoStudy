# [1865] 웜홀

## :pushpin: **Algorithm**

벨만 포드

## :round_pushpin: **Logic**

```java
int[] costs = new int[N];
Arrays.fill(costs, INF);

costs[0] = 0;

// 0번 정점부터, N - 1개의 정점만을 시작 정점으로,
// 어차피 모든 정점을 방문하기 위해서는 N - 1번의 탐색만 하면 됨
for (int start = 0; start < N - 1; start++) {
    for (int to = 0; to < N; to++) {
        for (int[] nextInfo : adjList[to]) {
            int next = nextInfo[0];
            int cost = nextInfo[1];

            // next까지 가는 비용이 기존 next의 비용보다 작다면 갱신
            if (costs[to] + cost < costs[next]) {
                costs[next] = costs[to] + cost;
            }
        }
    }
}
```

- N - 1개의 정점으로부터 모든 다른 정점을 거쳐 모든 정점까지의 최소 거리를 갱신한다.

```java
for (int start = 0; start < N; start++) {
    for (int[] nextInfo : adjList[start]) {
        int to = nextInfo[0];
        int cost = nextInfo[1];

        // 출발점부터 to까지 가는 비용이 기존 to의 비용보다 작다면 음수 사이클 존재 !
        if (costs[start] + cost < costs[to]) {
            return true;
        }
    }
}

return false;
```

- 각 정점까지의 N - 1번 탐색으로 최소 경로를 구한 후에, 한 번 더 탐색을 수행한다.
- 이때 기존 값에서 갱신이 되는 경우가 발생하면 음의 사이클이 존재하게 되는 경우이다.

## :black_nib: **Review**

- 벨만 포드 알고리즘은 생소해서 먼저 알고리즘을 알아본 후에 구현했다.
  - 다익스트라와 비슷하지만, 음의 가중치가 있는 경우의 최단 경로를 구할 때 사용한다!
  - 그리고 여기서 음의 가중치로 인한 음의 사이클이 존재하는 경우가 웜홀을 통해 시간 역행이 가능한 경우임을 파악할 수 있었다.