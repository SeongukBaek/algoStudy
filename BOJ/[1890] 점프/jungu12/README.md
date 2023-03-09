# [1890] 점프

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
private static long countRoute() {
	long[][] dp = new long[N][N];
	dp[0][0] = 1;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (dp[i][j] >= 1 && board[i][j] != 0) {
				if (i + board[i][j] < N) {
					dp[i + board[i][j]][j] += dp[i][j];
				}
				if (j + board[i][j] < N) {
					dp[i][j + board[i][j]] += dp[i][j];
				}
			}
		}
	}
	return dp[N-1][N-1];
}
  ```
   - 시작 좌표에서 부터 갈 수 있는 좌표만 방문하며 경로 수를 갱신 해주는 DP방법으로 해결하였다.
   - DP[][] 2차원 배열에 해당 좌표까지 갈 수 있는 경로의 수를 저장하며 도착점 까지 움직인다.
   - DP 배열의 값이 0이라면 갈 수 없는 좌표이기에, task를 수행할 필요가 없다.
   - 최대 경로의 개수가 Integer의 범위를 넘어감으로 Long형으로 DP배열을 만들어 주었다.

  
  
## :black_nib: **Review**
 - 별 생각 없이 재귀로 문제를 풀려고 하였으나, 문제 조건에 '경로의 개수는 2^63-1보다 작거나 같다' 라는 조건이 있어 재귀로는 풀 수 없는 문제였다.
 - 조건에 맞는 자료형을 바로 고르지 못하였다.



  
  	

  
