# [1167] 트리의 짊

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
private static void findEnd(int vertex, int length) {
	if (length > max) {
		max = length;
		endVertex = vertex;
	}

	for (int i = 0; i < graph[vertex].size(); i++) {
		Node current = graph[vertex].get(i);
		if (visited[current.vertex]) {
			continue;
		}

		visited[current.vertex] = true;
		findEnd(current.vertex, length + current.length);
		visited[current.vertex] = false;
	}
}

```

- 아무 Vertex에서 DFS로 가장 거리가 먼 Vertex를 찾으면 그 Vertex가 트리의 한쪽 끝이다.
- 위에서 찾은 트리의 한쪽 끝 Vertex로 부터 가장 거리가 먼 Vertex를 찾으면 또 다른 트리의 한쪽 끝을 찾은 것이다.
- 위 두 점을 이용하여 트리의 지름을 구할 수 있다. -짧게 요약하면 DFS 두번 돌리면 트리의 지름을 구할 수 있다~

## :black_nib: **Review**

- 위에 언급한 아이디어를 떠올리는 것이 쉽지 않은 문제이다.
