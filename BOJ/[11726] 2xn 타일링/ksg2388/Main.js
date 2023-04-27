let fs = require('fs');
let input = fs.readFileSync('/dev/stdin').toString();

let n = Number(input);
let prev = 1; // n = 0부터 시작
let cur = 1; // n = 1인 경우

if (n > 1) {
  for (let i = 2; i <= n; i++) {
    [cur, prev] = [(cur + prev) % 10007, cur];
  }
}

console.log(cur);
