# [11279] 최대 힙

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
for(int i = 0; i < n; i++) {
    int v = Integer.parseInt(br.readLine());
    if(v == 0) {
        getMaxValue();
    }
    maxHeap.add(v);
}
System.out.println(sb);
```
- `PrioriyQueue`를 사용해서 문제를 해결했다.

## :black_nib: **Review**

- 이 문제는 우선순위 큐를 사용할 수 있는지 묻는 문제인건가...
- 연산 개수 100,000개이기때문에 출력을 하려면 StringBuilder를 하는게 좋다.