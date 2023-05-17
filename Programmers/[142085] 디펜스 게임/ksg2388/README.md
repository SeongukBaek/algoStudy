# [142085] 디펜스 게임

## :pushpin: **Algorithm**

구현, 우선순위큐

## :round_pushpin: **Logic**

```javascript
enemy.slice(0, k).forEach((e) => pq.push(e));

for (let i = k; i < enemy.length; i++) {
  pq.push(enemy[i]);
  let temp = pq.pop();
  if (temp + capacity > n) {
    return i;
  }
  capacity += temp;
}
```

- 무적권의 개수만큼 라운드를 우선순위 큐에 담고 시작한다.
- 이후 남은 라운드부터 시작하면서 우선순위큐에 값을 담고 가장 작은 값을 빼서 남은 병사의 수에서 빼준다.
- 남은 병사의 수가 0보다 작아지면 종료한다.

## :black_nib: **Review**

- 우선순위큐를 사용하지 않고 해보려고했지만 계속 시간초과가 나서 어쩔 수 없이 직접 구현하여 사용했다.
- 생각보다 쉽지 않은 문제였다.
