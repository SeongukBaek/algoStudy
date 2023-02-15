# [11660] 구간 합 구하기 5

## :pushpin: **Algorithm**

DP, 누적 합, 구간 합 

## :round_pushpin: **Logic**

```java
// 구간합을 구한 매트릭스 
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
			}
		}
```
- matrix 원본 배열은 두고, dp 구간 합을 구하는 배열을 따로 만들어주었다. [ i-1 ][ j ]까지의 구간 합 + [ i ][ j-1 ]까지의 구간 합 - 겹치는 구간 + 마지막 남은 한 칸(원본 matrix 값)을 구하여 해당 좌표까지의 구간 합을 구하였다.

```java
int x1, y1, x2, y2; // 좌표 
		int sum;
		
		for(int i = 0; i < M; i++) {
			sum = 0;
			
			str = br.readLine().split(" ");
			x1 = Integer.parseInt(str[0]);
			y1 = Integer.parseInt(str[1]);
			x2 = Integer.parseInt(str[2]);
			y2 = Integer.parseInt(str[3]);
			
			sum = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
			
			System.out.println(sum);
		}
```
- 끝 좌표(x2, y2)까지의 구간 합에서 시작 좌표(x1, y1) 바깥의 합들을 빼주고 2번 빠지는 곳은 한 번 다시 더해줘서 (x1, y1)부터 (x2, y2)까지의 원하는 구간의 합을 구할 수 있었다. 

## :black_nib: **Review**
-  처음에 이중 for문을 통해서 각 행의 누적 합을 구해서 풀었는데 시간초과가 나왔다. 위 방법을 이해하는데 시간이 조금 걸렸지만 빠르고 간단한 방법을 배울 수 있어서 의미있는 시간이었다. 