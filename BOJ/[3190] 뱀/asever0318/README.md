# [3190] 뱀

## :pushpin: **Algorithm**

큐, 구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
static void playGame() {

		snake = new ArrayList<>();

		// 처음에 뱀의 길이는 1이고 오른쪽을 향하고 있으며, 맨위맨좌측에 있다.
		snake.add(new Snake(1, 1, 1));
		board[1][1] = 2; // 맵에서 뱀은 2로 표시

		Info info = rotate.poll();

		while(true) {
			totalTime++;

			Snake head = snake.get(0); // 뱀머리

			// 다음 좌표 구하기
			int nx = head.x + dx[head.d];
			int ny = head.y + dy[head.d];

			// 만약 다음 좌표가 벽이거나 자신의 몸이면 게임 종료
			if(!isVaildIndex(nx, ny) || !isNotSnake(nx, ny)) {
				return;
			}

			int direction = head.d;
			if(info.time == totalTime) { // 회전해야 될 시간이 흐른 시간과 같아지면
				// 새로운 방향 찾아주기
				direction = rotation(snake.get(0).d, info.d);

				if(!rotate.isEmpty()) { // 큐가 비어있지 않으면 (회전정보가 남아있으면)
					info = rotate.poll();
				}
			}

			snake.add(0, new Snake(nx, ny, direction)); // 뱀머리 이동(추가)
			moveSnake(nx, ny); // 사과가 있으면 꼬리 변화 x, 사과가 없으면 꼬리 줄이기
		}
	}
```

- 맵에서 사과는 1로, 뱀은 2로 표시한다.
- 머리에 저장된 방향 정보에 따라 다음 이동할 좌표를 구하고, 해당 좌표가 벽이거나 자신의 몸이면 게임을 종료한다.
- 회전해야 될 시간이 흐른 시간과 같아지면 새로운 방향을 찾고, 해당 방향으로 뱀을 이동시킨다.
- 한 번 회전하고 나면 새로운 회전정보를 가져온다. 회전정보는 큐에 담아놓고 큐가 빌때까지 반복한다.

```java
	static int rotation(int currentD, char newD) {
		if(newD == 'D') {
			if(currentD == 3) {
				currentD = 0;
			}else {
				currentD++;
			}
		}

		if(newD == 'L') {
			if(currentD == 0) {
				currentD = 3;
			}else {
				currentD--;
			}
		}

		return currentD; // 바뀐 방향 반환
	}
```

- 현재 방향에서 주어진 방향으로 90도 회전 후 반환하는 함수

```java

	static void moveSnake(int nx, int ny) {
		if(board[nx][ny] == 0) { // 사과가 없으면
			int tail = snake.size() - 1; // 뱀꼬리
			int x = snake.get(tail).x;
			int y = snake.get(tail).y;

			board[x][y] = 0; // 맵에서 꼬리 없애주고
			snake.remove(tail); // 꼬리 줄이기
		}

		board[nx][ny] = 2; // 보드에 뱀으로 표시
	}
```

- 뱀 이동시키는 함수
- 사과가 있으면 꼬리를 움직일 필요없으므로 사과가 없는 경우만 고려해서 꼬리를 1칸 줄이고 보드에 표시한다.

## :black_nib: **Review**

- 회전정보를 따로 관리해주고, 뱀 객체에 방향을 저장해서 문제 그대로 구현하여 풀 수 있었다.
