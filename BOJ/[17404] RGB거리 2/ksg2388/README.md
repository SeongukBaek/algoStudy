# [17404] RGB거리 2

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```javascript
// 첫 집을 각각 R, G, B로 칠하는 경우 확인
for (let start = 0; start < 3; start++) {
  let cur = Array(3).fill(map[0][start]);
  let prev = [0, 0, 0];

  // 2번집 칠하기
  for (let i = 0; i < 3; i++) {
    if (start === i) {
      prev[i] = MAX;
      continue;
    }
    prev[i] = map[0][start] + map[1][i];
  }
  cur = [...prev];

  // 나머지 집 칠하기
  for (let i = 2; i < n; i++) {
    prev[0] = Math.min(cur[1], cur[2]) + map[i][0];
    prev[1] = Math.min(cur[0], cur[2]) + map[i][1];
    prev[2] = Math.min(cur[0], cur[1]) + map[i][2];

    cur = [...prev];
  }

  for (let i = 0; i < 3; i++) {
    if (start === i) {
      continue;
    }
    result.push(cur[i]);
  }
}
```

- 첫번째 집을 각각 R, G, B로 칠하는 경우 모두를 확인한다.
- 두 번째 집과 마지막 집의 경우는 첫번째로 칠했던 색과 달라야 하기 때문에 그 경우는 따로 처리를 해준다.

## :black_nib: **Review**

- 기존의 RGB거리1과 점화식 자체는 같은 문제였다.
- 기존의 점화식에 첫번째와 마지막 집의 경우가 색이 같지 않아야한다는 조건이 추가되어서 첫번째 집을 R, G, B로 칠하는 경우로 두고 나올 수 있는 최소값들 중 가장 작은 값을 결과로 출력하였다.
