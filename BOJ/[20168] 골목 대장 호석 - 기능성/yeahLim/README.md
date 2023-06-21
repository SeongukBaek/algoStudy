# [20168] 골목 대장 호석 - 기능성

## :pushpin: **Algorithm**

백트래킹

## :round_pushpin: **Logic**

```java
for (int index = 0; index < M; index++) {
    line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int from = line[0] - 1;
    int to = line[1] - 1;
    int cost = line[2];

    roadInfo.get(from).add(new Node(to, cost));
    roadInfo.get(to).add(new Node(from, cost));
}
```

- 인접 정보를 양방향으로 저장한다.

```java
private static void searchRoute(int current, int cost, int max) {
    // 이미 방문한 경우
    if (visited[current]) {
        return;
    }
    // 낼 수 있는 비용보다 커질 경우
    if (cost > c) {
        return ;
    }
    // 목표점에 도달했을 경우
    if (current == b) {
        minCost = Math.min(minCost, max);
        return;
    }
    visited[current] = true;
    for (int i = 1; i <= n; i++) {
        if (roads[current][i] == 0) continue;
        searchRoute(i, cost + roads[current][i], Math.max(max, roads[current][i]));
    }
    visited[current] = false;
}
```

- 백트래킹으로 갈 수 있는 모든 경로를 탐색한다.
- 이때, 이미 방문했거나, 낼 수 있는 비용보다 커질 경우 돌아간다.

## :black_nib: **Review**
- 처음에 시간초과가 났는데, visited처리를 깜빡해서였다.. 백트래킹할 때도 visited처리하는 것을 잊지말자.
- 그리고 백트래킹에서 return을 만나 돌아올 때, visited를 false처리하는 것을 잊어버려서 테케 일부가 틀렸었다. 재귀를 쓸때 반환하는 경우를 주의해주자!