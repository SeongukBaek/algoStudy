# [14502] 연구소 

## :pushpin: **Algorithm**

부르트포스

## :round_pushpin: **Logic**
```java
static void makeWall(int n) {
		
		if(n == 3) { // 벽 3개 다 세웠으면 
			vQueue.addAll(temp);
			int[][] tmp = new int[N][M];
			init(tmp, matrix);
			
			while(!vQueue.isEmpty()) { 
				int[] current = vQueue.poll();
				int i = current[0];
				int j = current[1];
				
				virus(i, j);
			}
			countSafezone(); // 안전지대 카운트
			max = Math.max(max, count); // max 갱신 
			count = 0; // 안전지대 카운트 초기화  
			init(matrix, tmp);
			
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 0) { // 빈 공간이면
					matrix[i][j] = 1; // 벽 세우기 
					makeWall(n+1);
					matrix[i][j] = 0;
				}
			}
		}
	}
```
 - 벽 3개를 세울 수 있는 모든 경우의 수를 확인하면서 해당 경우에서 바이러스틑 퍼뜨리고 안전지대의 크기를 확인하는 방식으로 구현했다.
 - - 처음 연구소에서 바이러스가 있던 위치를 큐에 넣어두고, 큐가 빌때까지 해당 위치에서부터 4방향으로 바이러스를 퍼뜨린다.


## :black_nib: **Review**
- 연구소에 벽 3개를 세우고 나면 바이러스를 퍼트리는데 이때 다음 경우를 위해서 맵을 초기화 해주어야 하는데 이 부분을 빼먹는 바람에 해결하는데 시간이 걸렸다. 로직을 잘 따라가면서 좀 더 꼼꼼하게 코딩하도록 노력해야겠다.
