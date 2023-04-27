# [11279] 최대 힙

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
Queue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

for (int count = 0; count < T; count++) {
    int number = Integer.parseInt(br.readLine());
    if (number == 0) {
        if (heap.isEmpty()) {
            answer.append(0);
        } else {
            answer.append(heap.poll());
        }
        answer.append("\n");
    }

    if (number > 0) {
        heap.add(number);
    }
}
```

- 우선순위 큐를 사용했다.

## :black_nib: **Review**

- 우선순위 큐를 직접 구현하는 문제인 줄 알았으나, 입력 범위가 크지 않아서 우선순위 큐를 활용하면 충분히 가능해보였다.