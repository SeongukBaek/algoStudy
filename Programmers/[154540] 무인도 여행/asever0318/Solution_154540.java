import java.util.*;

class Solution {
    static int[][] map;
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    static class Pos{
        int x, y;
        
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        List<Integer> island = new ArrayList<>();
        
        for(int i = 0; i < N; i++){
            char[] st = maps[i].toCharArray();
            for(int j = 0; j < M; j++){
                if(st[j] == 'X'){
                    map[i][j] = 0;
                    continue;
                }
                
                map[i][j] = Character.getNumericValue(st[j]);
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0){ // 0이면 패스
                    continue;
                }
                
                island.add(countDays(i, j));
            }
        }
        
        if(island.size() == 0){
            return new int[] {-1};
        }
        
        Collections.sort(island);
        
        int[] answer = new int[island.size()];
        for(int i = 0; i < island.size(); i++){
            answer[i] = island.get(i);
        }
        
        return answer;
    }
    
    // 머무를 수 있는 날짜 반환 - BFS
    static int countDays(int x, int y){
        Queue<Pos> q = new LinkedList<>();
        int count = map[x][y];
        map[x][y] = 0;
        q.add(new Pos(x, y));
        
        while(!q.isEmpty()){
            Pos current = q.poll();
            
            for(int d = 0; d < 4; d++){
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M){
                    continue;
                }
                
                if(map[nx][ny] == 0){
                    continue;
                }
                
                count += map[nx][ny];
                map[nx][ny] = 0;
                q.add(new Pos(nx, ny));
            }
        }
        
        return count;
    }
}