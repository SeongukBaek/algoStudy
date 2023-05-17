# [152995] 인사고과

## :pushpin: **Algorithm**

정렬

## :round_pushpin: **Logic**

```javascript
scores.sort((o1, o2) => {
  return o2[0] + o2[1] - (o1[0] + o1[1]);
});
```

- 사원들의 점수를 합한 총점을 기준으로 내림차순으로 정렬한다.

```java
// 완호 등수 찾기
for (let i = 1; i < scores.length; i++) {
  let isIncentive = true;
  // 인센티브 대상인지 확인
  for (let j = 0; j < i; j++) {
    if (scores[i][0] < scores[j][0] && scores[i][1] < scores[j][1]) {
      isIncentive = false;
      break;
    }
  }
  if (!isIncentive) {
    continue;
  }
  if (scores[i][0] === target[0] && scores[i][1] === target[1]) {
    if (curSum !== scores[i][0] + scores[i][1]) {
      num++;
    }
    grade += num;
    return grade;
  }

  num++;
  if (curSum !== scores[i][0] + scores[i][1]) {
    grade += num;
    num = 0;
  }
}
```

- 완호의 점수라면 즉시 종료하고 등수를 반환한다.
- 인센티브를 받지 못하는 사람을 확인하기 위해 자신보다 총점이 큰 사람들을 탐색하며 확인한다.

## :black_nib: **Review**

- 생각한대로 로직을 세우니까 해결되는 문제였다.
- 하지만 다른 사람들의 풀이를 보니 더 쉽게 풀 수 있는 문제였다.
