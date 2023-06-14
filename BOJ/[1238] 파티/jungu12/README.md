# [1238] 파티

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

- 입력을 받으면서 정방향 그래프, 역방향 그래프 모두 리스트 배열에 저장해주었다.
- 파티에 가는데 걸리는 시간은 파티 장소에서 다익스트라로, 파티에서 집으로 오는데 걸리는 시간은 집에서 다익스트라로 구하는게 최적의 방법이기 때문이다!

```java
private static int[] dijkstra(List<int[]>[] roadInfos) {
    Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    boolean visited[] = new boolean[N + 1];
    int distance[] = new int[N + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[X] = 0;

    pq.add(new int[] { X, 0 });

    while(!pq.isEmpty()) {
        int[] cur = pq.poll();

        if(visited[cur[0]]) {
            continue;
        }

        visited[cur[0]] = true;
        for (int[] roadInfo : roadInfos[cur[0]]) {
            if(!visited[roadInfo[0]] && distance[roadInfo[0]] > distance[cur[0]] + roadInfo[1]) {
                distance[roadInfo[0]] = distance[cur[0]] + roadInfo[1];
                pq.add(new int[] { roadInfo[0], distance[roadInfo[0]] });
            }
        }
    }
    return distance;
}
```

- 출발지에서 목적지까지 가는데 걸리는 시간에 대한 정보를 담은 배열을 반환하는 메소드다
- 다익스트라로 시간을 구하였는데, 다익스트라를 쓰기 위해 우선 순위 큐를 시간에 대한 오름차순으로 정렬하여 사용하였다.

```java
private static void findMax() {
    int rightResult[] = dijkstra(rightRoadInfo);
    int reverseResult[] = dijkstra(reverseRoadInfo);
    int max = 0;
    for (int i = 1; i <= N; i++) {
        max = Math.max(max, rightResult[i] + reverseResult[i]);
    }

    System.out.println(max);
}
```

- 어떤 학생이 오고 가는데 가장 시간이 많이 걸리는지 찾는 메소드다.

## :black_nib: **Review**

- 단방향 역방향 그래프를 다 저장해야 하는 이유가 잘 이해되지 않았다..
- 다익스트라를 오랜만에 푸니 헷갈렸다..
