# [181188] 요격 시스템

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```javascript
targets.sort((e1, e2) => {
  return e1[1] - e2[1];
});
```

- 요격해야할 구간을 끝나는 시간을 기준으로 오름차순으로 정렬한다.

```javascript
for (let i = 1; i < targets.length; i++) {
  // 범위 밖인 경우
  if (end <= targets[i][0]) {
    start = targets[i][0];
    end = targets[i][1];
    count++;
    continue;
  }
}
```

- 다음 구간을 확인하면서 이전구간의 범위 안에 있는 경우는 계속 다음 구간을 확인한다.
- 범위 밖인 경우 요격 수를 1증가시키고 다시 시작과 끝 범위를 정하고 탐색한다.

## :black_nib: **Review**

- 정렬해서 푼다는 것을 쉽게 떠올리지 못했다.
- 정렬만 하면 쉽게 풀리는 문제라서 좋았다.
