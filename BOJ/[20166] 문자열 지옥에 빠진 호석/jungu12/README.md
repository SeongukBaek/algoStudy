# [20168] 골목 대장 호석 - 기능성

## :pushpin: **Algorithm**

백 트래킹

## :round_pushpin: **Logic**

```java
private static void findNumberOfCases(int x, int y, int depth, String result) {
	if (map.containsKey(result)) {
		map.put(result, map.get(result) + 1);
	}

	if (depth == maxDepth) {
		return;
	}

	for (int dir = 0; dir < 8; dir++) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (nx < 0) {
			nx = N - 1;
		}
		if (nx >= N) {
			nx = 0;
		}
		if (ny < 0) {
			ny = M - 1;
		}
		if (ny >= M) {
			ny = 0;
		}

		findNumberOfCases(nx, ny, depth + 1, result + world[nx][ny]);
	}
}
```

- 만들어야 할 단어 중 가장 길이가 긴 것을 변수에 저장한다.
- Depth가 변수만큼 되었다면 만들어야 할 단어가 만들어 졌는지 확인한다.

## :black_nib: **Review**

- BFS로 쉽게 풀 수 있는 문제였다!
