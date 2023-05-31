# [17404] RGB거리 2

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
/* DP : 집 색칠할 수 있는 모든 비용 구하기 */
for (int start = 0; start < 3; start++) {
    for (int end = 0; end < 3; end++) {
        dp[start][end][seq] = Math.min(dp[start][(end+1)%3][seq - 1], dp[start][(end+2)%3][seq - 1]) + cost[end];
    }
}
```

-  dp[시작 색][끝 색][순서]

```java
/* 최소 비용 구하기 */
int minCost = Integer.MAX_VALUE;
for (int start = 0; start < 3; start++) {
    for (int end = 0; end < 3; end++) {
        //시작 색, 끝 색 같을 경우
        if (start == end) {
            continue;
        }

        minCost = Math.min(dp[start][end][n], minCost);
    }
}
```
- 모든 집의 비용의 최소값 = min(마지막이 빨강색인 집의 최소값, 마지막이 파랑색인 집의 최소값, 마지막이 초록색인 집의 최소값)

## :black_nib: **Review**

- dp를 3차원으로 푼 것은 처음이다. dp와 더 깊은 관계를 맺는 시간이었다..
