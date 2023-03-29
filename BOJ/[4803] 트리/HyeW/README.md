# [4803] 트리

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private static int getTreeCount() {
    int treeCnt = 0;
    visited = new boolean[n+1];

    for(int node = 1; node <= n; node++) {
        if(visited[node]) {
            continue;
        }
        if(isTree(node)) {
            treeCnt++;
        }
    }
    return treeCnt;
}
```
모든 노드를 시작점으로 생각해 트리인지 아닌지 확인한다.<br/><br/>

```java
/*트리가 되는지 아닌지 확인하기*/
private static boolean isTree(int node) {
    boolean tree = true;
    Deque<Integer> graph = new ArrayDeque<>();
    // 부모 노드 저장하기
    Map<Integer, Integer> parent = new HashMap<>(); 
    graph.add(node);
    // 시작점의 부모는 -1로 설정한다.
    parent.put(node, -1); 
    visited[node] = true;

    while (!graph.isEmpty()) {
        int cur = graph.poll();

        for (int nxt : nodes.get(cur)) {
            if (visited[nxt]) {
                // 부모노드인지 판단 (1->2 순으로 이미 확인을 해서 2->1로 다시 체크를 안하기 위해)
                if (parent.get(cur) != nxt) { 
                    // 순환 그래프임을 표시
                    tree = false;
                }
            } else {
                graph.add(nxt);
                parent.put(nxt, cur); 
            }
            visited[nxt] = true;
        }
    }
    return tree;
}
```
- 현재 노드에서 연결된 자식 노드가 있다면 부모 노드를 현재 노드로 저장하고 큐에 넣는다.<br/>
- 방문한 노드인데 부모노드가 아니라면 순환을 한다는 의미이니 트리가 아님을 표시한다.  

## :black_nib: **Review**
- 처음에 단방향으로만 연결을 해서 문제를 해결하려고 했다.
    ```java
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        
        int start = Math.min(v1, v2);
        int end = Math.max(v1, v2);
        nodes.get(start).add(end);
    ```
    이렇게 단방향으로 구현한다면 트리인지 판단할때 부모노드인지 확인할 필요가 없어진다.<br>
    하지만 어떤 케이스인지는 아직 찾지못했지만 높은 노드에서 낮은 노드로 가는 경우가 있는지 답이 제대로 안나왔다. 그래서 너무 슬펐다.
- 문제를 보고 Union-Find문제인것을 알았는데 익숙하지 않은 알고리즘이고 BFS로직이 먼저 생각나서 익숙한 BFS를 응용해서 풀었다. 하지만 알고리즘을 연습하는 스터디이니 다음에는 익숙하지 않은 알고리즘을 사용해서 풀어보려고 노력해야 겠다.