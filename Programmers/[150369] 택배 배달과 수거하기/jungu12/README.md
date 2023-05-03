# [150369] 택배 배달과 수거하기

## :pushpin: **Algorithm**

Stack

## :round_pushpin: **Logic**

```java
Stack<Integer> dStack = new Stack<>();
Stack<Integer> pStack = new Stack<>();

for (int i = 0; i < n; i++) {
    for (int j = 0; j < deliveries[i]; j++) {
        dStack.push(i + 1);
    }

    for (int j = 0; j < pickups[i]; j++) {
        pStack.push(i + 1);
    }
}
```

- n(집의 수)가 최대 100,000까지 가능하여 배열을 하나하나 탐색하는 방법은 비효율적이라 생각했다.
- 그래서 stack을 사용하여 배열을 압축해주었다.

```java
while (!dStack.isEmpty() && !pStack.isEmpty()) {
    int lastD = dStack.peek();
    int lastP = pStack.peek();

    for (int i = 0; i < cap; i++) {
        if (!dStack.isEmpty()) dStack.pop();
        if (!pStack.isEmpty()) pStack.pop();
    }

    answer += Math.max(lastD, lastP) * 2;
}
```

- stack의 가장 위에 값은 가장 먼 집까지의 거리임으로 저장해놓고 나중에 answer에 더해준다.
- cap만큼 stack에서 pop해준다.

## :black_nib: **Review**

- 배열로 풀다가 시간 초과 났다..
- stack은 다른 자료구조보다 비교적 더 손이 안가는거 같다.. 더 많이 풀어보자.
