# [2156] 포도주 시식

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
static int getMax() {
    
    for(int i = 3; i < N+3; i++) {
        // i번째 잔을 마시는 경우
        int sum1 = wine[i] + wineSum[i-2];
        int sum2 = wineSum[i-3] + wine[i-1] + wine[i];
        int max = Math.max(sum1, sum2);
        
        // i번째 잔을 안마시는 경우와 비교하여 더 큰 값 저장
        wineSum[i] = Math.max(max, wineSum[i-1]);
    }
    
    return wineSum[N+2];
}
```
- N+3크기로 각 순서의 포도주를 고려할 때의 최대값을 담는 DP배열을 만든다.
- i번째 포도주를 고려하는 경우는 2가지로 1. i번째 잔을 마실 때, 2. i번째 잔을 마시지 않을 때 2가지로 나눌 수 있다.
- 연속 3개의 잔을 마실 수 없기 때문에 i번째 잔을 마시는 경우는 2가지로 나뉜다.
1. 바로 이전 잔[i-1]을 마시는 경우와 2. 전전 잔[i-2]을 마시는 경우로 sum1과 sum2로 나누어 구해준 후 둘 중 더 큰 값을 max값으로 정한다.
- 마지막으로 i번째 잔을 마시지 않는 경우와 max값 중 더 큰 값을 dp배열에 저장한다.
- 마지막 인덱스에 저장된 값이 구하고자 하는 최대값이 된다.


## :black_nib: **Review**
- DP는 항상 점화식 찾는 게 어렵다