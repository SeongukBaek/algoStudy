# [4803] 트리

## :pushpin: **Algorithm**

DPS

## :round_pushpin: **Logic**

```java
private static void makeEdge(int n) {
	visited[n] = true;
	vertexNum++;
	int connectedEdgeSize = graph[n].size();
	edgeNum += connectedEdgeSize;
	for (int i = 0; i < connectedEdgeSize; i++) {
		int current = graph[n].get(i);
		if (visited[current]) {
			continue;
		}
		makeEdge(current);
	}
}
```

- DFS를 이용하여 문제의 조건대로 트리를 만들어준다.

```java
private static void countTree() {
	for (int i = 1; i <= N; i++) {
		if (!visited[i]) {
			vertexNum = 0;
			edgeNum = 0;
			makeEdge(i);
			if (edgeNum == (vertexNum - 1) * 2) {
				count++;
			}
		}
	}
}
```

- 트리는 간선의 수가 정점의 수 - 1이다.
- 양방향 그래프로 만들어 주었음으로 위와 같이 계산해 주어 만족한다면 tree++

```

## :black_nib: **Review**
 - 트리인지 확인하는 로직을 답을 봐버렸다.. 근데 안 잊어버릴듯.







```
