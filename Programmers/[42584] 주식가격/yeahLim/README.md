# [42584] 주식가격

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
for (int i = 0; i < prices.length; i++) {
    for (int j = i+1; j < prices.length; j++) {
        result[i]++;
        if (prices[i] > prices[j]) {
            break;
        }
    }
}        
```

- 현재 가격보다 더 떨어질 때까지 시간 +1을 해준다.

## :black_nib: **Review**

- 처음에 스택 문제인 것을 파악했으나, 스택으로 풀려니 더 복잡해서 그냥 구현으로 풀었다!