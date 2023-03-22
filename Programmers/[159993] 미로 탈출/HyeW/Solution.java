import java.util.*;

class Solution {
	Position start, lever, end;
	boolean[][] visited;
	int rowCnt;
	int colCnt;
	char[][] map;
	int[] dr = {-1, 1, 0, 0};
	int[] dc = {0,0,-1,1};
	
    public int solution(String[] maps) {
    	rowCnt = maps.length;
    	colCnt = maps[0].length();
        map = new char[rowCnt][colCnt];
        
    	for(int x = 0; x < rowCnt; x++) {
    		for(int y = 0; y < colCnt; y++) {
    			map[x][y] = maps[x].charAt(y);

    			if(map[x][y] == 'S') {
    				start = new Position(x, y);
    				continue;
    			}
    			if(map[x][y] == 'L') {
    				lever = new Position(x, y);
    				continue;
    			}
    			if(map[x][y] == 'E') {
    				end = new Position(x,y);
    				continue;
    			}
    		}
    	}
    	
    	int minTime = 0;
    	
        //시작점과 레버의 최단경로 구하기
    	int result = getShortestPath(start, lever);
    	if(result == -1) {
    		return -1;
    	}else {
    		minTime += result;
    	}

        //레버와 탈출구 최단경로 구하기
    	result = getShortestPath(lever, end);
    	if(result == -1) {
    		return -1;
    	}else {
    		minTime += result;
    	}
    	
        return minTime;
    }
    
    private int getShortestPath(Position start, Position end) {
    	Queue<Position> path = new ArrayDeque<>();
    	visited = new boolean[rowCnt][colCnt];
    	int cnt = 0;
    	
    	path.add(start);
    	visited[start.x][start.y] = true;
    	
    	while(!path.isEmpty()) {
    		int parentSize = path.size();
    		cnt++;
    		while(parentSize-- > 0) {
    			Position cur = path.poll();
    			
    			for(int i = 0; i < 4; i++) {
    				int dx = cur.x + dr[i];
    				int dy = cur.y + dc[i];
    				
    				if(!arrayBoundsValidation(dx, dy)) {
    					continue;
    				}
    		
    				if(dx == end.x && dy == end.y) {
    					return cnt;
    				}
    				if(map[dx][dy] == 'X' || visited[dx][dy]) {
    					continue;
    				}
    				
    				visited[dx][dy] = true;
    				path.add(new Position(dx, dy));
    			}
    		}	
    	}
    	return -1;
    }
    
    private boolean arrayBoundsValidation(int x, int y) {
		return (x >= 0 && y >= 0 && x < rowCnt && y < colCnt);
	}

	class Position{
    	int x;
    	int y;
    	
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		} 
    }
}