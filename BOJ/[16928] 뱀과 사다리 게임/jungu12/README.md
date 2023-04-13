# [16928] 뱀과 사다리 게임

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private static int findMinRollCnt() {
	Queue<Integer> queue = new LinkedList<>();
	// 시작점은 0이 아닌 1이다.
	queue.add(1);
	// 주사위를 굴린 횟수
	int rollCnt = -1;

	while (!queue.isEmpty()) {
		int queueSize = queue.size();
		rollCnt++;

		for (int i = 0; i < queueSize; i++) {
			int cur = queue.poll();
			int nextBlank = 0;

			for (int j = 1; j <= 6; j++) {
				if (cur + j == 100) {
					return rollCnt + 1;
				}

				if (visited[cur + j]) {
					continue;
				}

				//뱀과 사다리가 있는 block인 경우
				if (board[cur + j] != 0) {
					queue.add(board[cur + j]);
					visited[cur + j] = true;
					continue;
				}
				nextBlank = j;
			}

			//주사위를 굴려서 갈 수 있는 빈칸이 있는 경우
			if (nextBlank != 0) {
				queue.add(cur + nextBlank);
				visited[nextBlank] = true;
			}
		}
	}
	return rollCnt;
}

```

- 100번째 칸에 도착하는 최적의 경우를 찾는 문제임으로 BFS를 선택하였다.
- 한 turn은 아래과 같다.
- 시작 칸에서 주사위를 던져서 도달할 수 있는 모든 뱀과 사다리는 queue에 추가한다.
- 시작 칸에서 주사위를 던져서 도달할 수 있는 가장 먼 빈칸을 queue에 추가한다.
- 주사위를 던진 횟수(rollCnt)를 증가 시켜준다.

## :black_nib: **Review**

- 뱀을 타고 이동하는 경우도 최적의 경로가 될 수 있다는 점을 인지해야 하는 문제였다.
