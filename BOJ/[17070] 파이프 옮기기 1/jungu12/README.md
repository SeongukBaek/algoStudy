# [17406] 파이프 옮기기 1

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
static void movePipe(int x, int y, int state) {
	if (x == N - 1 && y == N - 1) {
		count++;
		return;
	}
	if (state == 1) {
		checkState1(x, y, state);
		checkState2(x, y, state);
	}
	if (state == 2) {
		checkState1(x, y, state);
		checkState2(x, y, state);
		checkState3(x, y, state);
	}
	if (state == 3) {
		checkState2(x, y, state);
		checkState3(x, y, state);
	}
}

static void checkState1(int x, int y, int state) {
	if (x + 1 < N && house[x + 1][y] == 0) {
		movePipe(x + 1, y, 1);
	}
}

static void checkState2(int x, int y, int state) {
	if (x + 1 < N && y + 1 < N && house[x + 1][y + 1] == 0 && house[x + 1][y] == 0 && house[x][y + 1] == 0) {
		movePipe(x + 1, y + 1, 2);
	}
}

static void checkState3(int x, int y, int state) {
	if (y + 1 < N && house[x][y + 1] == 0) {
		movePipe(x, y + 1, 3);
	}
}

  ```
 
   - DFS로 파이프를 놓을 수 있다면, 파이프 좌표를 이동해주고 (N, N)에 다다르면 count++ 해준다.
  
  
## :black_nib: **Review**
 - (N, N)까지 최소로 이동하는 거리로 착각하고 풀어 BFS로 풀다가 잘못 이해한걸 알아버렸는데, BFS로 푸니 시간 초과가 났다.


  
  	

  
