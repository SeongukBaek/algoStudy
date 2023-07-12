const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const [N, K] = input.shift().split(' ').map(Number);
const list = input.shift().split(' ').map(Number);
let minLength = Infinity;
let start = 0;
let end = -1;
let count = 0;

// 초기 위치 찾기
for (let i = 0; i < list.length; i++) {
  if (list[i] === 1) {
    if (count === 0) {
      start = i;
    }
    count++;
  }

  if (count === K) {
    end = i;
    minLength = end - start + 1;
    break;
  }
}

console.log(findMinLength(start, end));

function findMinLength(start, end) {
  // K개 이상을 만족하는 집합이 없는 경우
  if (end === -1) {
    return -1;
  }

  // 다음 구간 탐색
  while (end < list.length) {
    if (list[start] === 1) {
      count--;
    }
    start += 1;
    end += 1;
    if (list[end] === 1) count++;
    // 구간 집합 안에 K개의 라이언 인형이 있는 경우
    if (count === K) {
      // 범위 재조정
      if (list[start] === 2) {
        while (list[start] !== 1 && start < end) {
          start++;
        }
      }
      // console.log(start, end);
      minLength = end - start + 1;
    }
  }

  return minLength;
}
