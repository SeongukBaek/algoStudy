# [20166] 문자열 지옥에 빠진 호석

## :pushpin: **Algorithm**

DFS, Map

## :round_pushpin: **Logic**

```javascript
for (let i = 0; i < N; i++) {
  wordList.push(input.shift().split(''));
}

for (let i = 0; i < K; i++) {
  const word = input.shift();
  wordMap.set(word, 0);
  wordCase.push(word);
  maxLength = Math.max(word.length, maxLength);
}
```

- map을 이용하여 찾아야하는 신의 문자열들을 저장해둔다.

```javascript
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
```

- DFS를 이용하여 8방향을 탐색한다.
- 구해야하는 문자열이 나오는 경우 `map`에서 값을 1 증가시켜준다.

## :black_nib: **Review**

- BFS로도 풀 수 있었지만 DFS가 더 구현이 쉬워 DFS를 이용하여 문제를 풀었다.
