# [14500] 테트로미노 

## **Algorithm**
- 부르트포스 알고리즘 

## **logic**
``` java
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
```
- 중간을 기준으로 검사할 x좌표와 y좌표(왼, 위, 오, 아)

```java
static void dfs(int n, int i, int j, int sum) {
		
		if(n == 4) {
			return;
		}
		
		for(int a = 0; a<4; a++) {
			int xx = i+dx[a];
			int yy = j+dy[a];
			
			if(xx >= 0 & xx < N) {
				if(yy >= 0 && yy < M) {
					if(!visit[xx][yy]) {
						visit[xx][yy] = true;
						sum += matrix[xx][yy];
						if(sum > max)
							max = sum;
						dfs(n+1, xx, yy, sum);
						visit[xx][yy] = false;
						sum -= matrix[xx][yy];
					}
				}
			}
		}
	}
```
- 총 5가지 모양의 테트로미노를 구할 수 있는데, 이들을 회전한 모양까지 구하려면 dfs를 통해 한 번에 탐색하는 것이 좋다고 생각했다. 한 가지 예외 모양을 빼고 4가지 모양은 matrix를 돌면서 탐색할 주변 좌표값(4방향) 배열을 가지고 좌표 값을 구해서 dfs로 탐색해주었다.
```java 
	static void exshape(int i, int j) {
		int sum = matrix[i][j];
		
		if((i + 1 < N && i >= 0) && (j - 1 >= 0 && j + 1 < M)) {
			sum += matrix[i][j-1] + matrix[i+1][j] + matrix[i][j+1];
			if(sum > max) max = sum;
		}
		sum = matrix[i][j];
		
		if((i - 1 >= 0 && i < N) && (j - 1  >= 0 && j + 1 < M)) {
			sum += matrix[i][j-1] + matrix[i-1][j] + matrix[i][j+1];
			if(sum > max) max = sum;
		}
		
		sum = matrix[i][j];
		if((i - 1 >= 0 && i + 1 < N) && (j + 1 < M && j >= 0)) {
			sum += matrix[i-1][j] + matrix[i][j+1] + matrix[i+1][j];
			if(sum > max) max = sum;
		} 
		
		sum = matrix[i][j];
		if((i - 1 >= 0 && i + 1 < N) && (j - 1 >= 0 && j < M)) {
			sum += matrix[i][j-1] + matrix[i-1][j] + matrix[i+1][j];
			if(sum > max) max = sum;
		}
	}
```
- 나머지 예외 모양은 직접 좌표를 찾아서 sum을 구해서 처리했다. 

## **review**
- 예외 모양처리 하면서 인덱스 맞추는데서 시간을 많이 썼다. matrix의 행과 열이 N, M으로 다른 것을 고려하지 못했다. 
