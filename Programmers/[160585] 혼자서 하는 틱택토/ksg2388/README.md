# [160585] 혼자서 하는 틱택토

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

잘못된 경우

- O개수와 X개수의 차이가 2개 이상인 경우
- X개수가 O개수보다 1개 많은 경우
- O, X 둘 다 틱택토를 완성한 경우
- O가 이겼는데 O와 X의 개수가 같은 경우

```javascript
// O의 개수가 X와 같거나 1개 더 많지 않은 경우
let diff = Math.abs(oCount - xCount);

if (diff > 1) {
  return 0;
}

if (xCount > oCount) {
  return 0;
}
```

- 위 조건중 첫번째 조건 확인

```javascript
// 둘 다 이긴 경우
if (xEnd && oEnd) {
  return true;
}
// 선공만 이긴 경우
if (oEnd && oCount <= xCount) {
  return true;
}
// 후공만 이긴 경우
if (xEnd && oCount != xCount) {
  return true;
}
```

- 위 조건중 아래 3가지 조건 확인

## :black_nib: **Review**

- 단순한 구현문제였는데 코드를 깔끔하게 정리하는게 생각보다 어려웠다...
