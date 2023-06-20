# [2156] 포도주 시식

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
// 초기값 설정
dp[1] = wine[1];

// n = 1일 경우 예외처리
if (n > 1) {
    dp[2] = wine[1] + wine[2];
}

for (int i = 3; i <= n; i++) {
    dp[i] = Math.max(dp[i-1], Math.max(dp[i-2], dp[i-3] + wine[i-1]) + wine[i]);
}
```

- 

## :black_nib: **Review**

- 요즘 dp문제가 어렵드아
