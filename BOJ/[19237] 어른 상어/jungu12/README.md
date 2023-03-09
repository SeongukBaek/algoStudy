# [19237] 어른 상어

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
private static void moveUntilShark1Left() {
	moveStart();
	count++;
	while (outSharkNum != M - 1) {
		reduceTraces();
		checkAllMap();
		removeTraces();
		count++;
		if (count > 1000) {
			count = -1;
			return;
		}
	}
}
```

- 로직은 아래와 같다.
- moveStart() -> 초기상태에서 1회 이동시켜준다.
- reduceTraces() -> 흔적들을 모두 -1시켜준다.
- checkAllMap() -> 맵을 돌면서 상어를 이동시켜준다.
- removeTraces() -> 사라질 흔적들을 지워준다.
- checkAllMap()을 수행하기 전 reduceTraces를 수행해 주는 이유는, 상어의 현재 위치 좌표를 따로 관리하지 않고
  맵을 다 돌면서 trace == K 인 곳을 상어 좌표로 활용하고 있기 때문이다.
- 따라서, trace가 0 ~ K-2인 곳은 상어의 흔적들이고, trace가 K-1은 이동하지 않은 상어가 존재하는 곳, trace가 K는 이미 이동한 상어가 존재하는 곳이 된다.

```java
private static void moveStart() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] != null && map[i][j].trace == 0) {
				for (int dir = 0; dir < 4; dir++) {
					int selectedDir = priorDir[map[i][j].num][map[i][j].dir][dir];
					int nx = i + dx[selectedDir];
					int ny = j + dy[selectedDir];
					if (isIn(nx, ny)) {
						// 다른 상어 만난 경우
						if (map[nx][ny] != null && map[nx][ny].trace == K) {
							Shark winShark = fightAndFindWinner(i, j, nx, ny, selectedDir);
							map[nx][ny] = new Shark(winShark.num, nx, ny, winShark.dir);
							map[i][j].trace = K - 1;
							outSharkNum++;
							break;
						}
						map[nx][ny] = new Shark(map[i][j].num, nx, ny, selectedDir);
						map[i][j].trace = K - 1;
						break;
					}
				}
			}
		}
	}
}
```

- 제일 처음 map에 아무 흔적도 없을 때, 초기 방향에 따라 상어를 이동시켜 줄 때 사용한다.
- map[i][j] 가 null인데 trace가 0이 아니라면 상어가 이동한 위치, map[i][j]가 null인데 trace가 0이면 이동하지 않은 상어의 위치이다.

```java
private static void checkAllMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			// 현재 위치에 상어가 있는 경우, trace == K - 1 -> 현재 상어가 그 위치에 존재한다는 의미
			if (map[i][j] != null && map[i][j].trace == K - 1) {
				findNextPos(i, j);
			}
		}
	}
}
```

- map을 돌면서 상어를 찾으면 findNextPos()를 호출한다.

```java
private static void findNextPos(int i, int j) {
	List<Integer> blanks = new ArrayList<>();
	List<Integer> traces = new ArrayList<>();
	for (int dir = 0; dir < 4; dir++) {
		int nx = i + dx[dir];
		int ny = j + dy[dir];
		if (!isIn(nx, ny)) {
			continue;
		}
		//방문한 곳이 빈칸인경우
		if (map[nx][ny] == null || map[nx][ny].trace == K && map[nx][ny].blanked) {
			blanks.add(dir);
			continue;
		}
		//방문한 곳이 자신의 흔적인 경우
		if (map[nx][ny].num == map[i][j].num) {
			traces.add(dir);
		}
	}
	//사방에 빈칸이 있는 경우
	if (!blanks.isEmpty()) {
		if (blanks.size() == 1) {
			moveShark(i, j, blanks.get(0), true);
			return;
		}
		if (blanks.size() > 1) {
			for (int dirNum = 0; dirNum < 4; dirNum++) {
				for (int num = 0; num < blanks.size(); num++) {
					int dir = blanks.get(num);
					if (priorDir[map[i][j].num][map[i][j].dir][dirNum] == dir) {
						moveShark(i, j, dir, true);
						return;
					}
				}
			}
		}
	}
	//사방에 빈칸이 없는 경우
	if (blanks.isEmpty()) {
		if (traces.size() == 1) {
			moveShark(i, j, traces.get(0), false);
			return;
		}
		if (traces.size() > 1) {
			for (int dirNum = 0; dirNum < 4; dirNum++) {
				for (int num = 0; num < traces.size(); num++) {
					int dir = traces.get(num);
					if (priorDir[map[i][j].num][map[i][j].dir][dirNum] == dir) {
						moveShark(i, j, dir, false);
						return;
					}
				}
			}
		}
	}
}
```

- 한 cycle안에서 다른상어가 먼저 이동한 곳은 빈칸으로 취급하여야 한다. map[nx][ny].blanked = true라면 이런 경우이다.

## :black_nib: **Review**

- 관리해야할 정보가 많아 적절한 자료구조를 선택하려 노력했으나, 다 짜고 나니 썩 마음에 들지 않았다...
- 정보들을 깔끔하게 관리하지 못하니 구현 과정에서 놓치는 부분이 잘 생기는 것 같다. 이번에도 어김없이 구현을 덜하여 찾는데 시간을 좀 썼다..
