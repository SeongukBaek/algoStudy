# [11000] 강의실 배정

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

- 강의 시작시간 순으로 정렬
	- 시작시간이 같다면 끝나는 시간 순으로 정렬한다.<br/>
```java
Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        });
```
- 우선순위 큐를 이용
	- 작은 값이 우선순위 
- i의 시작 시간이 i-1의 끝나는 시간보다 크거나 같으면 제거
- 큐의 크기가 강의실이 필요한 개수가 됨

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); 
        pq.offer(time[0][1]);
        for(int i=1; i<n; i++) {
            if(pq.peek() <= time[i][0])
                pq.poll();
            pq.offer(time[i][1]);
        }
```



## :black_nib: **Review**
- 시작시간이 빨라도 끝나는 시간이 언제일지 모르기 때문에, 매번 sort가 필요.
	- 때문에 자동으로 작은 값을 정렬해주는 priorityQueue를 이용
