# [2527] 직사각형

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
// 공통 부분이 없는 경우 (첫번째 사각형의 y2보다 두번째 사각형의 y1이 크거나 첫번째 사각형의 x)
if (squre[0][2].y < squre[1][0].y || squre[0][0].y > squre[1][2].y || squre[0][0].x > squre[1][1].x
        || squre[0][1].x < squre[1][0].x) {
    System.out.println("d");
    continue;
}
// 점이 겹치는 경우
if ((squre[0][0].x == squre[1][2].x && squre[0][0].y == squre[1][2].y)
        || (squre[0][1].x == squre[1][3].x && squre[0][1].y == squre[1][3].y)
        || (squre[0][2].x == squre[1][0].x && squre[0][2].y == squre[1][0].y)
        || (squre[0][3].x == squre[1][1].x && squre[0][3].y == squre[1][1].y)) {
    System.out.println("c");
    continue;
}

// 모서리가 만나는 경우 (위아래가 만나는 경우 or 좌우가 만나는 경우)
if (squre[0][0].x == squre[1][1].x || squre[0][1].x == squre[1][0].x || squre[0][0].y == squre[1][2].y
        || squre[0][2].y == squre[1][0].y) {
    System.out.println("b");
    continue;
}
System.out.println("a");
```

- 순서대로 공통 부분이 없는 경우, 점이 겹치는 경우, 모서리가 만나는 경우 순으로 확인을 해보고 모두 아닌 경우 "a"를 출력한다.
- 각각의 경우를 다 생각해보고 그대로 구현하였다.

## :black_nib: **Review**

- 객체의 비교가 생각처럼 되지 않아 시간이 좀 걸렸다.
- 이런 문제는 다양한 테스트 케이스를 잘 확인해봐야할 것 같다.
