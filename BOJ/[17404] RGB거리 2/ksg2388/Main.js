const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const MAX = 1000 * 1000 + 1;
const n = input.shift();
const map = input.map((line) => line.split(' ').map(Number));
const result = [];

// 첫 집을 각각 R, G, B로 칠하는 경우 확인
for (let start = 0; start < 3; start++) {
  let cur = Array(3).fill(map[0][start]);
  let prev = [0, 0, 0];

  // 2번집 칠하기
  for (let i = 0; i < 3; i++) {
    if (start === i) {
      prev[i] = MAX;
      continue;
    }
    prev[i] = map[0][start] + map[1][i];
  }
  cur = [...prev];

  // 나머지 집 칠하기
  for (let i = 2; i < n; i++) {
    prev[0] = Math.min(cur[1], cur[2]) + map[i][0];
    prev[1] = Math.min(cur[0], cur[2]) + map[i][1];
    prev[2] = Math.min(cur[0], cur[1]) + map[i][2];

    cur = [...prev];
  }

  for (let i = 0; i < 3; i++) {
    if (start === i) {
      continue;
    }
    result.push(cur[i]);
  }
}

console.log(Math.min(...result));
