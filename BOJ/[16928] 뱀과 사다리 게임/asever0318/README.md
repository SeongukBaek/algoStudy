# [16928] 뱀과 사다리 게임

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
move = new int[101]; // 0보다 큰 값이면 어디론가 이동함
```

- 1부터 100까지 각 칸에서 뱀 or 사다리로 연결되어 이동하여 도착할 숫자를 저장한다.

```java
static int playGame() {
		int cnt = 0;
		int num = 1;
		boolean[] visited = new boolean[101];
		Queue<Integer> q = new LinkedList<>();
		q.add(num);

		while(!q.isEmpty()) {

			// 주사위 1번 던질 때마다 카운트하기 위해서 이전큐 사이즈만큼 돌기
			int size = q.size();

			for(int n = 0; n < size; n++) {
				int c = q.poll();

				for(int i = 1; i <= 6; i++) {
					int nextNum = c + i; // 주사위 눈 크기만큼 이동

					if(nextNum == 100) { // 100에 도착하면 끝
						return cnt+1; // 현재 시간 카운트 되지 않았으므로 +1 해서 리턴
					}

					if(nextNum > 100) { // 다음에 갈 숫자가 100보다 크면 패스
						continue;
					}

					if(move[nextNum] != 0) { // 0이 아니면 어디로든 이동
						nextNum = move[nextNum];
					}

					if(visited[nextNum]) { // 방문했으면 패스
						continue;
					}

					q.add(nextNum);
					visited[nextNum] = true;
				}
			}
			cnt++;
		}

		return cnt;
	}
```

- 주사위를 한 번 던질때마다 1-6까지 나오는 모든 경우의 수에 따라서 이동한다.
- 시작점에서 출발하여 다음 숫자(현재 숫자 + 주사위 수)를 방문하면서 100에 도착할 때까지 진행한다.

## :black_nib: **Review**

- 뱀과 사다리를 구분해주지는 않았고 0보다 큰 값이면 무조건 이동하는 것으로 풀었다.
- 처음에 방문처리를 잘못해서 앞 번호로 돌아가는 경우(뱀) 이미 방문처리 되어 가지 못해 틀렸다.
