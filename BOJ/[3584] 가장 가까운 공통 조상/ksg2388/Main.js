const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

const T = Number(input.shift());

for (let tc = 0; tc < T; tc++) {
  const N = Number(input.shift());
  const visited = Array.from({ length: N + 1 }, () => 0); // root 노드를 찾기 위한 배열
  const node = Array.from({ length: N + 1 }, () => []); // 자식 노드의 정보 저장
  const parents = Array.from({ length: N + 1 }, () => 0);
  const level = Array.from({ length: N + 1 }, () => 0);
  let root = 0;

  for (let i = 1; i < N; i++) {
    const [parent, child] = input.shift().split(' ').map(Number);
    node[parent].push(child);
    parents[child] = parent;
    visited[child] = 1;
  }

  // root node 찾기
  for (let i = 1; i <= N; i++) {
    if (visited[i] === 0) {
      root = i;
      break;
    }
  }
  // level 설정
  setTreeLevel(root, node, level);

  const [target1, target2] = input.shift().split(' ').map(Number);

  console.log(findCommonAncestor(target1, target2, parents, level));
}

function setTreeLevel(root, node, level) {
  const start = root;
  let count = 1;
  level[start] = count++;
  const q = [];

  for (let item of node[start]) {
    q.push(item);
  }

  while (q.length) {
    const size = q.length;

    for (let i = 0; i < size; i++) {
      const cur = q.shift();
      level[cur] = count;
      for (let item of node[cur]) {
        q.push(item);
      }
    }
    count++;
  }
}

function findCommonAncestor(a, b, parents, level) {
  let lvA = level[a];
  let lvB = level[b];
  // a가 더 높은 레벨인 경우
  if (lvA > lvB) {
    const diff = lvA - lvB;
    // 레벨 맞추기
    for (let i = 0; i < diff; i++) {
      a = parents[a];
    }
  }
  // b가 더 높은 레벨인 경우
  else if (lvA < lvB) {
    const diff = lvB - lvA;
    // 레벨 맞추기
    for (let i = 0; i < diff; i++) {
      b = parents[b];
    }
  }

  while (a !== b) {
    a = parents[a];
    b = parents[b];
  }
  return a;
}
