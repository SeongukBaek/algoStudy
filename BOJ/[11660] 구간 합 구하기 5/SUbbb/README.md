# [11660] 구간 합 구하기 5

## :pushpin: **Algorithm**

DP, 누적 합

## :round_pushpin: **Logic**

```java
for (int x = 1; x <= N; x++) {
    st = new StringTokenizer(br.readLine());
    for (int y = 1; y <= N; y++) {
        map[x][y] = Integer.parseInt(st.nextToken()) + map[x][y - 1] + map[x - 1][y] - map[x - 1][y - 1];
    }
}
```

- 입력과 동시에, 해당 좌표를 포함하는 사각형 내의 숫자의 합을 계산한다.

```java
private static int getArea(int x1, int y1, int x2, int y2) {
    return map[x2][y2] - map[x1 - 1][y2] - map[x2][y1 - 1] + map[x1 - 1][y1 - 1];
}
```

- 누적 합 계산된 2차원 배열에서 좌표에 해당하는 넓이를 덧셈, 뺄셈하여 구한다.

## :black_nib: **Review**
- 누적 합을 이용하는 문제임은 바로 파악할 수 있었다. 가로, 세로 누적 합을 좀 더 빠르게 할 방법 고민에 시간을 많이 쓴 것 같다.