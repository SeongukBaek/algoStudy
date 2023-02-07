# [26215] 눈 치우기

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**
- 가장 큰수를 기준으로 priority queue를 이용
- 가장 큰 수 2개의 값에 -1를 해주면서, count값 증가
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 : 큰 숫자
            for (int i = 0; i < n; i++)
                pq.offer(arr[i]);
            while (pq.peek() != 0) {
                int tmp1 = pq.poll() - 1;
                int tmp2 = pq.poll() - 1;
                pq.offer(tmp1);
                pq.offer(tmp2);
                count++;
            }
```

## :black_nib: **Review**
- priority queue의 offer/add, poll/remove, peek/element의 차이에 대해 알게됨
