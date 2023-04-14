# [1932] 정수 삼각형

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
// 아래층으로 합 누적
for (int j = 1; j <= i; j++) {
  temp[j] = curLine[j] + Math.max(prevLine[j - 1], prevLine[j]);
}
prevLine = temp;
```

- 이전 행의 정보를 이용하여 큰 값을 아래로 누적시키며 마지막 줄의 숫자 중 가장 큰 수를 출력한다.

## :black_nib: **Review**

- 한번 풀어봤던 DP문제여서 비교적 쉽게 풀 수 있었다.
