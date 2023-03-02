# [12100] 2048 (Easy)

## :pushpin: **Algorithm**

구현, 큐, 중복조합

## :round_pushpin: **Logic**

중복조합을 이용해 5번 움직였을때 나올 수 있는 모드 경우의 수를 확인해본다.

```java
		if (depth == 5) {
			temp = copyArray(map);
			for (int c : cmd) {
				runCommand(c);
			}
			// 배열의 최대값 찾기
			result = Math.max(result, findMaxValue());
			return;
		}
```

- cmd배열에 이동명령을 저장해두고 5개가 되었을때 2048게임을 시작한 후 끝나고 최대값을 비교한다.

```java
public static void moveUp() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			int[] next = new int[n];
			int idx = 0;
			for (int j = 0; j < n; j++) {
				if (temp[j][i] == 0) {
					continue;
				}
				// 큐가 빈 경우
				if (queue.isEmpty()) {
					queue.add(temp[j][i]);
					continue;
				}
				// 큐에 값이 있고 그 값이 새로들어온 값과 같은 경우
				if (queue.peek() == temp[j][i]) {
					next[idx++] = queue.poll() * 2;
				} else {
					next[idx++] = queue.poll();
					queue.add(temp[j][i]);
				}
			}
			if (!queue.isEmpty()) {
				next[idx++] = queue.poll();
			}

			for (int j = 0; j < n; j++) {
				temp[j][i] = next[j];
			}
		}
	}

```

- 큐를 이용해 한줄씩 값을 비교한다.
- 같은 값이 나오는 경우 큐에 값을 넣지않고 기존의 값을 2배로 하여 내보낸다.

## :black_nib: **Review**

- 처음에 쉽게 할 수 있는 방법이 있지 않을까 많이 고민해봤지만 떠오르지 않아서 단순히 모든 경우의 수를 다 확인해보았더니 풀렸다.
- 시간이 생각보다 오래 걸리는데 어느 부분에서 오래 걸리는지 궁금하다.
