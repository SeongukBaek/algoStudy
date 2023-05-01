# [92341] 주차 요금 계산

## :pushpin: **Algorithm**

Map

## :round_pushpin: **Logic**

```java
// 차 번호별 입차 시간을 저장하는 배열
private final int[] carTimes = new int[10000];
// 차 번호별 누적 주차 시간을 저장하는 map
private final int[] cumulativeTimes = new int[10000];
```

- 차 번호별 입차 시간을 저장한다.
  - 해당 번호의 출차 기록이 나오면, 누적 주차 시간을 계산해서 누적 주차 시간 저장 배열에 저장한다.

## :black_nib: **Review**

- 문제는 간단하다. 예전에 풀었을 때는 Map을 사용해서 풀었었는데, 이번에는 이를 배열로 바꿔서 풀어보았다.
- 차 번호가 10000개까지 가능해서, 10000개짜리 배열을 2개 만들어서 그런지 탐색 시간으로 인해서 시간이 훨씬 더 많이 사용되었다.
