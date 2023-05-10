# [131130] 혼자 놀기의 달인

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
public int solution(int[] cards) {

	// 원소 전체를 각각 시작 박스로 탐색
	for(int i = 0; i < cards.length; i++){ // 첫 번째 상자
		visited = new boolean[cards.length];

		answer = 0;
		openBox(i, 0, cards);
		first = answer;

		for(int j = 0; j < cards.length; j++){ // 두 번째 상자
			if(visited[j]){ // 첫 번째에서 방문한 상자면 패스
				continue;
			}

			answer = 0;
			openBox(j, 0, cards);
			second = answer;

			max = Math.max(first*second, max);
		}
	}
	return max;
}
```

- 모든 상자를 시작 상자로 해서 모든 경우를 탐색하기 위해 for문을 통해 cards.length까지 방문하고, 2번째까지 진행해야되기 때문에 i번째 상자에서 openBox 진행 후 방문하지 않은 상자에 대해서 openBox를 한 번 더 진행한다.
- 첫 번째에 연 상자와 두 번째에 연 상자의 개수를 곱한 것이 답이기 때문에 first, second에 각 개수를 저장해준 후 max값을 갱신할 때 곱해서 비교했다.

```java
static void openBox(int box, int cnt, int[] cards){

	if(visited[box]){ // 이미 열어본 박스이면 멈춤
		answer = Math.max(answer, cnt);
		return;
	}

	visited[box] = true;
	openBox(cards[box]-1, cnt+1, cards);
}
```

- DFS를 통해서 이미 열어본 상자를 만날 때까지 cnt를 올리면서 상자에 적힌 번호를 다음 상자로 방문했다.

## :black_nib: **Review**

- 상자를 두 번 열어야해서 어떻게 해야될 지 고민을 많이 했는데 그냥 for문을 두 번 돌려서 모든 경우를 확인하는 걸로 했다.
