# [150369] 택배 배달과 수거하기

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
int delIdx = skipNoBoxHouse(deliveries, n-1);
int pickIdx = skipNoBoxHouse(pickups, n-1);
while(!isFinished(delIdx, pickIdx)){
  // 이동 거리 구하기
  move += Math.max(delIdx+1,pickIdx+1);
  // 택배 배달하기
  delIdx = carryBox(deliveries, delIdx);
  // 택배 수거하기
  pickIdx = carryBox(pickups, pickIdx);
  // 배달할 택배가 없는 집 스킵하기
  delIdx = skipNoBoxHouse(deliveries, delIdx);
  // 수거할 택배가 없는 집 스킵하기
  pickIdx = skipNoBoxHouse(pickups, pickIdx);
}
```

- 원점에서 목표 지점을 갈 때 택배를 배달하고 돌아올 때 수거를 한다고 생각했다.
  그래서 `deliveries`와 `pickups` 배열을 뒤에서부터 확인하며 `cap`만큼 택배를 빼주었다.

1. 배달/수거할 택배가 있는 집을 뒤에서 찾는다.
2. 배달, 수거할 집 중 가장 먼 집을 트럭이 이동할 거리로 잡는다.
3. 택배를 배달/수거한다.
4. 배달/수거를 하지 않아도 되는 집을 건너띈다.
5. 모든 택배를 배달/수거할 때까지 2,3,4 번을 반복한다.

## :black_nib: **Review**

- 처음에 스택 자료구조를 사용할까 하다가 포인터하나만 잘 다루면 배열을 스택처럼 사용할 수 있어서 그냥 주어진 배열을 사용해서 해결했다!
  - 인덱스 다루는 게 살짝 까다로워서 조금 헤맸다. 다음엔 그냥 자료구조를 사용해야겠다.