# [77485] 행렬 테두리 회전하기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static void rotate(int[] query){

	int x1 = query[0]-1;
	int y1 = query[1]-1;
	int x2 = query[2]-1;
	int y2 = query[3]-1;

	int temp = map[x1][y1];
	int d = 0;
	int x = x1;
	int y = y1;

	while(true){

		min = Math.min(map[x][y], min);

		int nx = x + dx[d];
		int ny = y + dy[d];

		if(nx < x1 || nx >= x2+1 || ny < y1 || ny >= y2+1){ // 한 면 다 갔으면 방향 바꿔주기
			d++;
			nx = x + dx[d];
			ny = y + dy[d];
		}

		map[x][y] = map[nx][ny];

		x = nx;
		y = ny;

		if(x == x1 && y == y1){ // 출발지로 돌아오면 리턴
			map[x][y+1] = temp; // 시작 요소 넣어주기
			return;
		}
	}
}
```

- x, y에서부터 nx, ny에 있는 요소를 x, y로 옮겨주는 방식으로 배열을 이동했다.
- nx, ny가 (x1, y1) ~ (x2, y2) 경계를 벗어나면 d를 증가시켜 방향을 바꿔서 다시 nx, ny를 구했다.
- nx, ny 요소를 x, y로 바꿔준 후 nx, ny를 x, y 좌표로 바꿔주고, x, y가 출발지(x1, y1)이 될 때까지 반복한다.
- 시계방향으로 회전하기 때문에 출발지의 요소(temp)를 마지막에 x, y+1에 넣어준다.

## :black_nib: **Review**

- 다음엔 다른 방법으로도 풀어봐야겠다.
