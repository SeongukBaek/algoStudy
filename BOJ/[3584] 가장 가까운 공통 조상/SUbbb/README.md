# [3584] 가장 가까운 공통 조상

## :pushpin: **Algorithm**

LCA, DP

## :round_pushpin: **Logic**

```java
private static void fillParent(int currentNode, int currentHeight, int parent) {
    heights[currentNode] = currentHeight;
    parents[currentNode] = parent;

    for (int nextNode : treeInfo[currentNode]) {
        // 부모 노드인 경우는 패스
        if (nextNode == parent) {
            continue;
        }

        fillParent(nextNode, currentHeight + 1, currentNode);
    }
}
```

- 각 노드의 높이와, 1번째 부모 노드 정보를 저장한다.

```java
private static int lca(int nodeA, int nodeB) {
    int aHeight = heights[nodeA];
    int bHeight = heights[nodeB];

    // 두 노드의 높이가 같아질 때까지, 높이가 낮은 노드 기준으로 맞춘다.
    while (aHeight > bHeight) {
        nodeA = parents[nodeA];
        aHeight--;
    }

    while (bHeight > aHeight) {
        nodeB = parents[nodeB];
        bHeight--;
    }

    // 두 노드의 높이를 같이 낮추면서, LCA를 찾는다.
    while (nodeA != nodeB) {
        nodeA = parents[nodeA];
        nodeB = parents[nodeB];
    }

    return nodeA;
}
```

- 두 노드의 높이가 같아질 때까지 일종의 커서(nodeA, nodeB)를 이동시킨다.
- 이후, 두 노드를 함께 이동시키면서, 공통 조상을 찾는다.

## :black_nib: **Review**
- 입력값이 O(N^2)로도 가능해서, DFS만을 사용할 수 있었지만, LCA에 대해 알아보려고 해당 알고리즘을 참고해서 구현했다.