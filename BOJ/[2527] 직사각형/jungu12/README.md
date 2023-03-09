# [2527] 직사각형

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
private static void findOverlapedShape(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
	if (x2 < x3 || y2 < y3 || x4 < x1 || y4 < y1) {
		System.out.println("d");
		return;
	}
	if ((x2 == x3 && y2 == y3) || (x2 == x3 && y1 == y4) || (x1 == x4 && y2 == y3) || (x1 == x4 && y1 == y4)) {
		System.out.println("c");
		return;
	}
	if (x2 == x3 || y2 == y3 || x1 == x4 || y1 == y4) {
		System.out.println("b");
		return;
	}
	System.out.println("a");

}
```

- 좌표로 비교하는 방식으로 구현하였다.
- 선분일 때, x좌표가 같으면 y좌표가 달라야하고, y좌표가 같다면 x좌표는 달라야하는 조건을 달아야하나,
  점일 때를 조기 반환 해주었음으로 조건을 생략할 수 있다.

## :black_nib: **Review**

- 좌표로 푸니까 실수하면 찾기 너무 힘들었다..
- 그래도 다음에도 좌표로 풀 것 같다.
