# [17144] 미세먼지 안녕!

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

문제에서 요구하는 동작은 2가지이다.<br/>
1. 미세먼지 확산하기
2. 공기청정기 작동하기

```java
while(T-- > 0) {
    spreadDusts();
    runAirCondition();
}
```

```java
private static void spreadDusts()
```
모든 배열을 확인하면서 0이상이면 4방탐색을 통해 미세먼지를 확산시킨다.<br/>

```java
private static void runAirCondition()
```
인덱스로 접근하여 미세먼지를 하나하나 이동시켜준다.

## :black_nib: **Review**

- 코드트리에서 푼 문제보다 너무 간단한 구현 문제였다.
- 그런데 공기청정기를 가동시킬 때 인덱스 범위를 잘못 설정해서 살짝 시간이 걸렸다.