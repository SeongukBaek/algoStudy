# [131704] 택배상자

## :pushpin: **Algorithm**

스택, 큐

## :round_pushpin: **Logic**

1. 초기 컨테이너를 큐로 생성해 order의 수만큼 상자를 싣는다.
2. order를 순서대로 확인하면서 현재 order의 값이 컨테이너 가장 앞 상자의 번호보다 크다면 보조컨테이너로 상자를 하나씩 옮긴다.
3. 이 과정을 반복하다가 주문 받은 상자를 꺼낼 수 없을때 종료하고 꺼낸 상자의 개수를 출력한다.

```java
public static boolean loadBox(int num, Deque<Integer> container, Stack<Integer> subContainer) {
    // 주문받은 번호가 컨테이너 가장 앞의 숫자보다 큰 경우
    while (!container.isEmpty() && container.peek() < num) {
        subContainer.add(container.poll());
    }

    // 컨테이너 가장 앞의 숫자와 주문받은 숫자를 확인
    if (!container.isEmpty() && num == container.peek()) {
        container.poll();
        return true;
    }

    // 보조 컨테이너 가장 앞의 숫자와 주문받은 숫자를 확인
    if (!subContainer.isEmpty() && num == subContainer.peek()) {
        subContainer.pop();
        return true;
    }
    return false;
}
```

- 주문받은 번호가 컨테이너 가장 앞의 수보다 큰 경우는 그 수가 될때까지 컨테이너의 상자를 보조 컨테이너로 옮겨준다.
- 주문받은 번호의 상자를 꺼낼 수 있는 경우 true를 리턴해준다.

## :black_nib: **Review**

- 문제를 읽고 적절한 자료구조를 이용해 쉽게 풀 수 있는 문제였다.
