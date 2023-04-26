# [11279] 최대 힙

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

int N = Integer.parseInt(br.readLine());
int x;
for(int i = 0; i < N; i++) {
	x = Integer.parseInt(br.readLine());
	if(x != 0) {
		pq.add(x);
		continue;
	}
	if(!pq.isEmpty()) {
		System.out.println(pq.poll());
		continue;
	}
	System.out.println(0);
}
```

- 최대 값을 출력하고 제거해야하니 내림차순으로 우선순위 큐를 정렬해준다.

## :black_nib: **Review**

- 자바에는 heap을 구현한 우선순위 큐 class가 있어 정말 간단하게 풀 수 있는 문제였다 !
