# [4803] 트리

## :pushpin: **Algorithm**

Union-Find

## :round_pushpin: **Logic**

```java
for (int index = 0; index < m; index++) {
  st = new StringTokenizer(br.readLine());

  int left = Integer.parseInt(st.nextToken());
  int right = Integer.parseInt(st.nextToken());

  int leftParent = findParent(left);
  int rightParent = findParent(right);
  // 이미 둘 중 한 노드가 싸이클에 속한다면 다른 노드 또한 싸이클 마킹
  if (leftParent == -1) {
    parents[right] = -1;
    markCycle(rightParent);
    continue;
  }
  if (rightParent == -1) {
    parents[left] = -1;
    markCycle(leftParent);
    continue;
  }
  // 이미 부모가 같다면 해당 부모 노드는 싸이클이 존재함
  if (leftParent == rightParent) {
    // 해당 부모 노드를 부모로 가지는 모든 노드의 parents 값을 -1로 갱신
    markCycle(leftParent);
    continue;
  }

  // 나머지는 연결
  union(left, right);
}
```

- Union-Find를 통해 싸이클의 존재 여부를 확인한다.
  - 싸이클이 존재한다면 이를 마킹하고, 그렇지 않다면 연결시켜준다.

```java
int treeCount = computeTree();

if (treeCount == 0) {
  answer.append("No trees");
}
if (treeCount == 1) {
  answer.append("There is one tree");
}
if (treeCount > 1) {
  answer.append("A forest of ").append(treeCount).append(" trees");
}
```

- 싸이클임을 의미하는 -1을 제외한 값들을 모두 집합으로 삽입한다.
- 중복이 제거된 후 집합의 크기는 트리의 개수이다.

## :black_nib: **Review**
- 보자마자 Union-Find로 연결성을 확인해주면 되는 문제임을 알았다.
- Path Compression을 사용했고, 갱신해야 하는 노드들 모두를 함께 갱신해줘야 하는 코드가 뭔가 비효율적인 것 같아 마음에 들지 않는다...