const [n, io, po] = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');
const indexArr = Array(n);
const inOrder = io.split(' ').forEach((e, i) => (indexArr[e] = i));
const postOrder = po.split(' ');
let ans = '';

const DFS = (inStart, inEnd, postStart, postEnd) => {
  if (inStart > inEnd || postStart > postEnd) return;
  const root = postOrder[postEnd];
  ans += root + ' ';
  let idx = indexArr[root];
  const leftCnt = idx - inStart;
  DFS(inStart, idx - 1, postStart, postStart + leftCnt - 1);
  DFS(idx + 1, inEnd, postStart + leftCnt, postEnd - 1);
};

DFS(0, n - 1, 0, n - 1);
console.log(ans);
