# [1167] 트리의 지름

## :pushpin: **Algorithm**

트리, DFS

## :round_pushpin: **Logic**

```java
visited[1] = true;
searchTreeDiameter(1, 0); // 임의의 한 노드(노드 1)에서 가장 멀리 있는 노드 구하기
visited = new boolean[n];
visited[endNode] = true;
searchTreeDiameter(endNode, 0); // 가장 멀리있는 노드에서 가장 멀리 있는 노드 구하기
```

- 한 노드에서 가장 멀리있는 노드(endNode)를 구한다.
- endNode에서 가장 멀리있는 노드를 구한다.

```java
/* DFS : 트리의 지름 구하기 */
private static void searchTreeDiameter(int node, int diameter) {
    if(answer < diameter) {
        answer = diameter;
        endNode = node;
    }

    for(int i=0; i<tree[node].size(); i++) {
        int[] infor = tree[node].get(i);
        if(visited[infor[0]]) continue;
        visited[infor[0]] = true;
        searchTreeDiameter(infor[0], diameter + infor[1]);
    }
}
```

-

## :black_nib: **Review**

- 트리의 지름 구하는 방법
  - 임의의 노드에서 가장 먼 노드까지 dfs를 돌린다.
  - 찾은 가장 먼 노드에서 다시 가장 먼 노드까지 DFS를 돌린다.
  - 그 거리가 지름(가장 거리가 먼 두 노드의 간선 길이)이다.
- 트리에서 한 번 방문한 곳은 재방문할 필요가 없기 때문에 true처리 한다.
- DFS에서도 return하고 visited를 false처리해주는지 주의하자.
