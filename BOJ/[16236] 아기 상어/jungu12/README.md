# [16236] 아기 상어

## :pushpin: **Algorithm**

시뮬레이션,BFS

## :round_pushpin: **Logic**

```java
static class Position implements Comparable<Position> {
	int x;
	int y;
	int distance;

	Position(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}

	public int compareTo(Position pos) {
		if (this.distance != pos.distance)
			return this.distance - pos.distance;

		if (this.x != pos.x)
			return this.x - pos.x;

		return this.y - pos.y;
	}
}

```

- 물고기들의 위치 정보와 상어와의 거리를 저장하는 class를 생성해주었다.
- 문제의 주어진 우선순위대로 priorityQueue에 저장하기 위해 compareTo 메소드를 override하여 구현해주었다.

```java
private static void findFish() {
	while (!queue.isEmpty()) {
		Position current = queue.poll();

		for (int i = 0; i < 4; i++) {
			int nx = current.x + dx[i];
			int ny = current.y + dy[i];

			if (!isIn(nx, ny)) {
				continue;
			}

			if (!visited[nx][ny] && sharkSize >= map[nx][ny]) {
				Position nextPos = new Position(nx, ny, current.distance + 1);
				visited[nx][ny] = true;
				queue.add(nextPos);

				if (map[nx][ny] != 0 && sharkSize > map[nx][ny]) {
					pq.add(nextPos);
				}
			}
		}
	}
}
```

- BFS로 먹을 수 있는 물고기들을 priorityqueue에 저장한다.

## :black_nib: **Review**

- 구현할게 많지 않은 시뮬레
