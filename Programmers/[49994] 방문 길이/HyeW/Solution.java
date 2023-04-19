import java.util.*;

class Solution {
    static final int MAP_SIZE = 11;
    static int[] dr = {-1, 1, 0, 0}; //0:U, 1:D, R:2, L:3
    static int[] dc = {0, 0, 1, -1};
    
    public int solution(String dirs) {
        int answer = 0;
        
        boolean[][][] map = new boolean[MAP_SIZE][MAP_SIZE][4];
        int curX = 5;
        int curY = 5;
        
        for(int i = 0; i < dirs.length(); i++){
            char order = dirs.charAt(i);
            int dir = 0;
            
            if(order == 'D'){
                dir = 1;
            }else if(order == 'R'){
                dir = 2;
            }else if(order == 'L'){
                dir = 3;
            }
            
            int dx = curX + dr[dir];
            int dy = curY + dc[dir];
            
            if(isMapOut(dx, dy)){
                continue;
            }
            
            if(!map[dx][dy][dir]){
                map[dx][dy][dir] = true;
                // 반대 방향도 방문처리를 해준다.
                map[curX][curY][getOppositeDir(dir)] = true;
                answer++;
            }
            // 이동하기
            curX = dx;
            curY = dy;
        }
        
        return answer;
    }
    
    private static boolean isMapOut(int x, int y){
        return x >= MAP_SIZE || y >= MAP_SIZE || x < 0 || y < 0;
    }
    
    private static int getOppositeDir(int dir){
        if(dir%2 == 0){
            return dir+1;
        }
        return dir-1;
    }
}