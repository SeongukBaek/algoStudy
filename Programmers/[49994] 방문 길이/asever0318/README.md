# [49994] 방문 길이

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static int solution(String dirs) {
	int answer = 0;
		map = new boolean[11][11][4];
		command = dirs.toCharArray();
		int[] current = {5, 5}; // 시작점은 5,5

		for(int i = 0; i < command.length; i++) {
				int d = nextDirection(command[i]); // 방향 구하기
				int opp = oppsiteDirection(d); // 반대 방향 구하기
				int[] next = nextPos(d, current[0], current[1]);

				if(next[0] == current[0] && next[1] == current[1]) { // 범위를 벗어나서 제자리에 있는 경우는 카운팅x
					continue;
				}

				if(!map[next[0]][next[1]][d]) {
						map[next[0]][next[1]][d] = true; // 도착지에 표시
						map[current[0]][current[1]][opp] = true; // 반대 방향도 방문 표시 해주기
						answer++;
				}

				current[0] = next[0];
				current[1] = next[1];
		}
		return answer;
}
```

- command(명령어) 끝까지 돌면서 해당 명령어에 대한 이동을 수행한다. 이미 방문한 길은 카운트 하지 않으므로 최초로 이동할 때 map배열에 방문처리를 해준다.
- 다른 칸으로 이동할 때마다 어느 방향에서 왔는지에 따라 이용하는 길이 다르기 때문에 해당 칸에 왔을 때, 들어온 방향(4방향)도 체크하기 위해서 map[11][11][4] 3차원 배열을 활용하였다.
- 이렇게 방향까지 저장해 줄 경우에 이전 칸에서 다음 칸으로 이동하는 길과 다음 칸에서 이전 칸으로 이동하는 길은 같은 길이므로 두 길 다 방문 표시를 해주어야 한다.

## :black_nib: **Review**

- 처음에 단순하게 해당 칸에 방문할 때마다 방문표시 해주고 새로운 길 카운트 했는데, 이전에 어느 방향에서 왔는지에 따라 오는 길이 다른 것을 깨닫게 되었다. 기존 map[][]배열에 4방향 체크해 줄 배열을 추가해서 3차원 배열로 해결할 수 있었다.
