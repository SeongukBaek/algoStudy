# [3190] 뱀

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
private static void playGame() {
	int dir = 3;
	int time = 0;
	while (true) {
		time++;
		Position pos = snakePosition.peekLast();
		int nx = pos.x + dx[dir];
		int ny = pos.y + dy[dir];

		//벽에 부딪히거나 자신의 몸통과 만난 경우 종료
		if (!isMoveable(nx, ny)) {
			System.out.println(time);
			break;
		}

		snakePosition.add(new Position(nx, ny));

		// 이동한 곳에 사과가 없는 경우
		if (board[nx][ny] == 0) {
			Position tail = snakePosition.poll();
			board[tail.x][tail.y] = 0;
		}

		//이동 완료
		board[nx][ny] = 1;

		// 뱀 방향 갱신
		if (!dirInfo.isEmpty() && dirInfo.peek().time == time) {
			dir = changeDir(dir, dirInfo.poll().dir);
		}
	}
}
```

- 덱을 사용하여 뱀의 좌표를 관리해주었다.
- 방향을 바꿀 시간이 되면 이동 후 방향을 바꾸어준다.

```java
private static int changeDir(int dir, String nextDir) {
	if (nextDir.equals("D")) {
		dir = (dir + 3) % 4;
	}
	if (nextDir.equals("L")) {
		dir = (dir + 1) % 4;
	}
	return dir;
}
```

- % 연산자를 이용하여 방향을 변경해주었다.

## :black_nib: **Review**

- 0초 -> 이동 -> 방향 변경 -> 1초이다. 문제를 꼼꼼하게 잘 읽자.
