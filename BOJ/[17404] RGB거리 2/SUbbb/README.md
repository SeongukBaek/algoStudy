# [17404] RGB거리 2

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
// 누적합
int answer = MAX;
// 첫 집을 하나의 색으로 고정한 후 모든 집을 색칠해보고 마지막에 answer 갱신
for (int color = 0; color < 3; color++) {
    //RED, GREEN, BLUE로 칠하는 경우 각 색을 제외한 나머지는 최댓값으로 초기화해서 사용하지 않도록 한다.
    for (int currentColor = 0; currentColor < 3; currentColor++) {
        if (currentColor == color) {
            dp[0][currentColor] = colorCosts[0][currentColor];
        } else {
            dp[0][currentColor] = MAX;
        }
    }

    // 첫 집 이후의 집들을 색칠하는 최소 비용 계산
    for (int house = 1; house < N; house++) {
        int prevHouse = house - 1;
        dp[house][0] = Math.min(dp[prevHouse][1], dp[prevHouse][2]) + colorCosts[house][0];
        dp[house][1] = Math.min(dp[prevHouse][0], dp[prevHouse][2]) + colorCosts[house][1];
        dp[house][2] = Math.min(dp[prevHouse][0], dp[prevHouse][1]) + colorCosts[house][2];
    }

    // answer 갱신
    for (int currentColor = 0; currentColor < 3; currentColor++) {
        if (currentColor != color) {
            answer = Math.min(answer, dp[N - 1][currentColor]);
        }
    }
}
```

- `dp[i][j] = k` -> 1 ~ i번째 이전 집까지는 모두 색칠했고, 현재 i번째 집을 j색으로 칠하는 최소 비용 k
- 조건 만족을 위해, 첫번째 집을 칠할 색을 고정해두고, 다른 색을 칠하는 비용은 최댓값으로 초기화한다.

## :black_nib: **Review**

- RGB거리와 마찬가지로 DP를 사용하는 문제이다.
  - 다만 조건이 추가되어, 첫번째 집을 칠할 색을 고정하고, 나머지 색에 대해서는 최댓값으로 초기화한 후, 로직을 진행하는 것이 주요한 문제였다.
