# [12913] 땅따먹기

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for(int cur = 1; cur < land.length; cur++){
    for(int i = 0; i < COL; i++){ //현재
        for(int j = 0; j < COL; j++){ //이전 값
            // 같은 열을 연속해서 밟을 수 없다.
            if(i==j){
                continue;
            }
            maxValue[cur][i] = Math.max(maxValue[cur][i], land[cur][i]+maxValue[cur-1][j]);
        }
    }
}
```
이전 행의 값을 보며 각 좌표에서 가질 수 있는 최대값을 구한다.<br/>
같은 열을 연속해서 밟을 수 없으니 그 부분만 조건을 걸어 처리해주었다.

## :black_nib: **Review**

- 간단한 DP문제였다. 백준에서 푼 정수 삼각형하고 같은 문제였다.