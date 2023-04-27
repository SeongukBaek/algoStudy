# [92342] 양궁대회

## :pushpin: **Algorithm**

중복조합

## :round_pushpin: **Logic**

```javascript
if (depth === n) {
  let point = calcPoint();
  if (point <= 0) {
    return;
  }
  // 점수 계산
  if (maxPoint < point) {
    answer = target.slice();
    maxPoint = point;
    return;
  }

  if (maxPoint === point) {
    for (let i = 11; i >= 0; i--) {
      if (target[i] < answer[i]) {
        return;
      }
      if (target[i] > answer[i]) {
        answer = target.slice();
        return;
      }
    }
  }
  return;
}

...

const calcPoint = () => {
  let point = target.reduce((acc, cur, idx) => {
    // 라이언이 점수를 얻는 경우
    if (cur > info[idx]) {
      return (acc += 10 - idx);
    }

    // 어피치가 점수를 얻는 경우
    if (info[idx] > 0 && cur <= info[idx]) {
      return (acc -= 10 - idx);
    }

    return acc;
  }, 0);

  return point;
};
```

- 중복조합을 이용한 후 라이언과 어피치의 점수를 계산해 return 해준다.
- 점수가 0보다 작거나 같은 경우 (어피치가 이긴 경우)는 continue를 해준다.
- 기존에 얻은 최대 점수차와 같은 경우는 작은 점수를 더 많이 맞춘 값으로 정해준다.

## :black_nib: **Review**

- 주어진 조건대로 진행하면 해결되는 문제였다.
- 가지치기를 더 할 수 있을 것 같은데 방법이 떠오르지 않는다...
