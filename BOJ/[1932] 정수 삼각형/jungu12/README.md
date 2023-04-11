# [1932] 정수 삼각형

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for (int i = 1; i < N; i++) {
	dp[i][0] = dp[i - 1][0] + triangle[i][0];
	dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
	for (int j = 1; j < i; j++) {
		dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
	}
}
```

- 문제에서 '아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.'라고 명시 되어 있다.
- 이를 '위층에 있는 대각선 왼쪽 또는 대각선 오른쪽 수 중 큰 수를 선택할 수 있다.'로 해석 가능하다.

## :black_nib: **Review**

- 로직을 생각하기 크게 어렵지 않은 DP문제였다.
