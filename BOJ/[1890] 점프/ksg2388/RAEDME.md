# [1890] 점프

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

1. (0, 0)에서 시작해 오른쪽 아래쪽으로 점프하면서 갈 수 있는 경우의 수를 추가한다.

```java
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        // 이 위치로 온 경우가 있는 경우
        if (dp[i][j] > 0) {
            if (i == N - 1 && j == N - 1) {
                break;
            }
            int num = map[i][j];
            // 오른쪽으로 이동하는 경우
            if (j + num < N) {
                dp[i][j + num] += dp[i][j];
            }
            // 아래로 이동하는 경우
            if (i + num < N) {
                dp[i + num][j] += dp[i][j];
            }
        }
    }
}
```

## :black_nib: **Review**

- 계속해서 한 방향으로 쌓아가는 느낌의 문제는 DP를 이용하여 푸는 것이 익숙해졌다.
