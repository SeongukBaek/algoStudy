# [11726] 2xn 타일링

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```javascript
let prev = 1; // n = 0부터 시작
let cur = 1; // n = 1인 경우

if (n > 1) {
  for (let i = 2; i <= n; i++) {
    [cur, prev] = [(cur + prev) % 10007, cur];
  }
}
```

- 현재 값과 이전 값을 이용해 다음 값을 구하는 식으로 피보나치 수열을 이용하여 문제를 해결하였다.

## :black_nib: **Review**

- 처음에는 피보나치 수열인지 파악하지 못했지만 규칙을 따라가다보니 피보나치 수열과 같다는 것을 알게 되었다.
