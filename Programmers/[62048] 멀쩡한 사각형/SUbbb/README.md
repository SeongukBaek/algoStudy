# [62048] 멀쩡한 사각형

## :pushpin: **Algorithm**

유클리드 호제법

## :round_pushpin: **Logic**

```java
private static long computeGdc(long w, long h) {
    if (w < h) {
        long swapTemp = w;
        w = h;
        h = swapTemp;
    }

    while (h != 0) {
        long divider = w % h;
        w = h;
        h = divider;
    }

    return w;
}
```

- 유클리드 호제법으로 최대 공약수를 구해 반환한다.

```java
private static long computeUnavailableSquare(long w, long h) {
    if (w == 1 || h == 1) {
        return Math.max(w, h);
    }

    if (w == h) {
        return w;
    }

    return w + h - 1;
}
```

- 더 이상 나눠지지 않는 w, h를 크기로 하는 직사각형은 위와 같은 규칙이 있다.
- 둘 중 하나라도 1이라면 사용할 수 없는 직사각형의 개수는 둘 중 큰 값이다.
- 두 값이 같다면 그 값이 사용할 수 없는 직사각형의 개수이다.
- 그렇지 않다면 w + h - 1이 사용할 수 없는 직사각형의 개수이다.

## :black_nib: **Review**

- 주어진 입력이 1억 이하의 자연수이므로, DP를 사용한다면 시간초과가 발생할 것 같았다.
- 따라서 그림을 보다보니, 주어진 입력을 줄여서 직사각형을 구할 수 있을 것 같아 여러 가지 경우에 대해 그림을 그려보았다.
- 더 이상 나누어지지 않을 때까지 w, h를 나누고 남은 w, h에 대해 사용할 수 없는 직사각형의 넓이를 구하는 공식을 만들어서 해결했다.
- 이런 문제에 대한 아이디어를 좀만 더 빨리 찾을 수 있어야겠다.
