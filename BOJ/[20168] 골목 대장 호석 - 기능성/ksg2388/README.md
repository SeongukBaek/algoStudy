# [20168] 골목 대장 호석 - 기능성

## :pushpin: **Algorithm**

백트래킹

## :round_pushpin: **Logic**

```javascript
function dfs(p, totalCost, maxCost) {
  // 가진 돈보다 많이 사용한 경우 종료
  if (totalCost > C) {
    return;
  }
  if (minCost <= maxCost) {
    return;
  }
  // 도착지에 도착한 경우 종료
  if (p === B) {
    minCost = maxCost;
    return;
  }

  for (let next of alley[p]) {
    const [from, cost] = next;
    // 방문한 경우 무시
    if (visited[from]) {
      continue;
    }
    visited[from] = true;
    dfs(from, totalCost + cost, Math.max(maxCost, cost));
    visited[from] = false;
  }
}
```

- 백트레킹을 이용하여 종료 조건을 설정해뒀다.

1. 현재 가진 돈보다 요금을 많이 사용한 경우 종료한다.
2. 현재까지 나온 `minCost`의 값보다 `maxCost`가 크거나 같은 경우 더 볼 필요가 없으므로 종료한다.
3. 목적지에 도착한 경우 종료하고 `minCost`를 갱신해준다.

## :black_nib: **Review**

- 기본적인 백트레킹 문제였다.
- 어떻게 종료조건을 설정하는지가 중요한 문제였다.
