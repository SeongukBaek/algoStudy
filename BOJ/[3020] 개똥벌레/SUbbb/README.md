# [3020] 개똥벌레

## :pushpin: **Algorithm**

누적합

## :round_pushpin: **Logic**

```java
// 누적합
for (int index = H - 2; index >= 0 ; index--) {
    ceil[index] += ceil[index + 1];
    floor[index] += floor[index + 1];
}

stone = new int[H];
int min = N + 1;

for (int index = 0; index < H; index++) {
    stone[index] = floor[index] + ceil[H - 1 - index];
    min = Math.min(min, stone[index]);
}
```

- 종유석과 석순 배열을 뒤에서부터 누적합을 수행한다.
  - 길이가 긴 장애물부터 누적합을 수행하므로, 누적합한 후의 배열의 값은 해당 길이의 장애물로부터 파괴해야 하는 장애물의 수를 의미하게 된다.
- 이제 1번째 구간부터 파괴해야 하는 석순과 종유석의 수를 확인하면서 최소 장애물 수를 찾는다.

## :black_nib: **Review**

- 입력범위를 보고, 무작정 구현했다가는 시간초과가 날 것임을 알 수 있었다.
- 주어진 값들을 다 쌓아서 한 번에 처리할 수 있지 않을까 싶어서 누적합을 사용해야 하나 했는데, 구체적인 방법이 떠오르지 않아서 참고했다..
- 그리고 이 문제에서 스트림을 사용하면서 알게 된 점을 공유..
  - https://madplay.github.io/post/effectively-final-in-java
