const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const n = BigInt(input);

const M = [
  [0n, 1n],
  [1n, 1n],
];

const N = 1000000007n;

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

console.log(fib(n).toString());
