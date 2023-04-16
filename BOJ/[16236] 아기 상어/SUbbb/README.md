# [16236] 아기 상어

## :pushpin: **Algorithm**

구현, 그래프 이론, 그래프 탐색, BFS, 시뮬레이션

## :round_pushpin: **Logic**

```c++
int seconds = 0;
// 물고기 탐색 -> 자신보다 작은 물고기를 찾으면, 거리 계산
// 거리 계산과 동시에 거리가 가장 가까운 물고기를 찾기
while (true) {
    findFish();

    if (minFish == null) {
        break;
    }

    // 상어 이동
    map[minFish[0]][minFish[1]] = 0;
    sharkX = minFish[0];
    sharkY = minFish[1];

    // 시간 증가
    seconds += minMove;

    // 상어 먹은 횟수 비교해서, 상어 크기 증가
    if (++eatCount == sharkSize) {
        sharkSize++;
        eatCount = 0;
    }
}
```

- 주어진 로직을 그대로 실행한다.
- 거리가 가장 가까운 물고기를 구하기 위해 BFS를 사용했다.

## :black_nib: **Review**

- 생각보다 간단한 문제여서 구현은 금방했는데, 메모리랑 시간 사용량이 다른 코드에 많이 컸다.
- 우선순위 큐를 사용해서 풀이한 코드들이었는데, 아무래도 나는 매번 BFS를 수행해서 많이 사용하는 것 같다. 