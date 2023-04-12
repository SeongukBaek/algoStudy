# [1932] 정수 삼각형

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
// 초기값 설정
dp[n-1] = triangle[n-1].clone();

for(int i=n-2; i>=0; i--) {
    for(int j=0; j<i+1; j++) {
        // dp[i][j]  = max(왼쪽 위 대각선, 오른쪽 위 대각선)
        dp[i][j] += triangle[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
    }
}
```

- 아래에서 위로 누적하는 식으로 dp를 구현하였다.
- 왼쪽 위 대각선과 오른쪽 위 대각선 중에서 큰 값을 더해 누적값을 만들어 준다.
- 최종적으로 `dp[0][0]`가 최대값을 가지게 된다.

## :black_nib: **Review**

- 요즘 dp문제가 아주 재미지다!
