# [77485] 행렬 테두리 회전하기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```javascript
let temp = map[x2][y2];
let list = [];
list.push(temp);
// 반시계로 돌면서 값 전달
// 위 -> 아래
for (let i = x2; i > x1; i--) {
  list.push(map[i][y2]);
  map[i][y2] = map[i - 1][y2];
}
// 왼쪽 -> 오른쪽
for (let i = y2; i > y1; i--) {
  list.push(map[x1][i]);
  map[x1][i] = map[x1][i - 1];
}
// 아래 -> 위
for (let i = x1; i < x2; i++) {
  list.push(map[i][y1]);
  map[i][y1] = map[i + 1][y1];
}
// 오른쪽 -> 왼쪽
for (let i = y1; i < y2 - 1; i++) {
  list.push(map[x2][i]);
  map[x2][i] = map[x2][i + 1];
}
```

- 오른쪽 가장 아래 값을 `temp`에 저장 시켜두고 반시계방향으로 돌면서 배열을 회전시킨다.

## :black_nib: **Review**

- 배열 돌리기 문제를 많이 풀다보니 인덱스 계산에 익숙해져 빠르게 풀 수 있었다. ㅎㅎ
