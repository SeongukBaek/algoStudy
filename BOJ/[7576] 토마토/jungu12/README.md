# [7576] 토마토

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
// 정해진 배열의 크기를 넘는지 check
if (nx >= 0 && nx < warehouse[0].length && ny >= 0 && ny < warehouse.length) {
	//토마토가 이미 익었는지 확인
	if (warehouse[ny][nx] == 0) {
		queue.add(new int[] { ny, nx });
		warehouse[ny][nx] = warehouse[y][x] + 1;
		if (warehouse[ny][nx] > result) {
			result = warehouse[ny][nx];
		}
	}
}
  ```
   - 토마토가 며칠차에 익었는지 배열에 저장하여 result를 출력하였다.
   - 입력 받은 초기 배열에 익은 토마토가 1로 저장 되어 있으므로 result -1 한 값을 출력한다.
  
  
## :black_nib: **Review**
 - 토마토가 모두 익을 때까지의 최소 날짜를 출력하는 방법에 대한 고민을 해본 문제였다.



  
  	

  
