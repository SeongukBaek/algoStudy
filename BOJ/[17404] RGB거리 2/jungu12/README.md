# [17404] RGB거리 2

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
private static void calCost() {
    for (int color = 0; color < 3; color++) {
        dp[0][color][color] = houses[0][color];
        dp[0][(color - 1 + 3) % 3][color] = 1000000;
        dp[0][(color + 1 + 3) % 3][color] = 1000000;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j][color] = Math.min(dp[i - 1][(j - 1 + 3) % 3][color], dp[i - 1][(j + 1 + 3) % 3][color])
                        + houses[i][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            //첫 번째 집과 마지막 집의 색이 같은 경우
            if (j == color) {
                dp[N - 1][j][color] = Integer.MAX_VALUE;
                continue;
            }

            dp[N - 1][j][color] = Math.min(dp[N - 2][(j - 1 + 3) % 3][color], dp[N - 2][(j + 1 + 3) % 3][color])
                    + houses[N - 1][j];
        }
    }
}
```

- 첫 번째 집과 마지막 집의 색깔이 같을 수 없으며, 첫 번째 집의 색이 무엇이냐에 따라 N - 1번째 DP 배열의 값이 바뀐다. -> dp 배열을 3차원으로 선언함
- bottom-up 방식으로 1번째 집부터 최소 비용을 dp배열에 저장한다.

## :black_nib: **Review**

- 최근에 비슷한 문제들을 좀 풀어봐서 금방 3차원 배열을 활용해야 한다는 아이디어를 떠올릴 수 있었다.
