# [7576] 토마토

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
tomato = new int[M][N];
queue = new LinkedList<int[]>();
		
	// 토마토 입력 받기 
	for(int i = 0; i < M; i++) {
		str = br.readLine().split(" ");
		for(int j = 0; j < N; j++) {
			tomato[i][j] = Integer.parseInt(str[j]);
				
			// 출발점이 여러 개이므로 큐에 넣어서 순서대로 처리해주기 
			if(tomato[i][j] == 1) {
				int[] xy = new int[3];
				xy[0] = i; // 시작점의 x좌표 
				xy[1] = j; // 시작점의 y좌표 
				xy[2] = 0; // 시작점이니까 day 0
				queue.add(xy); // 큐에 넣기 
			}
		}
	}
```
- 익은 토마토가 있는 곳이 시작점으로 시작점이 여러 곳이기 때문에 만약 입력 받은 수가 1(익은 토마토)이라면 큐에 넣어서 관리했다.
- 또한 사용해야할 정보가 x좌표, y좌표, 날짜 총 3개이므로 크기가 3인 배열로 저장해주었다. 

```java
private static void bfs() {
		
		// 시작점 큐가 다 빌때까지 
		while(!queue.isEmpty()) {
			// 시작점 좌표 하나 빼오기 
			start = (int[]) queue.poll();
			day = start[2];
			
			// 네 방향 탐색 
			for(int i = 0; i < 4; i++) {
				int xx = start[0] + dx[i];
				int yy = start[1] + dy[i];
				
				// 탐색할 좌표가 matrix를 벗어나지 않는 지 검사 
				if((0 <= xx && xx < M) && (0 <= yy && yy < N)) {
					// 만약 토마토가 익지 않았으면 
					if(tomato[xx][yy] == 0) {
						tomato[xx][yy] = 1;
						int[] index = {xx, yy, start[2]+1};
						queue.add(index);
					}
				}
			}
		}
	}
```
- 익은 토마토가 들어있는 시작점부터 상하좌우를 확인하면서 좌표가 matrix를 벗어나지 않고, 만약 토마토가 익지 않았으면 익은 걸로 바꿔주고 해당 토마토를 큐에 넣어주었다. 
- 이때 총 걸린 날짜를 계산해야하기 때문에 날짜를 하나씩 증가하여 저장해주었다. 

```java
	private static void checkTomato() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(tomato[i][j] == 0) { 
					day = -1;
					return;
				}
			}
		}
	}
```
- 마지막으로 전체 토마토를 확인하면서 만약 안익은 토마토가 남아있으면 day에 -1을 저장해주었다.
## :black_nib: **Review**
- 시작점이 여러점이라서 큐를 통해서 탐색 위치를 추가해주었는데 이때 한 사이클을 구분지을 방법을 고민하다가 결국 배열에 한 칸을 더 만들어서 날짜를 증가시키면서 저장해주는 방법을 사용했다.  