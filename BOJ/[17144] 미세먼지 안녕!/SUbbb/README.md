# [17144] 미세먼지 안녕!

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
private static int goodByeDust() {
    while(T-- > 0) {
        spreadDust();
        runCleaner();
    }
    return countDust();
}
```

- 코드는 먼지를 확산시키고, 공기청정기를 동작시킨다. 정해진 시간이 경과한 후, 전체 미세 먼지를 반환한다.

```java
private void spreadDust() {...}
```

- 미세 먼지를 확산시킨다.
- 이때 업데이트할 미세 먼지 정보를 `int[][] tmp` 에 저장하고, 확산이 끝난 이후 `room[][]` 을 업데이트한다.

```java
private void runCleaner() {...}
```

- 공기 청정기의 바람 방향을 따라 순차적으로 미세 먼지를 이동시킨다.
- 공기 청정기 바로 앞 좌표는 항상 `0` 으로 업데이트한다.

## :black_nib: **Review**

- 이전에는 큐를 이용해서 미세먼지 정보를 저장했었는데, 메모리를 너무 많이 잡아먹었다.
- 따라서 배열을 탐색해서 미세먼지를 찾도록 수정해서 메모리 사용량을 줄일 수 있었다.
