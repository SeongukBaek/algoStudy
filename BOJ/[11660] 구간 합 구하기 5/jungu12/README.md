# [11660] 구간 합 구하기 5

## :pushpin: **Algorithm**

누적 합

## :round_pushpin: **Logic**

```java
	for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j - 1] + map[i - 1][j] - map[i - 1][j - 1];
			}
  ```
   - 해당 좌표까지의 누적 합을 배열에 저장한 후
```java
	System.out.println(map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1]);
  ```
	- 필요한 부분 만큼의 누적 합을 출력한다.
  
  
## :black_nib: **Review**
 - input size를 고려하지 않고 풀어 시간을 많이 썼다.



  
  	

  
