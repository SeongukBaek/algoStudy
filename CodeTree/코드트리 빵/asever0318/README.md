# 코드트리 빵

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
static void simulation() {
		
		for(int i = 0; i < M; i++) {
			// 격자 밖에 있는 사람이면 패스
			if(people[i].x == -1 && people[i].y == -1) {
				continue;
			}
			
			// 이미 편의점에 도착한 사람이면 패스
			if(people[i].x == store[i].x && people[i].y == store[i].y) {
				continue;
			}
			
			// 편의점에서 현재 위치까지 오는 최단 거리 
			bfs(store[i]);
			
			int minDistance = Integer.MAX_VALUE;
			int minX = -1;
			int minY = -1;
			
			// 4방향으로 돌면서 거리가 가장 작은 곳 고르기
			for(int j = 0; j < 4; j++) {
				int nx = people[i].x + dx[j];
				int ny = people[i].y + dy[j]; 
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				
				if(visited[nx][ny] && minDistance > min[nx][ny]) {
					minDistance = min[nx][ny];
					minX = nx; 
					minY = ny;
				}
			}
			
			// 우선순위가 가장 높은 위치로 움직임 
			people[i] = new Pos(minX, minY);
		}
		
		// 편의점에 도착하면 map에 이동 불가능 표시 
		for(int i = 0; i < M; i++) {
			if(people[i].x == store[i].x && people[i].y == store[i].y) {
				map[people[i].x][people[i].y] = 2; 
			}
		}
		
		// 현재 시간이 M보다 크면 리턴
		if(time > M) {
			return;
		}
		
		// 편의점으로부터 가장 가까운 베이스 캠프고르기 
		bfs(store[time - 1]);
		
		int minDistance = Integer.MAX_VALUE;
		int minX = -1;
		int minY = -1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] && map[i][j] == 1 && minDistance > min[i][j]) {
					minDistance = min[i][j];
					minX = i;
					minY = j;
				}
			}
		}
		
		// 우선순위가 가장 높은 베이스 캠프로 이동
		people[time - 1] = new Pos(minX, minY);
		map[minX][minY] = 2; // 해당 위치는 이동 못함으로 표시 
	}
```
- 1. 격자에 있는 사람을 편의점을 향해 한 칸 움직인다. 
- 2. 편의점에 도착한 사람의 위치를 map에서 이동 불가능으로 바꿔준다.
- 3-1. time > M 이면 모두 베이스캠프로 이동 한 것이기 때문에 이 후 과정은 패스해도 된다.
- 3-2. 아직 격자 밖에 있는 사람이 있으면 편의점으로부터 가장 가까운 베이스 캠프로 이동한다. 


## :black_nib: **Review**
- 구현 문제는 생각할 게 많아서 어려운 것 같다. 해설보고 다시 풀었다.
