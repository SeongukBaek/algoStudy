# [11726] 2xn 타일링

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
/* dp : 2xn의 사각형 방법 구하기 */
dp[1] = 1;
dp[2] = 2;
for(int i=3; i<=n; i++) {
    dp[i] = (dp[i-2] + dp[i-1]) % 10007;
}
```

- 초기값을 설정하고, dp를 이용하여 풀었다.

## :black_nib: **Review**

- 숫자 하나만 입력 받는 것이라 원래 Scanner를 이용하여 풀었는데, BufferedReader로 채점해보니 약 2배정도 빨라져서 br로 수정하였다.
