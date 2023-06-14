# [14676] 영우는 사기꾼?

## :pushpin: **Algorithm**

위상정렬

## :round_pushpin: **Logic**

```javascript
// 건물 사이 관계 저장
for (let i = 0; i < M; i++) {
  const [from, to] = input[i].split(' ').map(Number);
  relation[from].push(to);
  indegrees[to]++;
}
```

- 건물 간의 관계를 그래프로 저장하면서 선행 건물이 필요한 건물들의 indegrees를 1씩 증가시킨다.

```javascript
// 건물이 건설되는 경우
if (command === 1) {
  // 건설 가능한 경우
  if (indegrees[num] === 0) {
    // 기존 건물이 존재하지 않는 경우
    if (infra[num] === 0) {
      relation[num].forEach((element) => {
        indegrees[element]--;
      });
    }
    infra[num]++;
  }
  // 건설 불가능한 경우
  else {
    console.log('Lier!');
    return;
  }
}
// 건물이 파괴되는 경우
if (command === 2) {
  // 건물이 없는데 파괴 된 경우
  if (infra[num] === 0) {
    console.log('Lier!');
    return;
  }
  // 건물이 하나만 존재하는 경우
  if (infra[num] === 1) {
    relation[num].forEach((element) => indegrees[element]++);
  }
  infra[num]--;
}
```

- 건물을 건설할 때

  1. indegrees가 0이고 기존 건물이 존재하지 않는 경우 건물을 건설하고 관계있는 건물들의 indegrees를 1씩 줄인다.
  2. 기존 건물이 존재하는 경우는 건물의 개수만 1증가시킨다.
  3. indegrees가 0이 아닌 경우는 건물을 지을 수 없기 때문에 종료한다.

- 건물을 파괴할 때
  1. 건물이 존재하지 않는 경우는 종료한다.
  2. 건물이 1개인 경우 건물의 개수를 1줄이고 관계있는 건물들의 indegrees를 1감소 시킨다.
  3. 건물이 2개 이상인 경우 건물의 개수만 1감소 시킨다.

## :black_nib: **Review**

- 처음 풀 때는 단순히 구현으로 풀었는데 시간이 너무 오래 걸리길래 다른 카테고리를 확인했다.
- 위상정렬 문제인 것을 확인하고 위상정렬 방법으로 해결하니 시간이 8배 단축되었다. Very Good!
