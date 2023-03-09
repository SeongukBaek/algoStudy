# [19237] 어른 상어

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
int time = 0;
while (sharks.size() > 1 && time <= 1000) {
    moveSharks();
    decreaseSmells();
    spreadSmells();
    time++;
}

```

- 로직의 순서는 다음과 같
  다.
  - 상어들을 이동시킨다.
  - 이전의 냄새들을 1씩 감소시킨다.
  - 이동한 상어의 좌표에 냄새를 뿌린다.

```java
// 상어가 위를 향할때
if(shark.head == 1) {

    // 빈 공간이면 이동
    for(int i=1; i<=4; i++) {
        if(checkSpace(shark.direction[1][i], shark)) return;
    }

    // 자신의 냄새가 있는 공간이면 이동
    for(int i=1; i<=4; i++) {
        if(checkOwnSmell(shark.direction[1][i], shark)) return;
    }
}
```

- 빈공간이 없을경우 자신의 냄새가 있는 공간으로 이동한다
- 너무 힘들다

## :black_nib: **Review**

- 너무 힘들어.. 하지만 나 배추가 아니기 때문에 포기하지 않는다!!!!!!!!!!!!!!!!!!!!!!!!!
