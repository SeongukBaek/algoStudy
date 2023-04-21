
class Solution {
    static boolean[][][] map;
    static char[] command;
    static int[] dx = {-1, 0, 1, 0}; // U, R, D, L
    static int[] dy = {0, 1, 0, -1};
    
    static int solution(String dirs) {
    	int answer = 0;
        map = new boolean[11][11][4];
        command = dirs.toCharArray();
        int[] current = {5, 5}; // 시작점은 5,5
        
        for(int i = 0; i < command.length; i++) {
            int d = nextDirection(command[i]); // 방향 구하기
            int opp = oppsiteDirection(d); // 반대 방향 구하기 
            int[] next = nextPos(d, current[0], current[1]);
            
            if(next[0] == current[0] && next[1] == current[1]) { // 범위를 벗어나서 제자리에 있는 경우는 카운팅x
            	continue;
            }
            
            if(!map[next[0]][next[1]][d]) {
                map[next[0]][next[1]][d] = true; // 도착지에 표시 
                map[current[0]][current[1]][opp] = true; // 반대 방향도 방문 표시 해주기 
                answer++;
            }
            
            current[0] = next[0];
            current[1] = next[1];
        }
        return answer;
    }
    
    
    static int oppsiteDirection(int d) {
    	if(d == 0) {
    		return 2;
    	}else if(d == 1) {
    		return 3;
    	}else if(d == 2) {
    		return 0;
    	}else {
    		return 1;
    	}
    }
    
    static int nextDirection(char c) {
        
        if(c == 'U') {
            return 0;
        }
        
        else if(c == 'L') {
            return 3;
        }
        
        else if(c == 'D') {
            return 2;
        }
        
        else {
            return 1;
        }
    }
    
    // 다음 위치 알려주기 : 4방향 or 그대로
    static int[] nextPos(int d, int x, int y) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        
        // 경계 넘어가면 그대로 
        if(nx < 0 || nx >= 11 | ny < 0 || ny >= 11) {
            nx = x;
            ny = y;
        }
        
        
        return new int[] {nx, ny};
    }
}