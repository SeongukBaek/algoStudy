# [118669] 등산코스 정하기

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
for (int[] path : paths) {
    // 출발점이거나, 산봉우리라면 단방향 지정
    if (nodeInfo[path[0]] == 1 || nodeInfo[path[1]] == 2) {
        adjInfo.get(path[0]).add(new Node(path[1], path[2]));
    }
    // 산봉우리거나, 출발점이라면 단방향 지정
    else if (nodeInfo[path[1]] == 1 || nodeInfo[path[0]] == 2) {
        adjInfo.get(path[1]).add(new Node(path[0], path[2]));
    } else {
        adjInfo.get(path[0]).add(new Node(path[1], path[2]));
        adjInfo.get(path[1]).add(new Node(path[0], path[2]));
    }
}
```

- 출발점이나 산봉우리의 경우는 단방향으로 인접 리스트 저장

```java
Queue<Node> nodes = new LinkedList<>();
for (int gate : gates) {
    nodes.add(new Node(gate, 0));
    // 출발점의 intensity는 0
    intensitys[gate] = 0;
}

while (!nodes.isEmpty()) {
    Node current = nodes.poll();

    // 이미 해당 정점까지의 intensity보다 큰 경우는 패스
    if (current.time > intensitys[current.to]) {
        continue;
    }

    for (Node next : adjInfo.get(current.to)) {
        // intensity 갱신
        // 현재 정점까지의 intensity와 현재 정점에서 갈 수 있는 다음 정점까지의 intensity 중 큰 값을 저장
        int intensity = Math.max(intensitys[current.to], next.time);

        // 현재 정점에서 갈 수 있는 다음 정점까지의 intensity보다 위에서 갱신한 값이 더 작다면,
        // 최소 intensity 갱신 -> next.to까지의 intensity를 최소로 갱신해주고 큐에 삽입
        if (intensitys[next.to] > intensity) {
            intensitys[next.to] = intensity;
            nodes.add(new Node(next.to, intensity));
        }
    }
}
```

- 모든 출발점을 큐에 넣어두고 시작한다.
- 큐를 탐색하면서, 현재 정점에서 갈 수 있는 정점들에 대한 최소 intensity를 갱신한다.

## :black_nib: **Review**

- 처음 접근은 모든 출발점에서 다익스트라를 수행하면서 최소 intensity를 구하는 방식으로 구현했다.
- 시간초과와 몇 개의 테스트케이스는 틀려서 최적화할 방법을 생각해봤지만 .. 떠오르지 않아서 풀이를 참고했다.
