const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const N = Number(input);

const dp = Array.from({ length: 1001 }, () =>
  Array.from({ length: 2 }, () => Array.from({ length: 3 }, () => 0))
);

dp[1][0][0] = 1;
dp[1][0][1] = 1;
dp[1][1][0] = 1;

for (let i = 1; i < N; i++) {
  dp[i + 1][0][0] = (dp[i][0][0] + dp[i][0][1] + dp[i][0][2]) % 1000000;
  dp[i + 1][0][1] = dp[i][0][0];
  dp[i + 1][0][2] = dp[i][0][1];
  dp[i + 1][1][0] =
    (dp[i][1][0] +
      dp[i][1][1] +
      dp[i][1][2] +
      dp[i][0][0] +
      dp[i][0][1] +
      dp[i][0][2]) %
    1000000;
  dp[i + 1][1][1] = dp[i][1][0];
  dp[i + 1][1][2] = dp[i][1][1];
}

const sum =
  dp[N][0][0] +
  dp[N][0][1] +
  dp[N][0][2] +
  dp[N][1][0] +
  dp[N][1][1] +
  dp[N][1][2];

console.log(sum % 1000000);
