# [1167] 트리의 지름

## :pushpin: **Algorithm**

트리, DFS

## :round_pushpin: **Logic**

```java
if (depth == 1) {
  start = cur;
}
```

- 트리의 정보를 입력 받으면서 리프노드인 경우의 위치를 저장해둔다.

```java
visited = new boolean[v + 1];
visited[start] = true;
dfs(start, 0);

visited = new boolean[v + 1];
visited[nextPoint] = true;
dfs(nextPoint, 0);
```

- DFS를 이용해 시작 노드에서 가장 먼 거리에 있는 노드를 찾고 그 위치를 `nextPoint`에 저장해둔다.
- `nextPoint`에서 다시 DFS를 이용해 가장 먼 거리에 있는 노드를 찾아 그 거리를 저장한다.

## :black_nib: **Review**

- 저번에 비슷한 문제를 풀어봐서 바로 그 방법을 적용했더니 풀렸다.
- 트리의 지름을 구하는 방식 기억해둬야겠다.
