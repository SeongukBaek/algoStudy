# [17406] 배열 돌리기 4

## :pushpin: **Algorithm**

순열

## :round_pushpin: **Logic**
```java
static void perm(int toSelect, int[] selected) {
		if(toSelect == R) {
			// 배열 초기화 
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					copy[i][j] = matrix[i][j];
				}
			}
			
			for(int i = 0; i < R; i++) {
				int index = selected[i];
				rotate(list.get(index));
			}
			
			for(int i = 0; i < N; i++) {
				int sum = 0;
				
				for(int j = 0; j < M; j++) {
					sum += copy[i][j];
				}
				
				if(min > sum) {
					min = sum;
				}
			}
			return;
		}
		
		for(int i = 0; i < R; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[toSelect] = i;
				perm(toSelect + 1, selected);
				visited[i] = false;
			}
		}
	}
```
 - 회전할 때 원본 배열의 값이 바뀌지 않도록 copy를 만들어줬다. 
 - 각 행에 있는 수들을 더해서 sum값과 min값을 비교해서 행의 최소합 min을 구했다.

```java
    static void rotate(Info info) {
		int r = info.r;
		int c = info.c;
		int s = info.s;
		
		int startx = r - s; 
		int starty = c - s;
		int endx = r + s; 
		int endy = c + s; 
	
		// 시계방향으로 회전 할 것임 
		for(int i = 0; i < s; i++) {
			int x = startx + i;
			int y = starty + i;
			int temp = copy[x][y];
			int d = 0;
			
			// 4방향으로 바꿔주면서  옮기기 
			while(d < 4) {
				int xx = x + dx[d];
				int yy = y + dy[d];
				
				if (xx >= startx + i && xx <= endx - i && yy >= starty + i && yy <= endy - i) {
					copy[x][y] = copy[xx][yy];
					x = xx;
					y = yy;
				}else {
					d++;
				}
			}
			// temp에 담아둔 모서리 부분 마지막에 옮기기 
			copy[x][y + 1] = temp;
		}
	}
```
- 시작좌표와 끝좌표를 받아서 시계방향으로 방향을 바꿔주면서 범위를 벗어나지 않으면 배열 요소를 옮겨줬다. 
- 모서리 부분은 따로 저장해줬다가 마지막에 넣어주었다.

## :black_nib: **Review**
- 배열을 돌릴때 4변을 모두 for문을 통해서 교환해주려고 했는데 어려워서 다른 풀이를 참고했다. 훨씬 간단하고 이해하기 쉬워서 좋은 방법을 배운 것 같다!
