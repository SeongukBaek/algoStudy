# [150369] 택배 배달과 수거하기

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
for (int index = 0; index < n; index++) {
    // 배달해야 하는 박스의 개수만큼 해당 지점까지의 거리를 스택에 저장
    for (int count = 0; count < deliveries[index]; count++) {
        deliver.add(index + 1);
    }
    // 수거해야 하는 박스의 개수만큼 해당 지점까지의 거리를 스택에 저장
    for (int count = 0; count < pickups[index]; count++) {
        pickup.add(index + 1);
    }
}
```

- 배달용 스택과 수거용 스택에, 각 지점별 처리해야 하는 박스 개수만큼 각 지점까지의 거리를 저장한다.
- 스택을 이용하기에, 가장 멀리 떨어진 배달 또는 수거해야하는 지점부터 처리할 수 있다.
- 또한 거리를 박스 개수만큼 저장해서, 처리해야 하는 지점을 바로 계산할 수 있다.

```java
private void processDeliverAndPickup(Stack<Integer> deliver, Stack<Integer> pickup) {
    // 배달과 수거 중 하나라도 끝날 때까지 수행
    while (!deliver.isEmpty() && !pickup.isEmpty()) {
        // 배달과 수거 각각의 가장 멀리 떨어진 거리
        int lastDeliver = deliver.peek();
        int lastPickup = pickup.peek();

        // cap만큼 배달과 수거를 수행
        for (int count = 0; count < cap; count++) {
            if (!deliver.isEmpty()) {
                deliver.pop();
            }
            if (!pickup.isEmpty()) {
                pickup.pop();
            }
        }

        // 배달과 수거 중 더 멀리 떨어진 거리까지 진행!
        answer += Math.max(lastDeliver, lastPickup) * 2L;
    }
}
```

- 배달과 수거 중 하나라도 완료할 때까지 진행한다.
- 둘 중 더 멀리 떨어진 거리를 선택하고, 트럭의 최대 가용 박스 개수(cap)만큼 배달과 수거를 진행한다.(스택에서 제거)
- 이후, 남은 배달과 수거를 따로 처리한다.

## :black_nib: **Review**

- 이런 비슷한 문제들을 몇 번 본 거 같은데 매번 획기적인 아이디어가 바로 생각 안 나는 것 같다.
- 매번 박스 개수 자체에 초점을 뒀는데, 개수만큼 거리를 저장하는 방식은 유용한 것 같다.