# [9465] 스티커

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
dp[0][0] = score[0][0];
dp[1][0] = score[1][0];

if (N > 1) {
	dp[0][1] = dp[1][0] + score[0][1];
	dp[1][1] = dp[0][0] + score[1][1];
}

for (int i = 2; i < N; i++) {
	dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + score[0][i];
	dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + score[1][i];
}

System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
```

```
   - 위쪽 스티커를 하나 골랐다면 그 다음 고를 수 있는 스티커는 아래쪽의 다음번째 혹은 다음다음번째이다.
   - 위 로직을 위 아래 다 적용하여 최대값을 구해준다.


## :black_nib: **Review**
 - 로직 생각하기 까다로웠다..







```
