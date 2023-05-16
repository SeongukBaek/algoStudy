import java.util.*;

class Solution {
    
    static int[][][] cost; // x, y, d - 우, 하, 좌, 상 
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n, result;
    
    public int solution(int[][] board) {
        n = board.length;
        cost = new int[n][n][4];
        initCost();
        
        return makeRoad(board);
    }
    
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
    
    // 도착지점 4방향 중 가장 작은 값이 정답 
    static int getMin(){
        int min = cost[n-1][n-1][0];
        
        for(int i = 1; i < 4; i++){
            min = Math.min(min, cost[n-1][n-1][i]);
        }
        
        return min;
    }
    
    // 최소값을 저장해줄 것이기 때문에 큰 값으로 초기화 
    static void initCost(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < 4; k++){
                    cost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    }
    
    static boolean isInRange(int nx, int ny){
        if(nx < 0 || nx >= n || ny < 0 || ny >= n){
            return false;
        }
        return true;
    }
}