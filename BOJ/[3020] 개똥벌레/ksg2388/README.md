# [3020] 개똥벌레

## :pushpin: **Algorithm**

누적합

## :round_pushpin: **Logic**

```javascript
input.forEach((element, index) => {
  if (index % 2 === 0) {
    bottom[element]++;
    return;
  }
  top[element]++;
});
```

- 종유석과 석순의 크기별 갯수를 각각 저장한다.

```javascript
// 낮은 높이로 내려가면서 값 누적
for (let i = h - 1; i > 0; i--) {
  bottom[i] += bottom[i + 1];
  top[i] += top[i + 1];
}

for (let i = 1; i <= h; i++) {
  if (minCount > bottom[i] + top[h - i + 1]) {
    minCount = bottom[i] + top[h - i + 1];
    count = 1;
    continue;
  }
  if (minCount === bottom[i] + top[h - i + 1]) {
    count++;
  }
}
```

- 낮은 높이로 내려가면서 그 값들을 누적시킨다.
- 그리고 높이 1부터 h까지 각각 부딪히는 종유석과 석순의 개수를 확인하며 최소값과 최소값의 개수를 확인한다.

## :black_nib: **Review**

- 문제를 보자마자 누적합을 사용해야겠다는 생각은 들었지만 어떤 방식으로 구현할지가 쉽지 않았다.
- 결국 값을 미리 다 저장시켜두고 높이가 높은 곳에서 낮은곳으로 값을 누적시키는 방법을 선택했다.
