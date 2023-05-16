import java.util.*;

class Solution {
    static char[][] map;
    static int[] start, end;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int r, c;
    static boolean[][] visited;
    
    public static int solution(String[] board) {
    
        r = board.length;
        c = board[0].length();
        map = new char[r][c];
        start = new int[2];
        end = new int[2];
        
        for(int i = 0; i < r; i++){
            map[i] = board[i].toCharArray();
            for(int j = 0; j < c; j++){
                if(map[i][j] == 'R'){ // 시작좌표
                    start[0] = i;
                    start[1] = j;
                }
                
                if(map[i][j] == 'G'){ // 도착좌표
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        
        return moveRobot();
    }
    
    // 로봇이동 함수 - BFS
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
    
    // 이동할 수 있으면 true, 없으면 false 반환 
    static boolean canGo(int nx, int ny){
        
        if(nx < 0 || nx >= r || ny < 0 || ny >= c){
            return false;
        }
        
        if(map[nx][ny] == 'D'){
            return false;
        }
        
        return true;
    }
}