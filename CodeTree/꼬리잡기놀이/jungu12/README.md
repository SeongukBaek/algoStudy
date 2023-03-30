# 꼬리잡기놀이

## :pushpin: **Algorithm**

DPS, 

## :round_pushpin: **Logic**

```java
for (int i = 0; i < N; i++) {
	st = new StringTokenizer(br.readLine());
	for (int j = 0; j < N; j++) {
		board[i][j] = new Member(Integer.parseInt(st.nextToken()));
	}
}

// 팀 별 이동선들의 좌표 저장
for (int i = 0; i < N; i++) {
	for (int j = 0; j < N; j++) {
		// List에 머리부터 저장함
		if (board[i][j].type == 1) {
			moveLine = new ArrayList<>();
			findMoveLine(i, j);
			moveLines.add(moveLine);
		}
	}
}

// board에 팀Idx 정보 추가
for (int i = 0; i < M; i++) {
	List<Position> moveLine = moveLines.get(i);
	for (int j = 0; j < moveLine.size(); j++) {
		Position cur = moveLine.get(j);
		board[cur.x][cur.y].teamIdx = i + 1;
	}
}

for (int i = 0; i < K; i++) {
	moveAll();
	score += shootBall(i);
}
```

- 기본 배열인 board는 몇 번 팀인지, 머리인지 몸통인지 꼬리인지 빈칸인지에 대한 정보를 담고있는 Member 클래스의 2차원 배열이다.
- 팀 별 이동 선을 moveLines ArrayList에 저장해주었으며, 머리부터 순차적으로 저장하였다.
- board 배열에 input을 저장한 후, 전체를 탐색하며 팀Idx에 대한 정보를 board 배열에 추가로 저장해준다. 팀 Idx는 1부터 순차적으로 배정해주었다.
- main 함수에서 주어진 라운드 수 K만큼 움직이고 공을 던지고 맞았다면 점수를 계산하는 로직을 반복해준다.

```java
private static void findMoveLine(int i, int j) {
	moveLine.add(new Position(i, j));
	visited[i][j] = true;

	for (int dir = 0; dir < 4; dir++) {
		int nx = i + dx[dir];
		int ny = j + dy[dir];
		if (!isIn(nx, ny)) {
			continue;
		}
		if (board[nx][ny].type == 0 || visited[nx][ny]) {
			continue;
		}

		// 머리 -> 몸통 -> 꼬리 순으로 저장하기 위해 moveLine에 머리만 있는 경우, 몸통이 나올 때 까지 스킵
		if (moveLine.size() == 1 && board[nx][ny].type != 2) {
			continue;
		}

		// 머리부터 꼬리까지의 moveLine의 size가 팀의 인원수. 해당 값을 저장.
		if (board[nx][ny].type == 3) {
			teamSize.add(moveLine.size() + 1);
		}

		findMoveLine(nx, ny);
	}
}
```

- main 함수에서 전체를 탐색하며, 머리를 발견하면 머리가 속하는 팀의 이동 선에 대한 정보를 만들어주는 method다.
- 머리 -> 몸통 -> 꼬리 -> 빈칸 순으로 이동 선에 대한 정보를 저장해주었다.
- 꼬리가 나온다면 팀의 인원수를 계산하여 해당 값을 저장해준다. -> 향후에 이동 및 reverse에 요긴하게 활용된다.

```java
private static void moveAll() {
	for (int i = 0; i < M; i++) {
		//현재 팀의 이동 선
		List<Position> currentLine = moveLines.get(i);
		//머리의 좌표
		Position headPos = currentLine.get(currentLine.size() - 1);
		for (int j = currentLine.size() - 1; j > 0; j--) {
			currentLine.set(j, currentLine.get(j - 1));
		}
		currentLine.set(0, headPos);
		moveLines.set(i, currentLine);

		//변경한 좌표 정보를 바탕으로 board에도 갱신
		int length = teamSize.get(i);
		for (int j = 0; j < currentLine.size(); j++) {
			Position current = currentLine.get(j);
			if (j == 0) {
				board[current.x][current.y].type = 1;
				continue;
			}
			if (j == length - 1) {
				board[current.x][current.y].type = 3;
				continue;
			}
			if (j >= length) {
				board[current.x][current.y].type = 4;
				continue;
			}
			board[current.x][current.y].type = 2;
		}
	}
}

```

