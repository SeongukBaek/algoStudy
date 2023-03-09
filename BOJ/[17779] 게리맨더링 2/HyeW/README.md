# [17779] 게리맨더링2

## :pushpin: **Algorithm**

시뮬레이션, 브루트포스

## :round_pushpin: **Logic**

```java
private static void getMinPopulationGap() {
  Queue<Border> borders = new ArrayDeque<>();

  // x,y좌표 정하기 -> 1,1부터 N-2,N-2까지
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      borders.clear();
      getBorders(borders, i, j);
      while (!borders.isEmpty()) {
        Border cur = borders.poll();

        // 선거구 인구 구하기
        int gap = getPopulationGap(cur, i, j);
        minPopulation = Math.min(gap, minPopulation);
      }
    }
  }
}
```

모든 좌표를 확인하면서 해당 좌표에서 사용할 수 있는 d1, d2를 `getBorders()`로 구한다.<br/>
구한 좌표는 큐에 담아 사용한다.<br/>

```java
private static int getPopulationGap(Border cur, int x, int y)
```

인구 차이를 구하는 함수는 좌표값을 조정하여 구했다.

## :black_nib: **Review**

- 기본 로직은 괜찮았다. 하지만 인구 수를 구하기 위해 인덱스를 설정하는데 각 구역마다 기준을 정해놓지 않고 감으로 하다보니 많은 시간이 들었다. 문제를 풀기전에 한번 종이에 그려보고 코드를 짜자!
