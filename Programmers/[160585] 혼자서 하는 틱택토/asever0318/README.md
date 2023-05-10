# [160585] 혼자서 하는 틱택토

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static char[][] map;
static int o, x;

public int solution(String[] board) {
	...

	countChar(); // 문자 카운트

	if(x > o || (o - x) > 1){ // x의 개수가 더 많거나 둘의 개수가 2개 이상 차이나는 경우는 일어날 수 없음
		return 0;
	}

	// 둘 다 빙고인 경우는 일어날 수 없음
	if(checkBingo('O') && checkBingo('X')){
		return 0;
	}

	// ----- 둘 중 한 명이 이기는 상황 -----
	// 1. O가 이겼으면 O가 한 개 많아야 함
	if(checkBingo('O')){
		if(o != (x+1)){
			return 0;
		}
	}

	// 2. X가 이겼으면 수가 같아야 함
	if(checkBingo('X')){
		if(o != x){
			return 0;
		}
	}

	return 1;
}
```

- 먼저 O의 개수와 X의 개수를 카운트 한다.
- X의 개수가 더 많거나 둘의 개수가 2개 이상 차이나는 경우는 일어 날 수 없으므로 O을 반환한다.
- 둘 다 빙고인 경우도 일어날 수 없으므로 0을 리턴한다.
- 위 상황들을 제외하면 둘 중 한 명이 이기는 상황이 남는데, 이때 O가 이겼으면 O가 한 개 많아야 하고 X가 이겼으면 수가 같아야 한다. (빙고이면 이기는 경우)

```java
static boolean checkBingo(char c){
	boolean check = false;

	// 가로
	for(int i = 0; i < 3; i++){
		if(c == map[i][0] && map[i][0] == map[i][1] && map[i][1] == map[i][2]){
			check = true;
		}
	}

	// 세로
	for(int i = 0; i < 3; i++){
		if(c == map[0][i] && map[0][i] == map[1][i] && map[1][i] == map[2][i]){
			check = true;
		}
	}

	// 대각선(2가지 경우)
	if(c == map[0][0] && map[0][0] == map[1][1] && map[1][1] == map[2][2]){
		check = true;
	}

	if(c == map[0][2] && map[0][2] == map[1][1] && map[1][1] == map[2][0]){
		check = true;
	}

	return check;
}
```

- 빙고인지 확인하는 함수
- 가로 / 세로 / 대각선의 경우로 나누어서 단순하게 문자를 비교하면서 검사했다.

## :black_nib: **Review**

- 일어날 수 없는 경우를 나누어서 생각하는 게 힘들었다. 처음에는 단순하게 맵에 빙고가 있는 지만 검사했는데 경우를 다 찾을 수가 없어서 답을 참고했다. 누가 이기는 지에 따라 경우를 나누는 것을 배웠다~
