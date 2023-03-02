# [2931] 가스관

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

1. 먼저 맵을 순서대로 돌아보면서 가스관이 연결되어 있지않은 위치를 찾는다.
2. 그 위치에서 사방탐색을 통해 가능한 파이프 모양을 찾는다.

```java
checkBlock();

public static void checkBlock() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// '-'블록의 경우 왼쪽, 오른쪽이 연결되어 있어야함
				if (map[i][j] == '-') {
					if (j - 1 >= 0 && map[i][j - 1] == '.') {
						target = new Block(i, j - 1);
						return;
					}
					if (j + 1 < m && map[i][j + 1] == '.') {
						target = new Block(i, j + 1);
						return;
					}
				}

        // 내용 생략

        // 4번 블록의 경우 아래쪽, 왼쪽이 연결되어 있어야함
				if (map[i][j] == '4') {
					if (i + 1 < n && map[i + 1][j] == '.') {
						target = new Block(i + 1, j);
						return;
					}
					if (j - 1 >= 0 && map[i][j - 1] == '.') {
						target = new Block(i, j - 1);
						return;
					}
				}
			}
		}
```

- 맵을 하나씩 돌아보면서 파이프가 나왔을때 그 파이프의 상하좌우를 살펴 연결되지 않은 곳이 없나 확인해보고 빈공간을 찾음.

```java
findBlock();
	public static void findBlock() {
		int cx = target.x;
		int cy = target.y;

		if (chechBlock1(cx, cy)) {
			System.out.println('|');
			return;
		}
		if (chechBlock2(cx, cy)) {
			System.out.println('-');
			return;
		}
		if (chechBlock3(cx, cy)) {
			System.out.println('+');
			return;
		}
		if (chechBlock4(cx, cy)) {
			System.out.println('1');
			return;
		}
		if (chechBlock5(cx, cy)) {
			System.out.println('2');
			return;
		}
		if (chechBlock6(cx, cy)) {
			System.out.println('3');
			return;
		}
		if (chechBlock7(cx, cy)) {
			System.out.println('4');
		}
	}
```

- 찾은 빈 공간에서 블록의 모양을 찾는다.

## :black_nib: **Review**

- 생각한대로 하다보니 문제가 쉽게 해결되기는 했지만 코드가 너무 길어졌다.
- 중복되는 부분이 너무 많은 것 같다. 다음에 이런 문제를 푼다면 미리 증복되는 로직들을 어떻게 처리할지 생각해보고 나서 문제를 풀어야겠다.
