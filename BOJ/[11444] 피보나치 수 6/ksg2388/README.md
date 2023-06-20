# [11444] 피보나치 수 6

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```javascript
function matmul(m1, m2) {
  return [
    [
      m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0],
      m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1],
    ],
    [
      m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0],
      m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1],
    ],
  ];
}

function matmod(m) {
  return [
    [m[0][0] % N, m[0][1] % N],
    [m[1][0] % N, m[1][1] % N],
  ];
}

/**
 * Calculates M^n mod N.
 */
function matpower(M, n) {
  if (n == 1) return matmod(M);
  if (n == 2) return matmod(matmul(M, M));

  if (n % 2n != 0) {
    return matmod(matmul(matmod(matpower(matmod(matpower(M, 2n)), n / 2n)), M));
  } else {
    return matmod(matpower(matmod(matpower(M, 2n)), n / 2n));
  }
}

/**
 * M^n * [0; 1] = [F_n; F_{n+1}]
 */
function fib(n) {
  return matpower(M, n)[0][1];
}
```

- 행렬을 이용해 푼다.

## :black_nib: **Review**

- 수학 문제는 너무 어렵다..........
- 혼자서는 도저히 해결 할 수 없어서 답을 봤지만 이해 불가능이었다.........
- 이게 뭐지?
