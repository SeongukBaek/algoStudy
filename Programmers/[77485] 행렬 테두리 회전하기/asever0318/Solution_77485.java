import java.util.*;

class Solution {
    
    static int min;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        map = new int[rows][columns];
        init(rows, columns);
        
        for(int i = 0; i < queries.length; i++){
            min = Integer.MAX_VALUE;
            rotate(queries[i]);
            answer[i] = min;
        }
        
        return answer;
    }
    
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
    
    static void init(int rows, int columns){
        int n = 1;
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                map[i][j] = n;
                n++;
            }
        }
    }
}