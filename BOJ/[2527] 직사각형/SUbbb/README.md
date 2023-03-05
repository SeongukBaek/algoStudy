# [2527] 직사각형

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
private static void computeCoverCount(int x, int y, int p, int q) {
    for (int index = x; index <= p; index++) {
        if (horizontalCover[index]) {
            horizontalCount++;
        }
    }
    for (int index = y; index <= q; index++) {
        if (verticalCover[index]) {
            verticalCount++;
        }
    }
}
```

- 첫번째 직사각형의 정보로 가로, 세로 배열에 방문 표시가 완료되었다.
- 두번째 직사각형의 정보로 첫번째 직사각형과 겹치는 부분의 카운트를 계산한다.

```java
private static char determineCode() {
    if (horizontalCount == 0 || verticalCount == 0) {
        return 'd';
    }

    if (horizontalCount == 1 && verticalCount == 1) {
        return 'c';
    }

    if (horizontalCount == 1 || verticalCount == 1) {
        return 'b';
    }
    return 'a';
}
```

- 가로든 세로든 겹치는 부분이 없다면, d
- 가로 세로가 1개씩만 겹친다면 c
- 가로나 세로 중 한 쪽만 1개 겹친다면 b
- 이외의 경우는 a

## :black_nib: **Review**
- 배열을 사용해서 두 직사각형이 겹치거나 하는 것을 판단하는 로직을 세워보고 싶었다.
- 단순히 좌표에 대한 비교만으로 풀릴 수 있겠지만, 이 방식이 금방 더 이해될 것 같았다.