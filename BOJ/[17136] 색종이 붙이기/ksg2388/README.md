# [17136] 색종이 붙이기

## :pushpin: **Algorithm**

브루트 포스, 백트래킹

## :round_pushpin: **Logic**

1. (0, 0)에서 시작해서 (9, 9)까지 이동하면서 색종이를 붙일 수 있는 경우를 확인한다.
2. 색종이를 붙일 수 있는 경우 큰 색종이부터 내림차순으로 확인해보면서 모든 경우를 다 확인해본다.

```java
static int[] confetti = { 5, 5, 5, 5, 5 };

// 큰 색종이부터 순서대로 붙일 수 있나 확인
for (int i = 4; i >= 0; i--) {
    if (checkConfetti(x, y, i + 1, map)) {
        int[][] newMap = new int[10][10];
        copyArray(newMap, map);
        if (confetti[i] == 0) {
            return;
        }
        confetti[i]--;
        glueConfetti(x, y + i + 1, count + 1, paste(x, y, i + 1, newMap));
        confetti[i]++;
    }
}
```

- 색종이의 개수 배열의 값들을 각각 5로 초기화 시켜둔다.
- 색종이를 붙일 수 있는 경우 복사한 배열에 색종이를 붙인 후 그 다음 좌표로 이동하여 계속 확인한다.
- 도중 색종이의 개수가 남지 않은 경우는 거기서 종료한다.

## :black_nib: **Review**

- 쉽게 구현할 수 있는 방법이 있지 않을까 많이 고민을 해봤지만 결국 모든 경우를 다 확인해보는 방법으로 구현했다.
- 2차원 배열의 인덱스를 다루는 방법이 많이 익숙해졌다.
