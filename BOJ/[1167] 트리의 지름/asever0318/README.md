# [1167] 트리의 지름

## :pushpin: **Algorithm**

dfs

## :round_pushpin: **Logic**

```java
findMax(1, 0);
		visited = new boolean[V+1];
		max = 0;
		findMax(y, 0);

		System.out.println(max);
```

- dfs를 총 2번 실행하는데
- 1. 임의의 정점(여기서는 0으로 설정했다)에서 가장 먼 정점 y 찾기
- 2. y에서 가장 먼 정점 찾기
- y에서 가장 먼 정점까지의 경로가 트리의 지름이 된다.

```java
static void findMax(int v, int sum) {
		if(max < sum) {
			max = sum;
			y = v;
		}

		visited[v] = true;

		for(int i = 0; i < linked[v].size(); i++) {
			Vertex next = linked[v].get(i);

			if(visited[next.to]) {
				continue;
			}

			findMax(next.to, sum+next.w);
		}
	}
```

- v정점에서 연결된 정점을 탐색하면서 방문하지 않은 정점이면 해당 정점에서 연결된 정점을 또 탐색하는 방식으로 끝까지 확인한다.
- 정점 사이의 거리를 sum 변수에 거리를 저장하고 재귀함수 호출할 때 넘겨주어서 거리 max 값을 갱신한다. 최종적으로 가장 먼 정점이 y에 저장된다.

## :black_nib: **Review**

- 답 보고 풀었다.. 트리의 성질을 모르면 못풀 것 같다.
