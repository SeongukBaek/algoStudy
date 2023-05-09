# [161988] 연속 펄스 부분 수열의 합

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```javascript
let seq1 = sequence.map((item, idx) => {
  if (idx % 2 === 0) {
    return item * -1;
  }
  return item;
});
let seq2 = sequence.map((item, idx) => {
  if (idx % 2 === 1) {
    return item * -1;
  }
  return item;
});
```

- 기존의 sequence배열을 이용해 2개의 펄스배열을 만든다.

```javascript
const dp1 = [seq1[0]];
const dp2 = [seq2[0]];

for (let i = 1; i < sequence.length; i++) {
  dp1[i] = Math.max(dp1[i - 1] + seq1[i], seq1[i]);
  dp2[i] = Math.max(dp2[i - 1] + seq2[i], seq2[i]);
  max = Math.max(dp1[i], dp2[i], max);
}
```

- 점화식 => DP[N] = max(DP[N-1] + K번째 원소, K번째 원소)
- 점화식을 이용하여 배열을 DP배열을 채우고 dp배열 중 가장 큰 값을 최대값에 넣는다.

## :black_nib: **Review**

- 생각보다 쉽게 풀리는 문제였다.
