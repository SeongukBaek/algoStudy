# [2931] 가스관 

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
static void dfs(int x, int y, int d, char c) {
      
	   // 답을 찾았으면 더 안봐도 됨 
	   if(findResult == true) {
		   return;
	   }
	   
	  if(x == Z[0] && y == Z[1]) { // 목적지에 도착하고 
		  if(pCheck == true) { // 파이프 하나를 사용했으면 
			  System.out.println((item[0]+1)+" "+(item[1]+1)+" "+selected);
			  findResult = true; // 답을 찾았으면 true 표시
			  return;
		  }
	  }
		  
      // 파이프 모양과 현재 방향에 따라 다음 방향 설정 
      int nd = direction(d, c);
      
      if(nd == -1) { // 빈 공간이면 return
    	  return;
      }
      
      int nx = x + dx[nd]; 
      int ny = y + dy[nd];
      
      if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
         return;
      }
      
      if(pipeMap[nx][ny] == '.' && pCheck == false) { // 만약 도착한 방향이 빈 공간이면, 파이프 아직 안썼으면
         for(int i = 0; i < 7; i++) { // 7개 파이프 넣어보기 
        	 pCheck = true; // 파이프 사용 표시하고 
        	 selected = pShape[i]; // 선택한 파이프 
        	 item[0] = nx; // 파이프 넣는 곳 좌표 
        	 item[1] = ny;
        	 
        	 visited[nx][ny] = true;
        	 pipeMap[nx][ny] = selected;
        	 dfs(nx, ny, nd, pShape[i]);
        	 visited[nx][ny] = false;
        	 pipeMap[nx][ny] = '.';
        	 pCheck = false; // 빠져나오면 다시 사용 x로 표시 
         }
      }
      else { // 빈 공간이 아니면
         if(!visited[nx][ny]) { // 방문하지 않은 곳이면 
            visited[nx][ny] = true;
            dfs(nx, ny, nd, pipeMap[nx][ny]);
            visited[nx][ny] = false;
         }
         else { // 방문 한 곳이면 ('+' 모양 때문에 방문한 곳 또 방문할 수 있음)
        	 dfs(nx, ny, nd, pipeMap[nx][ny]); // 그냥 지나가도 됨 
         }
      }
   }

```
- 문제의 해는 하나이기 때문에 해를 찾으면 findResult를 true로 바꿔주고 true일 경우 더 이상 불필요한 탐색을 안하도록 기저조건을 두었다.
-  파이프는 1개만 새로 넣을 수 있기 때문에 pCheck 변수를 통해 사용했는지 아닌지 확인하고 pCheck == false이고 '.'을 만날 경우 파이프 모양을 하나씩 넣어보면서 탐색하였다.
- '+' 모양은 두 방향으로 움직일 수 있기 때문에 방문했더라도 또 방문하는 경우가 생기므로 두 경우를 따로 처리해주었다.

## :black_nib: **Review**
- 처음에 코드짤 때 맵에 직접 선택한 파이프를 저장하지 않고 값만 따로 저장해두고 넘어갔는데, 이때 '+' 모양의 경우 두 방향으로 갈 수 있기 때문에 다른 길을 통해서 다시 오게 되면 맵에는 해당 파이프가 아닌 '.' 그대로 저장되어 있어서 경로를 찾을 수 없는 문제가 생겼다. 맵에 직접 저장해주는 것으로 해결 할 수 있었다. 
