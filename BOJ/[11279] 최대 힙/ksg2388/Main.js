const input = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');
const n = Number(input[0]);
let idx = 0;
const heap = [0];

const h_push = (num) => {
  heap[++idx] = num;
  for (let i = idx; i > 1; i = Math.floor(i / 2)) {
    // 부모 노드가 자신보다 작은 경우 swap
    if (heap[Math.floor(i / 2)] < heap[i]) {
      [heap[i], heap[Math.floor(i / 2)]] = [heap[Math.floor(i / 2)], heap[i]];
    } else break;
  }
};

const h_pop = () => {
  if (idx === 0) return 0;
  const num = heap[1];
  heap[1] = heap[idx--];

  // 힙 다시 정렬
  for (let i = 1; i * 2 <= idx; ) {
    if (heap[i] > heap[i * 2] && heap[i] > heap[i * 2 + 1]) {
      break;
    }
    // 왼쪽 자식이 더 큰 경우
    else if (heap[i * 2] > heap[i * 2 + 1]) {
      [heap[i], heap[i * 2]] = [heap[i * 2], heap[i]];
      i = i * 2;
    }
    // 오른쪽 자식이 더 큰 경우
    else {
      [heap[i], heap[i * 2 + 1]] = [heap[i * 2 + 1], heap[i]];
      i = i * 2 + 1;
    }
  }
  return num;
};

let ans = '';
for (let i = 1; i <= n; i++) {
  const num = Number(input[i]);
  if (num === 0) {
    ans += h_pop() + '\n';
  } else h_push(num);
}

console.log(ans);
