# [17070] 파이프 옮기기 1

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**
```java
static int[] dx = {1, 0, 1}; 
static int[] dy = {1, 1, 0};
```
- 파이프 끝이 갈 수 있는 방향이 오른쪽 대각선 아래, 오른쪽, 아래 3가지로 제한되어있기 때문에 방향을 알려줄 배열을 만들었다. 
```java
public static void movePipe(int x, int y, int state) {
		
		if(x == N-1 && y == N-1) { // N, N 좌표에 도착 
			count++;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			
			if(state == 1 && i == 2) { // 오른쪽일 때 아래로 못감 
				continue;
			}else if(state == 2 && i == 1) { // 아래일 때 오른쪽으로 못감 
				continue;
			}
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 좌표가 매트릭스를 벗어나면 
			if(nx < 0 || nx >= N ||  ny < 0 || ny >= N)
				continue;
			
			if(matrix[nx][ny] == 1) { // 벽이 아니면 
				continue;
			}
			
			if(i == 0) { // 대각선일때는 위와 왼쪽도 벽이 아닌지 확인해줘야 함 
				if(matrix[x][y+1] == 1 || matrix[x+1][y] == 1) {
					continue;
				}
			}
			movePipe(nx, ny, i);
		}
		return;
	}
```
- DFS로 파이프가 갈 수 있는 방향 끝까지 탐색하는 방식이며, 조건에서 90도 회전은 불가능하다고 했기 때문에 이전의 파이프 상태(state)를 함께 넘겨주어서 오른쪽일 때는 아래로 가지않도록, 아래쪽일때는 오른쪽으로 가지않도록 조건을 넣어주었다. 
- 대각선일 때는 nx, ny를 포함하여 위쪽과 왼쪽까지 3칸을 확인해줘야 하기 때문에 따로 검사해주었다. 


## :black_nib: **Review**
- 처음에 90도 회전이 가능하게 구현하는 바람에 다시 코드를 구현해야했다. 문제를 꼼꼼하게 읽는 습관을 들여야겠다.  