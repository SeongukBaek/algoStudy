# [2156] 포도주 시식

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```javascript
dp[0] = arr[0];
dp[1] = dp[0] + arr[1];
dp[2] = Math.max(dp[1], arr[1] + arr[2], arr[0] + arr[2]);

for (let i = 3; i < n; i++) {
  dp[i] = Math.max(
    dp[i - 1], // 1, 2번째 마시기
    dp[i - 2] + arr[i], // 1, 3번째 마시기
    dp[i - 3] + arr[i - 1] + arr[i] // 2, 3번째 마시기
  );
}
```

- 포도주를 3잔 연속으로 마실 수 없기 때문에 세울 수 있는 점화식은
  dp[i] = Math.max(
  dp[i - 1],
  dp[i - 2] + arr[i],
  dp[i - 3] + arr[i - 1] + arr[i]
  );
  이 된다.

## :black_nib: **Review**

- 문제를 읽자마자 DP로 풀어야겠다는 생각은 들었는데 점화식을 떠올리는데 시간이 조금 걸렸다.
- DP문제들은 다 조금씩 비슷하면서 조금씩 달라서 어렵다...
