# [1932] 정수 삼각형

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
// 꼭지점 초기화
maxCost.get(0)[0] = triangle.get(0)[0];

for (int i = 1; i < N; i++) {
    for (int j = 0; j <= i; j++) {
        //왼쪽 변일 경우
        if(j == 0) {
            maxCost.get(i)[j] = maxCost.get(i-1)[j]+triangle.get(i)[j];
            continue;
        }
        //오른쪽 변일 경우
        if(j == i) {
            maxCost.get(i)[j] = maxCost.get(i-1)[j-1]+triangle.get(i)[j];
            continue;
        }
        maxCost.get(i)[j] = Math.max(maxCost.get(i-1)[j],  maxCost.get(i-1)[j-1])
                +triangle.get(i)[j];
    }
}
```

현재 지점에서 바로 윗 줄을 확인한다.<br/>
내가 선택할 수 있는 두가지 수 중 큰 값을 내가 취하면 된다.<br/>
이때 `maxCost`란 현재 지점을 포함한 최대 값을 저정한 DP배열을 이용하여 구한다.

## :black_nib: **Review**
- 이정도 DP는 무리 없이 풀 수 있다!