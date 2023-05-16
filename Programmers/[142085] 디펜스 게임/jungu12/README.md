# [142085] 디펜스 게임

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
void defense() {
    for (int i = 0; i < enemy.length; i++) {
        //남은 무적권이 없고, 남은 병사가 현재 적보다 적은 경우
        if(k == 0 && n < enemy[i]) {
            return;
        }

        //남은 무적권이 있고, 남은 병사가 현재 적보다 적은 경우
        if (n < enemy[i]) {
            usedSoldiers.add(enemy[i]);
            n += usedSoldiers.poll();
            k--;
            n -= enemy[i];
            continue;
        }

        //남은 병사로 현재 적을 막을 수 있는 경우
        usedSoldiers.add(enemy[i]);
        n -= enemy[i];
    }
}
```

- 매 라운드 마다 막은 병사의 수를 usedSoldiers(우선순위 큐)에 저장한다.
- 남은 병사로 적을 막을 수 없는 라운드에 도달하면 usedSoldiers에서 맨 앞 index를 무적권을 사용해서 막은 것으로 바꾼다.
- 무적권이 없는데, 더 이상 적을 막을 수 없는 경우까지 위 과정을 반복한다.

## :black_nib: **Review**

- 문제의 로직을 생각하기가 다소 어려웠다.
- 로직을 생각해내니 구현하는데는 오래 걸리진 않았다.
