# [4803] 트리

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
static void dfs(int idx) {
  for (int next : node.get(idx)) {
    // 방문했던 곳을 다시 방문시 사이클이 형성되므로 트리 X
    if (visited[next]) {
      isTree = false;
      return;
    }
    node.get(next).remove(idx);
    visited[next] = true;
    dfs(next);
  }
  return;
}
```

- 방문했던 곳을 재방문 시 사이클이 형성되므로 트리가 아니게 된다.
- 다음 위치를 방문하는 경우 그 이전 값과 연결되있던 정보를 다음 위치의 값에서 지워준다.

## :black_nib: **Review**

- 여러가지 방법으로 풀 수 있는 문제 같은데 자신있는 DFS를 이용하여 문제를 풀었다.
- 여러가지 알고리즘을 알아두어야겠다고 생각했다.
