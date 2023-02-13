# [2206] 벽 부수고 이동하기

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
public static void bfs() {
		
		while(!q.isEmpty()) {
			map now = q.poll(); // 다음 이동할 곳 하나씩 가져오기 
			
			if(now.x == N - 1 && now.y == M - 1) {  // 도착 좌표에 도착하면 끝 
				System.out.println(now.cnt);		// 매트릭스에 0부터 넣었으니까 [N-1][M-1]
				return;
			}
			
			// 상하좌우 4방향 
			for(int i = 0; i < 4; i++) {
				
				int xx = now.x + dx[i];
				int yy = now.y + dy[i];
				
				if(0 <= xx && xx < N && 0 <= yy && yy < M){ // 매트릭스를 벗어나지 않고 
					if(matrix[xx][yy] == 0) { // 벽이 아니면
						if(!visited[xx][yy][0] && !now.crashed) { // 벽을 부순적이 없으면 
							q.add(new map(xx, yy, now.cnt+1, false));
							visited[xx][yy][0] = true;
						}else if(!visited[xx][yy][1] && now.crashed) { // 벽을 부순 적 있으면
							q.add(new map(xx, yy, now.cnt+1, true));
							visited[xx][yy][1] = true;
						}
					}else if(matrix[xx][yy] == 1){ // 벽을 만나면 
						if(!now.crashed) { // 한 번도 벽을 부순 적 없으면 부순다 
							q.add(new map(xx, yy, now.cnt+1, true));
							visited[xx][yy][1] = true;
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
```
- 여러 정보를 쉽게 관리하기 위해 x좌표, y좌표, 이동횟수, 벽 부순적 있는지 여부를 포함하는 클래스를 만들어주었다.
- 큐에서 좌표를 하나씩 꺼내와서 BFS 탐색해주는데 이때 최대 1번까지 벽을 부수고 갈 수 있으므로 각 좌표에서 벽을 부쉈을 때와 부수지 않았을 때를 구분하기 위해 visited배열을 3차원으로 구성해주었다. 벽을 부순적이 없으면 0에, 부순적이 있으면 1에 표시해주었다.
- 이동 할 때마다 탐색 횟수를 카운트하기 위해 큐에 넣을 때 현재 횟수에서 + 1 해주었다. 
- 큐에서 꺼내온 좌표가 도착점과 일치하면 횟수를 출력하고 빠져나간다. 



## :black_nib: **Review**
-  상하좌우 4방향으로 탐색하면서 최단거리를 한다는 점에서 BFS로 풀면 될 것 같았는데 최대 1개의 벽을 부수고 최단 거리를 구해야 한다는 점에서 어떻게 풀어야할지 잘 생각이 나지않아서 시간을 많이 보냈다. 결국 정답을 참고했는데 이동할 곳을 Queue에 넣어서 관리하는 것과 각 좌표에서 벽을 부쉈을 때와 부수지 않았을 때를 각각 따로 생각해줘야 했다. 아직 경험이 부족해서 바로바로 생각해내지 못하는 것 같다 :(.