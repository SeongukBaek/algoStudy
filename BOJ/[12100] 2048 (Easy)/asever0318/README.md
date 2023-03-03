# [12100] 2048 (Easy)

## :pushpin: **Algorithm**

구현, 부르트포스

## :round_pushpin: **Logic**

```java
static void perm(int n, int index) {
		
		if(n == 5) { // 5개 뽑았으면 
			playGame(); // 해당 조합따라서 게임 시작(이동)
			findMax(); // 게임 끝나면 max값 찾기 
			
			copyMatrix(copy, matrix); // 맵 원래대로 돌려놓기 
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			dList[index] = i;
			perm(n+1, index+1);
		}
	}
```
- 4가지 방향으로 만들 수 있는 5개 뽑을 수 있는 모든 경우의 수에 대해서 게임을 실행하고 Max 값을 찾아주었다.
- 한 경우의 수를 끝내면 다음 경우에 대해 게임을 실행할 수 있도록 matrix를 복사해주었다.

```java
static void moveUp() {
		int index;
		int[][] temp = new int[N][N];
		
		for(int i = 0; i < N; i++) { // 모든 열 다 땡겨줄 것 
			for(int j = 0; j < N; j++) {
				
				// 만약 0이 아닌 숫자가 나오면 
				if(matrix[j][i] != 0) {
					index = j; // 그 숫자 인덱스 찍어두고 
					
					for(int k = j+1; k < N; k++) { // 그 다음부터 돌면서 
						
						if(matrix[k][i] != 0) {
							if(matrix[k][i] == matrix[index][i]) { // 만약 같은 숫자가 나오면
								matrix[index][i] = matrix[index][i] * 2; // 찍어둔 자리에 더해서 넣기 
								matrix[k][i] = 0; // 더한 숫자 자리는 0으로 만들기 
								j = k; // 그 뒤부터 다시 탐색 
								break;
							}
							else {
								break;
							}
						}
					}
				}
			}
		}
		
		// 0으로 된 빈공간 없이 숫자 땡겨주기 
		for(int i = 0; i < N; i++) {
			int idx = 0;
					
			for(int j = 0; j < N; j++) {
				// 2차원 배열 하나 더 만들어서 0이 아닌 값이 나올때마다 추가
				if(matrix[j][i] != 0) {
					temp[idx][i] = matrix[j][i];
					idx++;
				}
			}
		}
		copyMatrix(temp, matrix);	
	}
```
- moveUp(), moveDown(), moveLeft(), moveRight() 총 4가지 방향에 대해 적용할 함수를 따로 만들어주엇다.
- 한 열(또는 행)씩 돌면서 0이 아닌 숫자가 나오면 해당 인덱스를 찍어두고 그 다음부터 돌면서 또 0이 아닌 숫자가 나왔을 경우, 만약 같은 숫자라면 두 수를 더한 수를 저장해준 인덱스 위치에 저장하고 만약 다른 숫자라면 해당 인덱스부터 다시 탐색을 한다.
- 위 작업이 끝나면 더해질 수들은 다 더해지고 숫자와 숫자 사이에 남아있을 수도 있는 0을 없애기 위해 temp를 만들어서 0을 제외하고 차례대로 저장해줬다. 

## :black_nib: **Review**
- move 함수들을 작성하면서 for문과 조건문을 쓰다보니까 중간에 꼬여서 문제점을 찾는데 오래 걸렸다. 동작을 잘생각해놓고 코드 작성하도록 노력해야겠다. 문제점을 찾다보니까 디버깅 하는 데 조금 익숙해진 것 같아서 그래도 의미있는 시간이었다.. 
