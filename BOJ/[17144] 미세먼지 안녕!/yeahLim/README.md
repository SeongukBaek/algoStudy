# [17144] 미세먼지 안녕!

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
/* t초 동안 실행 */
while(t-- > 0) {
    spreadDust();
    purifyAir();
}
```

- 1초 동안 먼지가 퍼지고, 공기청정기가 작동된다. 따라서 t초동안 spreadDust()와 purifyAir()을 실행한다.

```java
private static int[][] cloneDust(int[][] cpDust) {...}
```

- 미세먼지를 순회하면서 미세먼지를 하나하나 퍼뜨린다.
- 퍼진 미세먼지를 새 배열인 `int[][] newDust` 에 누적시킨다.
- 확산이 끝난 이후 `dust[][]`에 복사한다.

```java
private static void purifyAir() {...}
```

- `int[][] newDust`에 `dust[][]`를 복사한다. -`dust[][]`에 이동된 미세먼지의 값으로 업데이트한다.
- 공기 청정기 바로 앞 좌표는 항상 `0` 으로 업데이트한다.

## :black_nib: **Review**

- 문제 풀기 전에, 미리 수기로 로직을 다 작성하고 진행했더니 손쉽게 풀렸다.
- 알고리즘을 풀 때 주의할 점 : 1턴만 돌릴때랑, 여러 턴을 돌릴 때 정답이 달라지지 않도록 로직을 짜야한다.
