const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');
let answer = '';

const dx = [-1, -1, 0, 1, 1, 1, 0, -1];
const dy = [0, 1, 1, 1, 0, -1, -1, -1];
const [N, M, K] = input.shift().split(' ').map(Number);
const wordList = [];
let maxLength = 0;
const wordMap = new Map();
const wordCase = [];

for (let i = 0; i < N; i++) {
  wordList.push(input.shift().split(''));
}

for (let i = 0; i < K; i++) {
  const word = input.shift();
  wordMap.set(word, 0);
  wordCase.push(word);
  maxLength = Math.max(word.length, maxLength);
}

// 모든 칸에서 탐색
for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    findWord(i, j, wordList[i][j]);
  }
}

for (let word of wordCase) {
  answer += `${wordMap.get(word)}\n`;
}

console.log(answer);

function findWord(x, y, curWord) {
  if (wordMap.get(curWord) !== undefined) {
    wordMap.set(curWord, wordMap.get(curWord) + 1);
  }

  if (curWord.length === maxLength) {
    return;
  }

  // 8방향 탐색
  for (let i = 0; i < 8; i++) {
    let nx = (x + dx[i] + N) % N;
    let ny = (y + dy[i] + M) % M;

    findWord(nx, ny, curWord + wordList[nx][ny]);
  }
}
