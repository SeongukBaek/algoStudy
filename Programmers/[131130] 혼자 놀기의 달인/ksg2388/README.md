# [131130] 혼자 놀기의 달인

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```javascript
function openBox(idx) {
  cardList[idx] = 1;
  count++;

  if (cardList[cards[idx] - 1] !== 0) {
    return;
  }

  cardList[cards[idx] - 1] = 1;
  openBox(cards[idx] - 1);
}
```

- 박스에 들어있는 수의 인덱스의 박스를 연다.
- 이미 열린 박스라면 멈춘다.

```javascript
// 첫번째 박스가 빈 경우 값 저장
if (firstBox === 0) {
  firstBox = count;
  return;
}
// 두번째 박스가 빈 경우 값 저장
if (secondBox === 0) {
  secondBox = count;
  if (secondBox > firstBox) {
    [firstBox, secondBox] = [secondBox, firstBox];
  }
  return;
}
// 새로운 값이 두번 째 박스의 값보다 큰 경우
if (count > secondBox) {
  secondBox = count;
  if (secondBox > firstBox) {
    [firstBox, secondBox] = [secondBox, firstBox];
  }
}
```

- 2개의 박스에 그룹에 속한 수가 많은 상자들을 저장해둔다.

## :black_nib: **Review**

- DFS를 잘 활용하면 간단하게 구현할 수 있는 문제였다.
