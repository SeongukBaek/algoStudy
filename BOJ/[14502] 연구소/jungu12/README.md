# [14502] 연구소

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
static void chooseBlank(int depth, int[] visited) {
	if (depth == 3) {
		spreadVirusAndFindMax(visited);
		return;
	}
	int start = (depth == 0) ? -1 : visited[depth - 1];
	for (int i = start + 1; i < blankPos.size(); i++) {
		visited[depth] = i;
		chooseBlank(depth + 1, visited);
	}
}

	static void makeWall() {
        //blankPos에 몇번째에 있는 빈 공간 좌표가 벽을 놓을 위치로 선정 되었는지 저장할 배열
		int[] visited = new int[3];
		chooseBlank(0, visited);
	}

static void spreadVirusAndFindMax(int[] visited) {
	Queue<int[]> que = new LinkedList<>();
	//초기 바이러스의 위치를 que에 저장
	for (int i = 0; i < virusPos.size(); i++) {
		que.add(virusPos.get(i));
	}
	//해당 경우에서만 사용할 map배열을 복사한 tmpMap배열
	int[][] tmpMap = new int[N][M];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			tmpMap[i][j] = map[i][j];
		}
	}
	//조합으로 뽑은 새로 벽을 놓을 곳에 벽을 놓아줌
	for (int i = 0; i < 3; i++) {
		int[] cur = blankPos.get(visited[i]);
		tmpMap[cur[0]][cur[1]] = 1;
	}
	//bfs로 돌면서 바이러스 상하좌우가 빈공간이면 바이러스로 바꾸어줌
	while (!que.isEmpty()) {
		int[] curPos = que.poll();
		for (int i = 0; i < 4; i++) {
			int nx = curPos[0] + dx[i];
			int ny = curPos[1] + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}
			if (tmpMap[nx][ny] == 0) {
				tmpMap[nx][ny] = 2;
				que.add(new int[] { nx, ny });
			}
		}
	}
	//모든 작업을 마치고 tmpMap에 안전 공간을 세어줌
	int count = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (tmpMap[i][j] == 0) {
				count++;
			}
		}
	}
	best = Math.max(best, count);
}
  ```
   - 조합으로 벽을 놓을 3곳의 빈 공간을 찾아주었다.
   - bfs로 바이러스를 확산 시켜 주었다.
  
  
## :black_nib: **Review**
 - 조합으로 벽 놓을 곳을 뽑을 때, 중복이 가능하게 뽑아서 시행착오를 겪었다. 문제에서 꼭 3곳의 새로 생긴 벽이 있다는 조건을 확인하지 못하였다..
 - 각각의 경우의 수에서 사용하기 위해 원본 map배열을 복사할 tmpMap배열을 사용하여야 한다는 것을 주의해야한다.


  
  	

  
