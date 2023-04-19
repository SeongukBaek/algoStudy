# [1865] 웜홀

## :pushpin: **Algorithm**

벨만 포드

## :round_pushpin: **Logic**

```java
boolean isMinusCycle = false;
Arrays.fill(dist, INF);
dist[1] = 0;

// 노드의 개수 - 1 만큼 반복
for(int i=1; i<n; i++) {
    for(int j=1; j<roads.length; j++) {
        for(Road road : roads[j]) {
            dist[road.dest] = Math.min(dist[road.dest], dist[j] + road.time);
        }
    }
}

// 값이 갱신되면 음수 사이클 존재
for(int j=1; j<roads.length; j++) {
    for(Road road : roads[j]) {
        if(dist[road.dest] > dist[j] + road.time) {
            isMinusCycle = true;
        }
    }
}
```

- 각 정점에서 모든 정점으로 가는 최소값을 갱신한다
- 위를 n-1번 반복한다.

- 마지막으로 '각 정점에서 모든 정점으로 가는 최소값을 갱신한다'
- 이 때, 값이 갱신 되었을 경우 사이클이 있다고 판단한다.
- 조건에서 더 작은 값으로 갱신되었기 때문에 음의 사이클이라고 판단한다.

## :black_nib: **Review**

- 벨만-포드 알고리즘을 처음으로 구현해보았다! 음의 사이클을 판단하는 부분이 신기했다.
- 이 문제는 음의 사이클만 찾으면 되는 코드로 임의의 한 점에서 사이클이 있는지 판단할 수 있는지 알 수 없기 때문에, `dist[v] != INF`조건을 없애야 한다.
