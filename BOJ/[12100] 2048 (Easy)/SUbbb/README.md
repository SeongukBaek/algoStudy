# [12100] 2048 (Easy)

## :pushpin: **Algorithm**

구현, 스택, 백트래킹

## :round_pushpin: **Logic**

```java
private static void move(int[][] movedMap, int count) {
    if (count == 5) {
        return;
    }

    for (int m = 0; m < 4; m++) {
        move(moveMap(movedMap, m), count + 1);
    }
}
```

- 백트래킹으로 5번까지 이동할 방향을 정하고 이동한다.

```java
private static int[][] moveMap(int[][] target, int move) {
    Stack<Integer> numbers;
    int[][] movedMap = new int[N][N];
    boolean isPlus;
    int number;

    ...
}
```

- 스택을 이용해 이동하면서 합쳐지는 숫자들을 처리한다.
- 또한 boolean 값으로 합쳐진 숫자들이 다시 합쳐지지 않도록 한다.

## :black_nib: **Review**
- 해당 게임은 예전에도 몇번 해본 적이 있어서 잘 알고 있었고, 어떻게 하면 최대 수가 나올 수가 있을지 정해진 공식이 있는 게 아니라고 생각해서 결국 다 해보는 수밖에 없다고 판단했다.
- 이럴 때는 백트래킹 방식으로 각 이동 시의 상태를 함수의 인자로 전달해주면서 모든 경우를 돌려봐야했고, 숫자 합치기는 스택을 이용해서 구현해보았다.
- 구현 문제는 가닥만 얼추 잡으면 구현하는데서는 많이 막히지 않는 것 같다. 시간을 좀 더 줄여볼 수 있도록 하자.