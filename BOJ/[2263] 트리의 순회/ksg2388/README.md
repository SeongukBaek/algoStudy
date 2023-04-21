# [2263] 트리의 순회

## :pushpin: **Algorithm**

트리, 분할정복

## :round_pushpin: **Logic**

```javascript
const DFS = (inStart, inEnd, postStart, postEnd) => {
  if (inStart > inEnd || postStart > postEnd) return;
  const root = postOrder[postEnd];
  ans += root + ' ';
  let idx = indexArr[root];
  const leftCnt = idx - inStart;
  DFS(inStart, idx - 1, postStart, postStart + leftCnt - 1);
  DFS(idx + 1, inEnd, postStart + leftCnt, postEnd - 1);
};
```

- postOrder의 맨 마지막 숫자가 Root -> 그 Root 값을 inOrder에서 찾아 index 값을 기억 -> 그 index를 기준으로 postOrder를 다시 index의 앞, 뒤로 서브트리 분리.

- 위 과정을 반복하면서 preOrder를 구한다.

## :black_nib: **Review**

- 트리에 대한 지식 부족으로 풀이를 참고했다.
- 분할정복에 대해서도 익숙하지 않아서 풀이를 봤는데도 어려웠다...
