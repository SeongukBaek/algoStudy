# [삼성 SW 역량테스트] 싸움땅

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
private static Queue<Integer>[][] map;
```

- 무기 정보는 2차원 우선순위 큐 배열을 사용해서 저장한다.
- 매번 가장 큰 무기를 가져가는 경우밖에 없기 때문이다.

```java
private static void playGame()
```

- 게임은 다음과 같은 순서로 진행된다.
- 플레이어는 한 명씩 차례대로 이동한다.
  - 이동하려는 위치에 플레이어가 없다면, 무기 정보만을 갱신한다.
  - 플레이어가 있다면, 싸운다.
    - 승자와 패자를 가린 후, 각자의 행동을 수행한다.

## :black_nib: **Review**
- 하라는 것만 모두 순서에 맞게 구현하면 딱히 더 머리 쓸 건 없는 문제지만, 항상 중간에 구현을 잘못하는 부분이 생겨서 시간이 오래 걸렸다.
- 지난 삼전 코테에서 풀었을 때는 답도 없이 어려워보였는데, 지금은 좀 더 금방 구현할 수 있었던 것 같다.
- 최대한 돌아가도록 코드를 작성하고 리팩토링하는 방식으로 구현했는데 이를 좀 더 빠르게 할 수 있도록 하자.