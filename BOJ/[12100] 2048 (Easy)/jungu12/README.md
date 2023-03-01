# [12100] 2048 (Easy)

## :pushpin: **Algorithm**

구현, 백트래킹

## :round_pushpin: **Logic**

```java
static void playGame(int cnt) {
	if (cnt == 5) {
		findMaxBlock();
		return;
	}
	int[][] copyBoard = copyBoard(board);
	swipeLeft();
	playGame(cnt + 1);
	board = copyBoard(copyBoard);
	swipeRight();
	playGame(cnt + 1);
	board = copyBoard(copyBoard);
	swipeUp();
	playGame(cnt + 1);
	board = copyBoard(copyBoard);
	swipeDown();
	playGame(cnt + 1);
	board = copyBoard(copyBoard);
}
```

- 현재까지의 board를 copy에 저장 하여 다시 돌아왔을 때 board에 옮겨 사용해준다.

```java
	static void swipeUp() {
		for (int i = 0; i < N; i++) {
			int index = 0;
			int before = 0;
			for (int j = 0; j < N; j++) {
				if (board[j][i] == 0) {
					continue;
				}
				if (before == board[j][i]) {
					board[index - 1][i] = before * 2;
					before = 0;
					board[j][i] = 0;
				} else {
					before = board[j][i];
					board[j][i] = 0;
					board[index][i] = before;
					index++;
				}
			}
		}
	}

	static void swipeDown() {
		for (int i = 0; i < N; i++) {
			int index = N - 1;
			int before = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (board[j][i] == 0) {
					continue;
				}
				if (before == board[j][i]) {
					board[index + 1][i] = before * 2;
					before = 0;
					board[j][i] = 0;
				} else {
					before = board[j][i];
					board[j][i] = 0;
					board[index][i] = before;
					index--;
				}
			}
		}
	}

	static void swipeLeft() {
		for (int i = 0; i < N; i++) {
			int index = 0;
			int before = 0;
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				if (before == board[i][j]) {
					board[i][index - 1] = before * 2;
					before = 0;
					board[i][j] = 0;
				} else {
					before = board[i][j];
					board[i][j] = 0;
					board[i][index] = before;
					index++;
				}
			}
		}
	}

	static void swipeRight() {
		for (int i = 0; i < N; i++) {
			int index = N - 1;
			int before = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (board[i][j] == 0) {
					continue;
				}
				if (before == board[i][j]) {
					board[i][index + 1] = before * 2;
					before = 0;
					board[i][j] = before;
				} else {
					before = board[i][j];
					board[i][j] = 0;
					board[i][index] = before;
					index --;
				}
			}
		}
	}
```

- 상 하 좌 우로 swipe하는 경우를 따로따로 method로 만들었다.
- 한쪽 벽면으로 다 붙여야 하기 때문에 index로 값을 넣을 곳을 가르켰다.
- 가장 최근에 방문한 0이 아닌 block의 값을 before에 저장하였다.

## :black_nib: **Review**

- swipe했을 때 어떤 모양이 나올지 차근차근 구현하는게 까다로운 문제였다.
