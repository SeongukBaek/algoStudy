const [n, k] = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split(' ')
  .map(Number);

const position = [];
const visited = Array.from({ length: 100001 }, () => 0);
position.push([n, 0]);
visited[n] = true;

while (position.length) {
  let [p, dist] = position.shift();

  if (p === k) {
    console.log(dist);
    break;
  }

  // 순간 이동
  if (p * 2 <= 100000 && !visited[p * 2]) {
    position.unshift([p * 2, dist]);
    visited[p * 2] = true;
  }

  // 이동
  if (p + 1 <= 100000 && !visited[p + 1]) {
    position.push([p + 1, dist + 1]);
    visited[p + 1] = true;
  }
  if (p - 1 >= 0 && !visited[p - 1]) {
    position.push([p - 1, dist + 1]);
    visited[p - 1] = true;
  }
}
