# [1932] 정수 삼각형

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
dp[0][0] = triangle[0][0];

for (int row = 1; row < N; row++) {
    // col == 0
    dp[row][0] = triangle[row][0] + dp[row - 1][0];

    for (int col = 1; col < row; col++) {
        dp[row][col] = triangle[row][col] + Math.max(dp[row - 1][col - 1], dp[row - 1][col]);
    }

    // col == row
    dp[row][row] = triangle[row][row] + dp[row - 1][row - 1];
}
```

- 이전 행의 삼각형 숫자 중 현재 위치로 이동할 수 있는 값들에서 최댓값을 찾아 현재 행으로 더한다.

## :black_nib: **Review**

- 매우 쉬운 형태의 DP!
