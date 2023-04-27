# [11726] 2xn 타일링

## :pushpin: **Algorithm**

DP, 재귀

## :round_pushpin: **Logic**

이 문제는 직사각형의 가로길이가 커질 때마다 채우는 방법 가지수는 피보나치 수열과 같다.

```java
private static int tiling(int n) {
    if(tile[n] != 0) {
        return tile[n];
    }
    return tile[n] = (tiling(n-1) + tiling(n-2))%10007;
}
```
- DP와 재귀함수를 사용하여 피보나치 수열을 구현했다.

## :black_nib: **Review**
- 공간을 2x1 로 하나씩 늘릴 때마다 현재 공간을 1x2 블럭으로 채우면 n-2번째 방법수 만큼의 경우의 수가 나오고, 2x1 블럭으로 채우면 n-1번째 방법수 만큼 나오기 때문에 피보나치 수열을 사용했다.
    - 직접 그려보며 깨달았다.