const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const [n, m] = input.shift().split(' ').map(Number);
const map = input.map((line) => line.split('').map(Number));
let zeroCount = [0];
let dx = [1, 0, 0, -1];
let dy = [0, -1, 1, 0];
let curNum = 2;
let answer = '';

// 0을 구역별로 나누어 개수 저장
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (map[i][j] === 0) {
      map[i][j] = curNum;
      makeGroup(i, j);
      curNum++;
    }
  }
}

// 벽을 부수고 이동할 수 있는 칸 확인
for (let i = 0; i < n; i++) {
  let str = '';
  for (let j = 0; j < m; j++) {
    if (map[i][j] === 1) {
      str += `${checkMove(i, j) % 10}`;
      continue;
    }
    str += '0';
  }
  answer += `${str}\n`;
}

console.log(answer);

// 0의 구역을 그룹별로 나누는 메서드
function makeGroup(x, y) {
  let queue = [];
  let count = 1;
  queue.push([x, y]);

  while (queue.length) {
    let cur = queue.shift();

    for (let i = 0; i < 4; i++) {
      let nx = cur[0] + dx[i];
      let ny = cur[1] + dy[i];

      if (isMapIn(nx, ny) && map[nx][ny] === 0) {
        queue.push([nx, ny]);
        map[nx][ny] = curNum;
        count++;
      }
    }
  }
  zeroCount.push(count);
}

function checkMove(x, y) {
  let set = new Set();
  let cnt = 1;

  // 상, 하, 좌, 우 방향에 0 그룹이 있는지 확인
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    // 0 그룹이 존재하는 경우
    if (isMapIn(nx, ny) && map[nx][ny] > 1) {
      const id = map[nx][ny] - 1;
      if (!set.has(id)) {
        set.add(id);
        cnt += zeroCount[id];
      }
    }
  }

  return cnt;
}

function isMapIn(x, y) {
  return x < n && y < m && x >= 0 && y >= 0;
}
