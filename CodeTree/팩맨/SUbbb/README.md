# [삼성 SW 역량테스트] 팩맨

## :pushpin: **Algorithm**

구현, 시뮬레이션, 중복 순열

## :round_pushpin: **Logic**

```java
private static void makePackManRoutes(int[] route, int count) {
  if (count == 3) {
    packManRoutes.add(route.clone());
    return;
  }

  for (int move = 0; move < 8; move += 2) {
    route[count] = move;
    makePackManRoutes(route, count + 1);
  }
}
```

- 중복 순열을 이용해 우선순위를 기반으로 한 모든 경로를 저장한다.

```java
for (int turn = 1; turn <= t; turn++) {
  // 몬스터 복제 시도
  // 현재 자기 위치에 그대로 하나 생성
  // eggs에만 추가
  copyMonsters();

  // 몬스터 이동
  // 이동하지 못해, 방향을 -1하는 경우 : 몬스터 시체가 있거나, 팩맨, 혹은 격자 밖인 경우 반복
  // -> 모든 방향 탐색 후 이동하지 못하면 이동 X
  moveMonsters();

  // 팩맨 이동
  // 조합을 사용해서 미리 이동 선택지를 만들어두고, 매번 해당 조합에서 최대값 찾아 이동하기
  // 먹은 후 해당 자리에는 시체 두기
  // 현재 자리에 있는 몬스터는 먹지 않고, 알도 먹지 않음 -> 따라서 최대 3개의 몬스터만 먹을 수 있음
  movePackMan();

  // 몬스터 시체 소멸
  // 2턴동안만 유지됨.
  removeDeadMonsters();

  // 몬스터 복제 완성
  hatchEggs();
}
```

- 주어진 순서대로 로직을 실행한다.

## :black_nib: **Review**
- 거의 5시간 넘게 걸렸으나 끝내 시간초과를 해결하지 못했다.
- 주어진 정보를 저장하는 방식에서 너무 많은 자료구조를 사용하려 해서, 이어지는 로직 구현에서 많은 시간을 소요할 수밖에 없었다.
- 좀 더 간단하게 정보를 저장하도록 해야겠다.