# 팩맨

## :pushpin: **Algorithm**

구현, 시뮬레이션, DFS

## :round_pushpin: **Logic**

```java
private static void makeEgg() {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (board[i][j][0].isEmpty()) {
				continue;
			}

			board[i][j][1].addAll(board[i][j][0]);
		}
	}
}

```

- 현재 몬스터가 존재하는 자리에 알을 만들어준다.

```java
private static void moveMonster() {
		List<Integer>[][] tmpBoard = new List[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tmpBoard[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j][0].isEmpty()) {
					continue;
				}

				int size = board[i][j][0].size();
				for (int k = 0; k < size; k++) {
					int monsterDir = board[i][j][0].get(k);
					for (int dir = 0; dir <= 8; dir++) {
						if(dir == 8) {
							tmpBoard[i][j].add(monsterDir);
							break;
						}
						int nx = i + monsterDx[(monsterDir + dir) % 8];
						int ny = j + monsterDy[(monsterDir + dir) % 8];

						if (!isIn(nx, ny)) {
							continue;
						}

						if (isPacman(nx, ny)) {
							continue;
						}

						if (deadBoard[nx][ny][(turn % 3 + 1) % 3] != 0 || deadBoard[nx][ny][(turn % 3 + 2) % 3] != 0) {
							continue;
						}

						// 조건 만족하면 이동
						tmpBoard[nx][ny].add((monsterDir + dir) % 8);
						break;
					}
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j][0] = tmpBoard[i][j];
			}
		}
	}

```

- 이번 Turn에 이동한 몬스터와 이동하지 않은 몬스터를 구분해주어야 한다.
- tmpBoard에는 이번 Turn에 이동한 몬스터를 저장하고 모든 몬스터의 이동이 끝난 후, tmpBoard의 값을 board에 옮겨준다.
- 이동할 곳이 없어서 제자리에 있는 몬스터도 tmpBoard에 저장하여야 한다. (중요 !!!!)

```java
private static void findBestMovingCase(int x, int y, int count, int depth) {
	if (depth == 3) {
		if (count <= max) {
			return;
		}

		R = x;
		C = y;
		max = count;

		pacmanMovedPos.clear();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (visited[i][j]) {
					pacmanMovedPos.add(new int[] { i, j });
				}
			}
		}
		return;
	}

	for (int dir = 0; dir < 4; dir++) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (!isIn(nx, ny)) {
			continue;
		}

		if (visited[nx][ny]) {
			findBestMovingCase(nx, ny, count, depth + 1);
			continue;
		}

		int curCount = count;
		visited[nx][ny] = true;

		count += board[nx][ny][0].size();

		findBestMovingCase(nx, ny, count, depth + 1);
		visited[nx][ny] = false;
		count = curCount;
	}
}
```

- DFS로 몬스터를 가장 많이 잡을 수 있는 이동경로를 찾아준다.
- 문제에 주어진 우선순위가 높은 방향으로 먼저 이동한다.
- 이전 경로보다 몬스터를 많이 잡은 경우 현재까지의 최적의 경로가 된다.
- 최적의 경로는 pacmanMovedPos에 저장된다.

```java
private static void movePacman() {
	for (int[] pos : pacmanMovedPos) {
		int size = board[pos[0]][pos[1]][0].size();

		board[pos[0]][pos[1]][0].clear();
		deadBoard[pos[0]][pos[1]][turn % 3] = size;
	}
}
```

- 위에서 찾은 최적의 경로로 팩맨을 이동시켜준다.
- 이동한 경로에 있던 몬스터들은 모두 시체가 된다.

```java
private static void removeDead() {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			deadBoard[i][j][(turn % 3 + 1) % 3] = 0;
		}
	}
}

	private static void copyMonster() {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (board[i][j][1].isEmpty()) {
				continue;
			}

			board[i][j][0].addAll(board[i][j][1]);
			board[i][j][1].clear();
		}
	}
}
```

- 죽은지 2턴이 지난 몬스터들을 지워주고, 알 상태인 몬스터들을 부화시켜준다.

## :black_nib: **Review**

- 최대한 단순하고 간단한 자료구조를 활용해야 시간초과가 나지 않는 문제였다.... 단순하고 간단.. 명심 해야겠다..
