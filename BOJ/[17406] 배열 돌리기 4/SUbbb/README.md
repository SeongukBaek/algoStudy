# [17406] 배열 돌리기 4

## :pushpin: **Algorithm**

순열, 구현

## :round_pushpin: **Logic**

```java
private static void makeOrders(int[] order, boolean[] isSelected, int size) {
    if (size == K) {
        copy();
        operateByOrders(order);
        min = Math.min(min, computeRowSum());
        return;
    }
    
    for (int i = 0; i < K; i++) {
        if (isSelected[i]) {
            continue;
        }
        order[size] = i;
        isSelected[i] = true;
        makeOrders(order, isSelected, size + 1);
        isSelected[i] = false;
    }
}
```

- 가능한 모든 연산 순서를 순열로서 구한다.

## :black_nib: **Review**
- 로직은 생각보다 금방 짰다. 순열을 이용해 가능한 모든 연산 순서를 생성한 후, 최소값을 찾았다.
- 하지만 배열에 대한 초기화와 복사를 수행하지 않아서, 아까운 시간을 허비했다.
    - 얕은 복사와 깊은 복사 .. 명심