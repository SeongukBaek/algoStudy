# [16234] 인구 이동

## :pushpin: **Algorithm**

DFS, 시뮬레이션

## :round_pushpin: **Logic**

```java
while (true) {
	unions = new ArrayList<>();
	visited = new boolean[N][N];

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (visited[i][j]) {
				continue;
			}

			visited[i][j] = true;
			List<int[]> union = new ArrayList<>();
			union.add(new int[] { i, j });
			makeUnion(i, j, union);
			if (union.size() != 1) {
				unions.add(union);
			}
		}
	}

	if (unions.isEmpty()) {
		System.out.println(count);
		break;
	}

	count++;
	movePopulation();
}
```

- 모든 block을 순차적으로 방문한다.
- 방문한 block이 visited = false이면, true로 변경해주고 makeUnion()해준다.
- 더 이상 연합이 만들어지지 않는다면 종료해준다.

```java
private static void makeUnion(int i, int j, List<int[]> union) {
	for (int dir = 0; dir < 4; dir++) {
		int nx = i + dx[dir];
		int ny = j + dy[dir];

		if (!isIn(nx, ny)) {
			continue;
		}

		if (visited[nx][ny]) {
			continue;
		}

		int sub = Math.abs(ground[i][j] - ground[nx][ny]);
		if (sub >= L && sub <= R) {
			union.add(new int[] { nx, ny });
			visited[nx][ny] = true;
			makeUnion(nx, ny, union);
		}
	}
}
```

- dfs로 연합을 만들어준다.
- 해당 block이 연합에 추가 된다면, visited = true 처리 해준다.

```java
private static void movePopulation() {
	int countryNum = unions.size();

	for (int i = 0; i < countryNum; i++) {
		List<int[]> currentUnion = unions.get(i);
		int population = 0;

		for (int[] currentCountry : currentUnion) {
			int x = currentCountry[0];
			int y = currentCountry[1];
			population += ground[x][y];
		}

		for (int[] currentCountry : currentUnion) {
			int x = currentCountry[0];
			int y = currentCountry[1];
			ground[x][y] = population / currentUnion.size();
		}
	}
}
```

- 연합끼리 인구수 합치고 나눠준다.

## :black_nib: **Review**

- 방문처리를 언제 해주는지 조금 헷갈리는 문제였다. 연합에 추가 될 때 방문처리 해주어야 함!

```

```
