# [118669] 등산코스 정하기

## :pushpin: **Algorithm**

다익스트라 알고리즘

## :round_pushpin: **Logic**

```java
Queue<Node> nextCourse = new PriorityQueue<>((o1, o2) -> {
    return o1.w - o2.w;
});
```

- 우선순위 큐를 이용하여 거리가 짧은 순으로 먼저 탐색.

```java
for (int gate : gateSet) {
    nextCourse.offer(new Node(gate, 0));
    intensity[gate] = 0;
}
```

- 모든 출발점을 큐에 넣어두고 시작한다.

```java
while (!nextCourse.isEmpty() && count > 0) {
    Node cur = nextCourse.poll();
    // 산인 경우 무시
    if (summitSet.contains(cur.num)) {
        count--;
        continue;
    }
    for (Node next : location.get(cur.num)) {
        // 게이트인 경우 무시
        if (gateSet.contains(next.num)) {
            continue;
        }

        int dist = Math.max(intensity[cur.num], next.w);
        // 지금 갈 수 있는 거리보다 짧은 경우
        if (intensity[next.num] > dist) {
            intensity[next.num] = dist;
            nextCourse.add(new Node(next.num, dist));
        }
    }
}
```

- 산인 경우 큐에 담지 않고 방문해야할 산의 개수인 count를 줄여준다.
- 입구의 경우는 큐에 담지 않고 무시한다.
- 새로 현재 지점의 intensity값과 이동한 지점의 가중치를 비교하여 더 높은 값으로 dist를 설정한다.
- 이후 이동할 지점의 intensity보다 dist가 작은 경우 값을 갱신해준다.

## :black_nib: **Review**

- 처음 문제를 보고 다익스트라가 생각났지만 일반적인 다익스트라 알고리즘으로는 문제가 풀리지 않았다.
- 문제의 조건을 잘 확인해보니 intensity를 어떻게 비교하느냐가 관건인 것 같아서 그 부분을 잘 생각하여 적용하니 문제가 풀렸다.
