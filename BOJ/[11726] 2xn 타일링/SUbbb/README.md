# [11726] 2xn 타일링

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
int doublePrev = 1;
int prev = 2;
int now = 0;

for (int index = 2; index < N; index++) {
    now = (doublePrev + prev) % DIV;
    doublePrev = prev;
    prev = now;
}
```

- 변수 3개를 사용해서 피보나치 수를 구하는 방식으로 N - 1까지의 경우의 수를 구한다.

## :black_nib: **Review**

- 처음에는 배열을 이용해서 피보나치 수를 구하는 방식으로 구현했는데, 피보나치 수를 구할 때 굳이 배열을 사용하지 않고 변수만으로도 가능하다는 것이 생각나서 변수 3개를 사용하는 방식으로 수정했다.