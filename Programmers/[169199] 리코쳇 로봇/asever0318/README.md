# [169199] 리코쳇 로봇

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
static int moveRobot(){
    
    Queue<int[]> q = new LinkedList<>();
    visited = new boolean[r][c];
    int cnt = 0;
    
    q.add(start);
    visited[start[0]][start[1]] = true;
    
    while(!q.isEmpty()){
        
        int size = q.size();
        
        for(int i = 0; i < size; i++){
            int[] current = q.poll();
            
            if(current[0] == end[0] && current[1] == end[1]){ // 도착좌표에 도착하면 멈춤 
                return cnt;
            }
            
            for(int d = 0; d < 4; d++){ // 4방향
                int x = current[0];
                int y = current[1];
                
                while(true){ // 벽이나 장애물을 만날 때까지 
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    if(!canGo(nx, ny)){ // 다음 칸으로 이동할 수 없으면 멈춤 
                        if(!visited[x][y]){ // 이미 방문한 좌표는 또 방문x
                            q.add(new int[] {x, y}); // 이전 좌표를 큐에 넣기 
                            visited[x][y] = true;
                        }
                        break;
                    }
                    
                    x = nx;
                    y = ny;
                }
            }
        }
        cnt++;
    }
    return -1; // 도착할 수 없으면 -1 반환 
}
```
- BFS를 통해서 로봇을 4방향으로 이동시키는데 벽이나 장애물을 만날때까지 이동시킨다. 
- 만약 다음 칸으로 이동할 수 없으면 멈추고, 해당 좌표 바로 이전 좌표를 큐에 넣는다. 
- 몇 번 이동했는지 구하기 위해서 q의 size를 이용했고, 도착좌표에 도착하면 cnt를 리턴한다.

## :black_nib: **Review**
- BFS 문제 재미있었다~!