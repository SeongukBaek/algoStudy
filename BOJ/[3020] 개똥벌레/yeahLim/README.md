# [3020] 개똥벌레

## :pushpin: **Algorithm**

누적합

## :round_pushpin: **Logic**

```java
// 구간 개수 세기
for (int i = 0; i < n / 2; i++) {
    floor[Integer.parseInt(br.readLine())]++;
    ceil[Integer.parseInt(br.readLine())]++;
}

// 누적합
for (int i = h-1; i > 0; i--) {
    floor[i] += floor[i+1];
    ceil[i] += ceil[i+1];
}
```

- 석순과 종유석 배열을 뒤에서부터 누적합을 구한다.
    - 높이가 높은 부분이 낮은 부분에도 반영이 되어야하기 때문에


```java
for (int i = 1; i <= h; i++) {
    bar[i] = floor[i] + ceil[h-i+1];  // 전체 누적합
    if (min > bar[i]) {
        min = bar[i];
        cnt = 1;
    }
    else if (min == bar[i]) {
        cnt++;
    }
}
```
- 구간마다의 석순과 종유석의 전체 수를 구한다.
- 그리고 가장 적은 장애물의 수와 그 구간들의 개수를 구한다.

## :black_nib: **Review**

- 누적합도 일종의 dp라는 것을 알게되었다. 이렇게 응용된 누적합은 낯설다... 어려운 녀석이다..
