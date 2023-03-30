# [4803] 트리

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
static int countTree(int n) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		visited[n] = true;

		// 이미 방문한 정점을 재방문하려고 할 때, 그 노드가 현재 정점의 부모가 아니라면 사이클
		while(!queue.isEmpty()) {
			int current = queue.poll();

			// 해당 정점에서 연결된 정점들 모두 탐색
			for(int i = 0; i < link[current].size(); i++) {
				int next = link[current].get(i);

				if(visited[next]) { // 방문한 정점을 다시 방문할 떄
					if(next != parent[current]) {
						return 0; // 부모가 아니라면 사이클이므로 0 반환
					}
					else { // 부모면 다음 정점으로 넘어가기
						continue;
					}
				}

				// 방문한 점이 아닐 때
				visited[next] = true; // 방문표시
				queue.add(next); // 큐에 넣기
				parent[next] = current; // 부모노드 저장
			}
		}

		return 1;
	}
```

- BFS로 그래프의 인접한 정점을 탐색한다.
- 그래프를 탐색할 때 현재 정점의 부모 정점을 저장해서 만약 다음에 탐색해야할 정점이 이미 방문한 정점이면 해당 정점의 부모 정점을 확인한다.
- 방문하려는 노드가 현재 정점의 부모가 아니면 사이클이 있는 것이므로 0을 반환한다.
- 사이클이 없으면 트리이므로 1을 반환한다.

## :black_nib: **Review**

- 그래프의 사이클을 확인하기 위해서 부모 노드를 저장하는 방법을 배웠다!
