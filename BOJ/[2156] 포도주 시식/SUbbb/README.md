# [2156] 포도주 시식

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for (int index = 1; index < N; index++) {
    dp[index][0] = wines[index] + dp[index - 1][1];
    dp[index][1] = wines[index] + dp[index - 1][2];
    dp[index][2] = Math.max(dp[index - 1][0], Math.max(dp[index - 1][1], dp[index - 1][2]));
}
```

- 2차원 배열을 사용했다.
  - [i][0] : i - 1번째 포도주를 시식하고, 현재 포도주도 시식하는 경우
  - [i][1] : i - 1번째 포도주를 시식하지 않고, 현재 포도주를 시식하는 경우
  - [i][2] : 현재 포도주를 시식하지 않는 경우

## :black_nib: **Review**

- 처음에는 "현재 포도주를 시식하거나, 시식하지 않거나"와 같이 2가지 경우로 나눠 생각했다.
- 이후에 "이전 포도주를 시식하거나, 시식하지 않는 경우"로 변경했다가, 현재 포도주를 무조건 먹지 않는 경우가 있을 수도 있음을 간과하는 바람에 추가해줬다!
