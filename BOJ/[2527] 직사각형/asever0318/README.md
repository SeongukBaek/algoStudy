# [2527] 직사각형

## :pushpin: **Algorithm**

수학, 기하학, 많은 조건 분기

## :round_pushpin: **Logic**

```java
static void checkSquare() {

		// 안겹칠 때
		if (p1 < x2 || q1 < y2 || p2 < x1 || q2 < y1) {
			System.out.println("d");
		} // 점일 때
		else if ((x1 == p2 && y1 == q2) || (x1 == p2 && q1 == y2) || (p1 == x2 && q1 == y2) || (p1 == x2 && y1 == q2)) {
			System.out.println("c");
		} // 선분일 때
		else if (p1 == x2 || q1 == y2|| p2 == x1 || y1 == q2){
			System.out.println("b");
		} // 직사각형으로 겹칠 때
		else {
			System.out.println("a");
		}
	}
```

- 두 개의 사각형이 안겹칠 때, 점일 때, 선분일 때, 직사각형으로 겹칠 때 총 4가지 경우로 나누어서 좌표 비교를 통해 문제를 풀었다.

## :black_nib: **Review**

- 좌표 비교하는 게 생각보다 어려워서 오래걸렸다.. 다음엔 다른 방법으로 풀어야지..
