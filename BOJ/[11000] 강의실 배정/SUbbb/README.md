# [11000] 강의실 배정

## :pushpin: **Algorithm**

우선순위 큐, 그리디

## :round_pushpin: **Logic**

```java
private static int countRooms() {
    Queue<Integer> endTimes = new PriorityQueue<>();
    endTimes.add(times.poll().end);
    while (!times.isEmpty()) {
        ClassTime current = times.poll();
        if (current.start >= endTimes.peek()) {
            endTimes.poll();
        }
        endTimes.add(current.end);
    }
    return endTimes.size();
}
```

- 강의 종료 시간을 저장하는 `endTimes`
- 강의 시간 정보를 탐색하면서 현재 가장 먼저 끝나는 강의에 이어서 진행할 수 있는 강의가 있다면, 현재 가장 먼저 끝나는 강의 정보를 큐에서 삭제하고, 현재 강의의 종료 시간을 큐에 삽입한다.
- 마지막에 큐의 크기가 사용할 수 있는 최소의 강의실 개수이다.

## :black_nib: **Review**
- 이전에 C++로 구현했었는데, 다시 보니 우선순위 큐를 사용해야 하는 문제임은 쉽게 파악할 수 있었으나, 구현이 오래 걸렸다.