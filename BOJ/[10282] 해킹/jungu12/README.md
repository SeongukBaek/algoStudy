# [10282] 해킹

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
private static void startHacking() {
    Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        return o1[1] - o2[1];
    });
    pq.add(new int[] { C, 0 });
    while (!pq.isEmpty()) {
        int[] cur = pq.poll();
        for (int i = 0; i < dependency[cur[0]].size(); i++) {
            int[] destination = dependency[cur[0]].get(i);
            if (cur[1] + destination[1] < hackedTime[destination[0]]) {
                hackedTime[destination[0]] = cur[1] + destination[1];
                pq.add(new int[] { destination[0], hackedTime[destination[0]] });
            }
        }
    }
}
```

- 우선순위큐를 사용하는 다익스트라로 시간이 오래 걸리는 경로는 나중에 보도록 하여 시간초과를 방지하였다.

## :black_nib: **Review**

- 처음에는 의존도를 2차원 배열로 사용해서 메모리 초과를 봤다.
- 2차원 배열을 리스트로 바꾸니 시간 초과가 났다. (DFS로 풀었음)
- 그제서야 다익스트라를 떠올렸다..
