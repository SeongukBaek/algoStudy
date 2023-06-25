# [150366] 표 병합

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```javascript
function convertNum(x, y) {
  return 50 * (Number(x) - 1) + Number(y);
}
```

- 50 \* 50 사이즈의 2차원 배열을 2500 사이즈의 1차원 배열로 바꾸어 사용하기 위한 메서드

```javascript
function mergeSell(reminder) {
  const [x1, y1, x2, y2] = reminder;
  const num1 = merge[convertNum(x1, y1)];
  const num2 = merge[convertNum(x2, y2)];
  let value = sell[num2];
  if (sell[num1] !== 'EMPTY') {
    value = sell[num1];
  }

  for (let i = 1; i < 2501; i++) {
    if (merge[i] === num2) {
      merge[i] = num1;
      sell[i] = 'EMPTY';
    }
  }
  sell[num1] = value;
}
```

- merge 시 처음 위치의 `merge` 데이터 값에 저장된 값을 두번쨰 위치의 `merge` 데이터 값에 저장되어있는 모든 값들에 넣어 병합 정보를 저장시킨다.

```javascript
function unmergeSell(reminder) {
  const [r, c] = reminder;
  const num = convertNum(r, c);
  const idx = merge[num];
  const value = sell[idx];

  for (let i = 1; i < 2501; i++) {
    if (merge[i] === idx) {
      merge[i] = i;
      sell[i] = 'EMPTY';
    }
  }
  sell[num] = value;
}
```

- unmerge 시 초기 값으로 설정을 바꾸어 주고 sell의 값들을 다 EMPTY로 초기화 시킨다.
- 이후 r, c의 위치에 병합 해제 전의 값을 넣어준다.

## :black_nib: **Review**

- 처음에는 union find를 사용하여 풀려고 노력했지만 2차원에서 union find를 사용하여 문제를 해결하려고 하니 머리가 안돌아갔다...
- 그래서 방법을 찾아보다가 문제에서 요구되는 사항이 큰 사이즈가 아니어서 단순 구현으로도 돌아간다는 사실을 알고 단순 구현으로 풀었다.
