# [181188] 요격 시스템

## :pushpin: **Algorithm**

우선순위 큐, 그리디

## :round_pushpin: **Logic**

```java
PriorityQueue<int[]> missile = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
```

- 이 코드의 핵심은 미사일 범위의 끝을 기준으로 오름차순으로 정렬하는 것이다.

```java
while (!missile.isEmpty()) {
    baseMs = missile.poll();
    count++;

    // 기준 미사일의 범위와 겹치는지 확인
    while(!missile.isEmpty()) {
        comparedMs = missile.peek();
        // 겹치는 경우
        if (comparedMs[0] < baseMs[1]) {
            missile.poll();
        }
        // 아닌 경우
        else {
            break;
        }
    }
}
```

- 기준 미사일을 기준으로 미사일을 하나하나 비교한다.
- 겹치지 않은 미사일이 나오면 비교를 멈추고, 그 미사일을 기준 미사일로 변경한다.
- 기준 미사일의 개수만큼 count값을 +1 해준다.

## :black_nib: **Review**

- 이런 그리디 문제는 정렬이 핵심이라는 것을 페어에게 배운 적이 있다. 그 부분을 고려하여 풀었더니, 바로 정답나와버려서 기분이 좋았다.
