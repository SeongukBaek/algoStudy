# [10282] 해킹

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
Queue<Node> nodes = new PriorityQueue<>(Comparator.comparingInt(value -> value.time));
nodes.add(new Node(start, 0));
// start -> start = 0
times[start] = 0;

while (!nodes.isEmpty()) {
    Node current = nodes.poll();

    // 현재 번호
    int now = current.index;
    // 현재 번호까지 오는 시간
    int time = current.time;

    // 현재 번호로 다시 방문했는데, 이미 더 빠르게 왔다면 패스
    if (times[now] < time) {
        continue;
    }

    for (Node next : dependencyInfo.get(now)) {
        int nextTime = times[now] + next.time;

        if (times[next.index] <= nextTime) {
            continue;
        }

        times[next.index] = nextTime;
        nodes.add(new Node(next.index, nextTime));
    }
}
```

- 이동 시간을 기준으로 하는 우선순위 큐를 사용한다.
- 방문했던 곳이라도, 더 작은 비용으로 방문할 수 있다면 갱신한다.

## :black_nib: **Review**

- 전형적인 다익스트라 문제였는데, 25%에서 메모리 초과가 계속 발생했다.
- 풀이가 특별할 게 없어보였는데 원인을 못 찾아서 다른 풀이를 보니 클래스를 별도로 만들어서 한 번호에서 갈 수 있는 다른 번호만 `List<List<Node>>` 로 저장해서 사용하고 있었다.
  - 나는 `List<int[]>`를 사용해서 한 번호에서 무조건 N개를 다 봤어야 했다...
- 일부러 이너 클래스를 안 만들고 문제를 풀려고 해서 난 사고인 거 같다.
