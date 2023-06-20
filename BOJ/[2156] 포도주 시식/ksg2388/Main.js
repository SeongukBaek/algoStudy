const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let [n, ...arr] = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n')
  .map(Number);

console.log(findMaxGrape(n));

function findMaxGrape(n) {
  let dp = Array(n).fill(0);

  if (n === 1) {
    return arr[0];
  }

  if (n === 2) {
    return arr[0] + arr[1];
  }

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

  return dp[n - 1];
}
