# [11279] 최대 힙

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());


/* Heap : priorityqueue로 최대 힙 구하기 */
for(int i=0; i<n; i++) {
    int num = Integer.parseInt(br.readLine());
    if(num == 0) {
        if(pq.isEmpty()) {
            sb.append("0\n");
        }
        else {
            sb.append(pq.poll()).append("\n");
        }
    }
    else {
        pq.offer(num);
    }
}
```

- 우선순위 큐를 이용하고 StringBuilder를 통해서 출력했다..

## :black_nib: **Review**

- 우선순위 큐를 활용하면 쉽게 풀 수 있는 문제이다.
