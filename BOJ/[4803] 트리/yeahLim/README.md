# [4803] 트리

## :pushpin: **Algorithm**

Union-Find

## :round_pushpin: **Logic**

```java
 /* union */
static void union(int nodeA, int nodeB) {
    // nodeA < nodeB로 만들기
    if(nodeA > nodeB) {
        int tmp = nodeA;
        nodeA = nodeB;
        nodeB = tmp;
    }
    int rootA = findRoot(nodeA);
    int rootB = findRoot(nodeB);
    // 같은 집합이거나, 두 노드 중 하나가 트리가 아닐 경우
    if(rootA == rootB || !isTree[rootA] || !isTree[rootB]) {
        isTree[rootA] = isTree[rootB] = false;
    }
    tree[rootB] = rootA;
}
```

- union함수를 통해 두 노드를 한 트리로 연결 시켜준다.
  - 만약, 이미 두 노드가 같은 집합이거나
  - 두 노드 중 한 노드가 이미 트리가 아니라면 (싸이클이 존재한다면)
- isTree에 false를 대입한다.

```java
for(int i=1; i<=n; i++) {
    int root = findRoot(i);
    // 루트노드의 개수 세기 (루트 노드의 개수 == 트리의 개수)
    if(isTree[root]) {
        isTree[root] = false;
        answer++;
    }
}
```

- 최종적으로 각 노드의 루트 노드의 개수를 세주면 곧, 트리의 개수가 된다.

## :black_nib: **Review**

- 문제를 보자마자 Union-Find로 풀었다가 수많은 실패를 겪고, 힌트를 보았다..! 트리의 개수를 어떻게 세야할지 여러가지 고민을 많이 했는데, 루트 노드의 개수가 곧 트리의 개수라는 점을 이용하면 된다는 것을 다시 배웠다!!!! 꺠먹지맙시덩!
