# [172927] 광물 캐기

## :pushpin: **Algorithm**

구현, 그리디 알고리즘

## :round_pushpin: **Logic**

```javascript
for (let i = 0; i < size; i++) {
  let diaPoint = 0;
  let ironPoint = 0;
  let stonePoint = 0;

  for (let j = 0; j < 5; j++) {
    if (i * 5 + j === minerals.length) {
      break;
    }
    if (minerals[i * 5 + j] === 'diamond') {
      diaPoint += 1;
      ironPoint += 5;
      stonePoint += 25;
      continue;
    }
    if (minerals[i * 5 + j] === 'iron') {
      diaPoint += 1;
      ironPoint += 1;
      stonePoint += 5;
      continue;
    }
    if (minerals[i * 5 + j] === 'stone') {
      diaPoint += 1;
      ironPoint += 1;
      stonePoint += 1;
    }
  }
  mineralSet.push([diaPoint, ironPoint, stonePoint]);
}

mineralSet = mineralSet.slice(0, pickCnt);
mineralSet.sort((a, b) => b[2] - a[2]);
```

- `mineralSet`을 5개씩 묶어 에 각각의 곡괭이에 대한 합 피로도를 저장한다.
- 이후 곡괭이의 개수만큼 mineralSet을 자른다.
- 돌곡괭이로 사용되는 피로도를 기준으로 내림차순으로 정렬한다.

```javascript
for (let i = 0; i < mineralSet.length; i++) {
  // 다이아 곡괭이가 있는 경우
  if (picks[0] > 0) {
    picks[0]--;
    answer += mineralSet[i][0];
    continue;
  }
  // 철 곡괭이가 있는 경우
  if (picks[1] > 0) {
    picks[1]--;
    answer += mineralSet[i][1];
    continue;
  }
  picks[2]--;
  answer += mineralSet[i][2];
}
```

- 광물을 다이아 곡괭이부터 순서대로 사용해서 캔다.

## :black_nib: **Review**

- 대략적인 아이디어를 생각해내 풀었지만 마지막에 광물을 캐는 부분에서 생각이 꼬여 쉽게 풀리지 않았다.
- 결국 다른사람의 조언의 참고하여 정렬을 하고 곡괭이를 순서대로 사용하는 방법으로 해결했다.
