# [10282] 해킹

## :pushpin: **Algorithm**

다익스트라

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

- 전형적인 다익스트라 문제였는데, 25%에서 메모리 초과가 계속 발생했다.
- 풀이가 특별할 게 없어보였는데 원인을 못 찾아서 다른 풀이를 보니 클래스를 별도로 만들어서 한 번호에서 갈 수 있는 다른 번호만 `List<List<Node>>` 로 저장해서 사용하고 있었다.
  - 나는 `List<int[]>`를 사용해서 한 번호에서 무조건 N개를 다 봤어야 했다...
- 일부러 이너 클래스를 안 만들고 문제를 풀려고 해서 난 사고인 거 같다.
