# [142085] 디펜스 게임

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
for (int index = 0; index < enemy.length; index++) {
    enemies.add(enemy[index]);

    // 막아야 하는 적 라운드 수가 무적권을 사용해서 막을 수 있는 라운드 수보다 많아지면, 그 중 가장 작은 적이 있는 라운드를 병사로 디펜스!
    if (enemies.size() > k) {
        n -= enemies.poll();
    }

    // 만약 병사 수가 음수가 된다면, 더 이상 막을 수 없음!
    if (n < 0) {
        return index;
    }
}
```

- 우선순위 큐를 사용해서, 가장 작은 적만을 병사로 디펜스하도록 한다.

## :black_nib: **Review**

- 각 라운드에 대해서, 병사로 막거나, 무적권을 사용하거나, 막지 못하거나 하는 3가지 경우가 존재했다.
- 하지만 이를 모두 확인하는 방식은 시간초과가 무조건 발생할 것 같았고, 큐를 사용하는 아이디어를 참고했다.
