const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const [N, M, K] = input.shift().split(' ').map(Number);

const infra = Array(N + 1).fill(0);
const relation = Array.from({ length: N + 1 }, () => Array());
let indegrees = new Array(N + 1).fill(0);

// 건물 사이 관계 저장
for (let i = 0; i < M; i++) {
  const [from, to] = input[i].split(' ').map(Number);
  relation[from].push(to);
  indegrees[to]++;
}

checkInfra();

function checkInfra() {
  // 건물 건설 정보 확인
  for (let i = M; i < M + K; i++) {
    const [command, num] = input[i].split(' ').map(Number);
    // 건물이 건설되는 경우
    if (command === 1) {
      // 건설 가능한 경우
      if (indegrees[num] === 0) {
        // 기존 건물이 존재하지 않는 경우
        if (infra[num] === 0) {
          relation[num].forEach((element) => {
            indegrees[element]--;
          });
        }
        infra[num]++;
      }
      // 건설 불가능한 경우
      else {
        console.log('Lier!');
        return;
      }
    }
    // 건물이 파괴되는 경우
    if (command === 2) {
      // 건물이 없는데 파괴 된 경우
      if (infra[num] === 0) {
        console.log('Lier!');
        return;
      }
      // 건물이 하나만 존재하는 경우
      if (infra[num] === 1) {
        relation[num].forEach((element) => indegrees[element]++);
      }
      infra[num]--;
    }
  }
  console.log('King-God-Emperor');
  return;
}
