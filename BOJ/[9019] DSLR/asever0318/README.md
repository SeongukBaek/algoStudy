# [9019] DSLR

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
static String calcNum() {
		String cmd = null;
		Queue<Number> queue = new LinkedList<>();
		// 한 숫자에 대해서 한 번에 모든 명령어 다 확인해줄 거이기 때문에 한 숫자는 다시 반복하지 않기 위해서 방문처리
		boolean[] visited = new boolean[10000];
		queue.add(new Number(n1, ""));
		visited[n1] = true;

		while(!queue.isEmpty()) {
			Number current = queue.poll();

			if(current.num == n2) { // n2와 일치하면 끝!
				return current.command;
			}

			for(int i = 0; i < 4; i++) {
				Number next = command(i, current.num);

				if(visited[next.num]) {
					continue;
				}

				queue.add(new Number(next.num, current.command+next.command));
				visited[next.num] = true;
			}

		}

		return cmd;
	}
```

- 최소한의 명령어를 사용해서 n2를 만들어야 하기 때문에 BFS를 통해서 가장 먼저 n2가 되는 명령어를 답으로 반환한다.
- 한 숫자에 대하여 4가지 명령어(DSLR)를 모두 확인해서 큐에 넣어주는 방식으로 모든 경우의 수를 탐색한다. 큐에 넣을 때는 해당 이전 명령어에 이번에 선택한 명령어를 추가해서 넣어준다.
- 위에서 설명한 것처럼 한 숫자에 대해서 모든 경우의 수를 다 확인하기 때문에 다시 방문할 일이 없으므로 visited로 방문처리 해준다.

```java
static int D(int num) { // num * 2, 만약 9999보다 큰 경우에는 10000으로 나눈 나머지
		return (num * 2) % 10000; // 10000미만일 때 10000으로 나눈 나머지는 자기자신이므로 따로 처리할 필요 x
	}

	static int S(int num) { // num - 1, 만약 0이면 9999 반환
		if(num == 0) {
			return 9999;
		}
		return num - 1;
	}

	static int L(int num) { // 왼쪽으로 한 칸 이동
		return ((num % 1000) * 10) + (num / 1000);
	}

	static int R(int num) { // 오른쪽으로 한 칸 이동
		return ((num % 10) * 1000) + (num / 10);
	}
```

- 각 명령어에 따라 숫자를 처리해주는 코드이다.
- 숫자 연산을 통해서 간단하게 구해줄 수 있었다.

## :black_nib: **Review**

- 처음에 char[]로 숫자를 입력받아서 처리하려고 했는데 실패하고 숫자로 처리했다. 훨씬 간단하고 쉬운 방법이었다..
- 최근에 StringBuilder를 주로 사용했다보니 String에 String을 추가할 때 '+'연산자로 가능한 걸 잊고있었다.. 대박..!
