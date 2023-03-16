# [118669] 등산코스 정하기

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
for (int[] path : paths) {

    // 산봉우리가 포함될 때
    if (gateSet.contains(path[0]) || summitSet.contains(path[1])) {
        nodes[path[0]].add(new Node(path[1], path[2])); // 단방향만 추가
    }

    else if (gateSet.contains(path[1]) || summitSet.contains(path[0])) {
        nodes[path[1]].add(new Node(path[0], path[2])); // 단방향만 추가
    }

    // 포함되지 않을 때
    else {
        nodes[path[0]].add(new Node(path[1], path[2])); // 양방향 모두 추가
        nodes[path[1]].add(new Node(path[0], path[2]));
    }
}
```

- 최소의 intensity를 찾아야하고, '입구 -> 산봉우리' / '산봉우리 -> 입구'의 경로의 최소 intensity는 똑같기 때문에, 산봉우리가 포함될때는 '입구 -> 산봉우리'만 고려한다.

```java
for(int gate: gates) {
    q.add(new int[] {gate, 0});
    d[gate] = 0; // 입구 초기 intensity값 설정
}

while(!q.isEmpty()) {
    int[] data = q.poll();
    int edge = data[0];
    int intensity = data[1];

    // intensity가 더 크면 넘어간다
    if(d[edge] < intensity) continue;

    // 작으면 그 다음 경로를 찾는다
    for(Node nd : nodes[edge]) {
        int dist = Math.max(d[edge], nd.intensity); // intensity 업데이트
        // 현재 intensity보다 다음 경로의 intensity가 더 작으면
        if(d[nd.edge] > dist) {
            d[nd.edge] = dist; // 그 경로의 intensity 업데이트
            q.add(new int[] {nd.edge, dist});
        }
    }
}
```

- 출발지인 입구를 모두 큐에 올려주고, 각 입구를 시작으로 intensity가 작은 경로를 찾는다.
- 각 노드로부터 갈 수 있는 경로를 하나 하나 확인해서, intensity가 현재 intensity보다 작은 경우 큐에 추가해준다.

## :black_nib: **Review**

- 다익스트라 어렵다. 그래프용 dp + dfs 같다는 느낌을 받았다..! 더 공부해야한ㄷ으ㅏ아!
