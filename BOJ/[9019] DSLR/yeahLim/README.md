# [9019] DSLR

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
Deque<String> oq = new ArrayDeque<>(); // 명령어 큐
Deque<Integer> nq = new ArrayDeque<>(); // 숫자 큐
```

- 두 개의 queue를 사용해, 명령어를 넣는 큐와 숫자를 넣는 큐를 구현했다.

```java
while(!oq.isEmpty()) {
    int curNum = nq.poll();
    String order = oq.poll();

    if(curNum == endNum) {
        answer = order;
        return;
    }

    int nextNum;
    // D
    nextNum = (curNum * 2) % 10000;
    if(!visited[nextNum]) {
        visited[nextNum] = true;
        nq.offer(nextNum);
        oq.offer(order + "D");
    }
    // S
    nextNum = (curNum == 0) ? 9999 : curNum - 1;
    if(!visited[nextNum]) {
        visited[nextNum] = true;
        nq.offer(nextNum);
        oq.offer(order + "S");
    }
    // L
    nextNum = (curNum % 1000) * 10 + (curNum / 1000);
    if(!visited[nextNum]) {
        visited[nextNum] = true;
        nq.offer(nextNum);
        oq.offer(order + "L");
    }
    // R
    nextNum = (curNum % 10) * 1000 + (curNum / 10);
    if(!visited[nextNum]) {
        visited[nextNum] = true;
        nq.offer(nextNum);
        oq.offer(order + "R");
    }
}
```

- nextNum에 D,S,L,R의 연산을 각각 진행한 결과값을 저장한다.
- 방문하지 않은 nextNum이라면, 숫자 큐에는 nextNum을, 명령어 큐에는 각각의 연산을 넣어준다.

## :black_nib: **Review**

- 하나의 큐에 클래스를 저장한는 방법도 있지만, 두 개의 큐를 써보고 싶어서 이렇게 구현했다.
