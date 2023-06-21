# [42584] 주식가격

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
for (int current = 0; current < N; current++) {
    answer[current] = 0;
    
    for (int next = current + 1; next < N; next++) {
        // 다음 초가 있으면 일단 최소 1초동안은 가격이 떨어지지 않은 것
        answer[current]++;
        
        // 가격이 떨어지면 종료
        if (prices[next] < prices[current]) {
            break;
        }
    }
}
```

- 현재 가격보다 다음 주식가격이 더 크면 시간 증가를 멈춘다.

## :black_nib: **Review**

- 알고리즘 분류가 스택/큐로 되어있어서.. 자료구조를 활용하려 했는데, 깔끔한 방법이 떠오르지 않았다.
- 단순 구현으로도 가능할 것 같아서 구현했다!