- 이동한 좌표 정보를 바탕으로 board에도 갱신해준다.

```java
private static int shootBall(int round) {
	round %= (4 * N);
	round += 1;
	if (round <= N) {
		for (int i = 0; i < N; i++) {
			if (board[round - 1][i].teamIdx != 0 && board[round - 1][i].type != 4) {
				return getScore((round - 1) % N, i);
			}
		}
	} else if (round <= 2 * N) {
		for (int i = N - 1; i >= 0; i--) {
			if (board[i][(round - 1) % N].teamIdx != 0 && board[i][(round - 1) % N].type != 4) {
				return getScore(i, (round - 1) % N);
			}
		}
	} else if (round <= 3 * N) {
		for (int i = N - 1; i >= 0; i--) {
			if (board[N - ((round - 1) % N) - 1][i].teamIdx != 0 && board[N - ((round - 1) % N) - 1][i].type != 4) {
				return getScore(N - (round - 1) % N - 1, i);
			}
		}
	} else {
		for (int i = 0; i < N; i++) {
			if (board[i][N - ((round - 1) % N) - 1].teamIdx != 0 && board[i][N - ((round - 1) % N) - 1].type != 4) {
				return getScore(i, N - ((round - 1) % N) - 1);
			}
		}
	}
	return 0;
}

private static int getScore(int x, int y) {
	//현재 팀의 이동 선
	List<Position> currentLine = moveLines.get(board[x][y].teamIdx - 1);
	//이동 선을 따라가며, 공을 맞은 사람과 같은 좌표를 찾아낸다.
	for (int cnt = 0; cnt < currentLine.size(); cnt++) {
		Position current = currentLine.get(cnt);
		if (x == current.x && y == current.y) {
			//좌표를 찾아낸다면 reverse해준다.
			reverse(board[x][y].teamIdx);
			return (cnt + 1) * (cnt + 1);
		}
	}
	return 0;
}
```

- 공 던져서 맞으면 점수를 계산해준다.
- 공을 맞았다면 reverse() 메소드를 호출한다.

```java
private static void reverse(int teamIdx) {
	//현재 팀의 인원 수
	int currentTeamSize = teamSize.get(teamIdx - 1);
	//현재 팀의 이동 선
	List<Position> currentLine = moveLines.get(teamIdx - 1);
	//현재 팀의 이동 선 길이
	int currentLineSize = currentLine.size();

	//좌표 정보 갱신을 위해 복사한 List
	List<Position> tmp = new ArrayList<>();
	for (Position current : currentLine) {
		tmp.add(current);
	}

	//꼬리부터 머리까지 좌표 순차적으로 저장
	for (int i = currentTeamSize - 1, idx = 0; i >= 0; i--, idx++) {
		currentLine.set(idx, tmp.get(i));
	}

	//바뀐 꼬리의 다음 칸부터 바뀐 머리가 나오기 전까지 좌표 순차적으로 저장
	for (int i = currentLineSize - 1, idx = currentTeamSize; i >= currentTeamSize; i--, idx++) {
		currentLine.set(idx, tmp.get(i));
	}

	//변경한 좌표 정보를 바탕으로 board에도 갱신
	for (int j = 0; j < currentLine.size(); j++) {
		Position current = currentLine.get(j);
		if (j == 0) {
			board[current.x][current.y].type = 1;
			continue;
		}
		if (j == currentTeamSize - 1) {
			board[current.x][current.y].type = 3;
			continue;
		}
		if (j >= currentTeamSize) {
			board[current.x][current.y].type = 4;
			continue;
		}
		board[current.x][current.y].type = 2;
	}
}
```

- 머리부터 꼬리까지 뒤집어 버리는 역할을 하는 method다.
- 뒤집는 과정에서 원본 List 하나만 활용한다면 못 뒤집는다. 그래서 임시 List에 복사해서 복사한 List에 정보를 참조하며 뒤집는다.
- 뒤집고 board에도 정보 갱신해준다.

## :black_nib: **Review**

- 어려웠다.
- 나 자신을 너무 과신하지말자. 머리속으로 풀 수 없다. 시뮬레이션 문제는 꼭 종이에 정리해가면서 풀어야겠다. 머리속으로 풀다보니 매끄럽게 풀 수 없었다..
