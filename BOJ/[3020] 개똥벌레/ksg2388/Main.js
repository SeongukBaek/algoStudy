const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [n, h] = input.shift().split(' ').map(Number);
const top = Array(h + 1).fill(0);
const bottom = Array(h + 1).fill(0);
let minCount = 200001;
let count = 0;

input.forEach((element, index) => {
  if (index % 2 === 0) {
    bottom[element]++;
    return;
  }
  top[element]++;
});

// 낮은 높이로 내려가면서 값 누적
for (let i = h - 1; i > 0; i--) {
  bottom[i] += bottom[i + 1];
  top[i] += top[i + 1];
}

for (let i = 1; i <= h; i++) {
  if (minCount > bottom[i] + top[h - i + 1]) {
    minCount = bottom[i] + top[h - i + 1];
    count = 1;
    continue;
  }
  if (minCount === bottom[i] + top[h - i + 1]) {
    count++;
  }
}

console.log(minCount + ' ' + count);
