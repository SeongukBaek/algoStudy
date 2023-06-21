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
private static void findPath(Node current, int allCost, int maxCost, boolean[] isVisited) {
    maxCost = Math.max(maxCost, current.cost);

    if (current.to == end) {
        if (allCost <= money) {
            minMoney = Math.min(minMoney, maxCost);
        }
        return;
    }

    isVisited[current.to] = true;
    for (Node next : roadInfo.get(current.to)) {
        if (isVisited[next.to]) {
            continue;
        }
        findPath(next, allCost + next.cost, maxCost, isVisited);
    }
    isVisited[current.to] = false;
}
```

- 재귀함수를 통해 백트래킹을 구현한다.
- 전달되는 인자는
  - 현재 방문한 노드
  - 현재 방문한 노드까지 오는 총 비용
  - 현재 방문한 노드까지 오는 비용 중 최대 비용
  - 해당 경로의 방문 여부

## :black_nib: **Review**
- 노드 개수가 많지 않아서 다익스트라도 가능하지만, 백트래킹이 가능해보였고 더 구현이 쉬울 것 같아 백트래킹을 사용했다.
- 첫 풀이 시, 16개의 테케만 맞았는데, 다시 보니, 백트래킹 시 중요한 방문한 이후, 다시 `false` 처리하는 코드가 없어서였다.