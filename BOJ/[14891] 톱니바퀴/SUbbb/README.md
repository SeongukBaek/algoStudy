# [14891] 톱니바퀴

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
// 톱니 정보 저장
saveGears();

// 회전 수행
rotateGears();

// 톱니 상태 확인 후 출력
System.out.println(computePoint());
```

- 톱니바퀴의 정보를 저장한다.
- 회전을 수행한다.
- 최종 톱니바퀴 상태에 따른 점수를 출력한다.

```java
private static void saveGears() throws IOException {
    gears = new ArrayList<>();
    br = new BufferedReader(new InputStreamReader(System.in));

    for (int index = 0; index < GEARS; index++) {
        gears.add(new StringBuilder(br.readLine()));
    }
}
```

- 톱니바퀴 정보를 맨 앞 삭제, 맨 뒤 삭제, 맨 앞 삽입, 맨 뒤 삽입에 용이하도록 `StringBuilder`를 사용했다.
  - 이를 통해 시계방향 회전과 반시계방향 회전을 구현할 수 있다.

```java
private static void rotateGear(int gear, boolean isClockwise);
```

- 현재 회전할 톱니바퀴와 방향 정보를 받아 회전한다.
- 이때 왼쪽과 오른쪽 톱니에 대해 확인하고 추가 회전이 필요하다면 재귀 호출을 수행한다.

## :black_nib: **Review**

- 예전보다 시간과 메모리를 조금이나마 효율적으로 사용했다.
- 주어진 문제 풀이 순서에 맞게 함수를 나누는 것으로 좀 더 코딩을 쉽게 할 수 있었다.
