# [67259] 경주로 건설

## :pushpin: **Algorithm**

BFS, DP

## :round_pushpin: **Logic**

```java
static int makeRoad(int[][] board){

    Queue<int[]> q = new LinkedList<>();
    
    if(board[0][1] != 1){ // 오른쪽
        q.add(new int[] {0, 1, 0});
        cost[0][1][0] = 100;
    }
    
    if(board[1][0] != 1){ // 아래쪽 
        q.add(new int[] {1, 0, 1});
        cost[1][0][1] = 100;
    }
    
    while(!q.isEmpty()){
        int[] current = q.poll();
        
        for(int d = 0; d < 4; d++){
            int nx = current[0] + dx[d];
            int ny = current[1] + dy[d];
            
            if(!isInRange(nx, ny)){ // 범위 벗어나면 이동 x
                continue;
            }
            
            if(board[nx][ny] == 1){ // 벽일 때 이동 x
                continue;
            }
            
            if(current[2] != d){ // 방향 바꿀 때마다 코너가 생기므로 직선+코너 비용 600 저장 
                if(cost[nx][ny][d] <= cost[current[0]][current[1]][current[2]]+600){ // 이미 저장되어 있는 cost보다 크면 패스 
                    continue;
                }
                cost[nx][ny][d] = cost[current[0]][current[1]][current[2]]+600; // 작으면 cost 갱신 
            }
            
            if(current[2] == d){ // 현재 방향과 같으면 직선이므로 비용 100 저장 
                if(cost[nx][ny][d] <= cost[current[0]][current[1]][current[2]]+100){
                    continue;
                }
                cost[nx][ny][d] = cost[current[0]][current[1]][current[2]]+100;
            }

            q.add(new int[] {nx, ny, d});
        }
    }
    
    return getMin();
}
```
- cost 배열에 각 좌표에 도달할 때까지의 비용의 최소값을 저장한다.
- (0, 0)에서 갈 수 있는 오른쪽 / 아래쪽을 먼저 큐에 넣어주고 시작했다.
- 4방향을 탐색하면서, 방향이 바뀔 때마다 코너가 생기므로 직선+코너 비용인 600을 추가한 값이 이미 저장된 값보다 작으면 갱신해준다. 현재 방향과 같으면 직선이므로 100을 추가한 값과 이미 저장된 값을 비교하여 작은 값을 갱신해준다.
- 모든 경우를 탐색 후 도착지 (n-1, n-1)에 저장된 4방향 중 최소값이 답이 된다.

## :black_nib: **Review**

- 2차원 배열로 풀다가 테스트케이스 몇 개가 안풀려서 결국 답을 봤다. 이런 문제 코테 볼 때 생각 못해낼 거 같다아아악 어렵다
