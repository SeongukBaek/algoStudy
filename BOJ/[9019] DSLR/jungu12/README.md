# [9019] DSLR

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private static void findAnswer(int A, int B) {
	queue.add(A);
	visited[A] = true;
	command[A] = "";
	
	while (!visited[B]) {
		int cur = queue.poll();

		int D = cur * 2 % 10000;
		int S = cur == 0 ? 9999 : cur - 1;
		int L = cur % 1000 * 10 + cur / 1000;
		int R = cur % 10 * 1000 + cur / 10;

		if (!visited[D]) {
			queue.add(D);
			visited[D] = true;
			command[D] = command[cur] + "D";
		}

		if (!visited[S]) {
			queue.add(S);
			visited[S] = true;
			command[S] = command[cur] + "S";
		}

		if (!visited[L]) {
			queue.add(L);
			visited[L] = true;
			command[L] = command[cur] + "L";
		}

		if (!visited[R]) {
			queue.add(R);
			visited[R] = true;
			command[R] = command[cur] + "R";
		}
	}
}
```
- 커맨드에 맞게 값을 바꾸어주고 원하는 값이 나올 때 까지 BFS

## :black_nib: **Review**
- 커맨드 마다 숫자를 야무지게 바꾸어 주는 식을 생각해 내는 것이 중요 !
