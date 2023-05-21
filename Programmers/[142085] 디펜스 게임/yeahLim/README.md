# [142085] 디펜스 게임

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
for (int i = 0; i < enemy.length; i++) {
        pq.offer(enemy[i]);

        // 무적권 사용 개수보다 사이즈가 커질 경우
        if (pq.size() > k) {
            n -= pq.poll();
        }

        // n의 개수
        if (n < 0) {
            return i;
        }
    }
    return enemy.length;
}
```

- 한 라운드씩 우선순위 큐에 넣어주면서,
- 무적권 사용 개수보다 큐의 크기가 커질 경우, 가장 적은 적의 수를 우선순위 큐에서 꺼내 병사의 수를 빼준다.
- 위를 병사가 0보다 작을 때까지 반복해준다.

- 최종적으로, 가능한 라운드의 수를 반환할 수 있게 된다.

## :black_nib: **Review**

- Priority Queue를 for문에서 하나하나 넣으면서 확인하는 방법은 한번도 사용해 보지 않아서인지 생각이 나지 않았다. 앞으로 다른 문제에서도 Priority Queue를 잘 활용해봐야겠다.
