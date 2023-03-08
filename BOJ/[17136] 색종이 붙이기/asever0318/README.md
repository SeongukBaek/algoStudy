# [17136] 색종이 붙이기

## :pushpin: **Algorithm**

백트래킹, DFS

## :round_pushpin: **Logic**

```java
static void dfsPaper(int x, int y, int count) {

		if(x >= 9 && y >= 10) { // 오른쪽 끝까지 탐색 끝
			// 남은 게 있는 지 확인
			min = Math.min(min, count);
			return;
		}

		if(y >= 10) { // y 끝까지 다 탐색했으면 다음행으로 넘어가기
			x++;
			y = 0;
		}

		if(matrix[x][y] == 1) { // 1을 만나면
			for(int p = 1; p <= 5; p++) { // 색종이 하나씩 넣어보기
				if(checkPaper(x, y, p) && paper[p] > 0) { // 해당 사이즈 색종이 넣을 수 있으면
					paper[p]--; // 종이 하나 쓰고
					attachPaper(x, y, p);
					dfsPaper(x, y+1, count+1);
					paper[p]++; // 갔다오면 다시 돌려주기
					removePaper(x, y, p); // 색종이 다시 떼기
				}
			}
		}else {
			dfsPaper(x, y+1, count);
		}
	}
```

- 왼쪽 위 시작점부터 오른쪽 아래까지 matrix 칸에 대해서 1일 경우에 색종이를 크기별로 넣어보면서 해당 사이즈의 색종이를 넣을 수 있으면 남은 색종이 수를 담은 paper 배열에서 -1하고, 사용한 색종이 수를 +1을 가지고 다음 칸으로 넘어간다.
- matrix 한 행을 다 탐색하면 다음 행으로 넘어가야되기 때문에 따로 처리해주었다.

## :black_nib: **Review**

- 색종이로 1을 다 가리지 못했을 경우에는 -1을 출력해야 하는데 처음에 종료 조건 안에서 검사했다가 값이 제대로 나오지 않았다. 색종이를 다 쓰거나, 크기가 맞지 않아서 1을 다 가릴 수 없는 경우는 dfs를 더 타고 들어가지 못해서 종료조건까지 가지 못하는 거였다. min값이 변화가 없으면 -1를 출력하는 것으로 수정할 수 있었다.
