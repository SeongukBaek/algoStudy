import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static int r, c;
    static char[][] map;
    static int[] moscow; // 모스크바 좌표
    static boolean[][] visited;
    // 방향 백터 : 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        
    	/* 입력 */
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        r = Integer.parseInt(str[0]);
        c = Integer.parseInt(str[1]);
        map = new char[r][c];
        visited = new boolean[r][c];
        for(int i=0; i<r; i++) {
            String s = br.readLine();
            for(int j=0; j<c; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'M') {
                    moscow = new int[]{i, j};
                }
            }
        }
        
        /* M에서부터 파이프 탐색 */
        searchPipes(moscow[0], moscow[1], 'M');
    }
    
    /* dfs로 이어지는 파이프 탐색 */
    static void searchPipes(int x, int y, char pipe) {
    	
    	// M에서 출발
    	if(pipe == 'M') {
	        
	        for(int i=0; i<4; i++) {
	            int nx = dx[i] + x;
	            int ny = dy[i] + y;
	            
	            if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
	            
	            if(map[nx][ny] != '.') {            	
	                checkPipe(nx, ny);
	            }
	        }
    	}
    	
    	// |일때 상,하 체크
    	else if(pipe == '|') {
    		checkPipe(x-1, y);
            checkPipe(x+1, y);
    	}
    	
    	// -일때 좌,우 체크
    	else if(pipe == '-') {
    		checkPipe(x, y-1);
            checkPipe(x, y+1);
    	}
    	
    	// +일때 상,하,좌,우 체크
    	else if(pipe == '+') {
    		checkPipe(x-1, y);
            checkPipe(x+1, y);
            checkPipe(x, y-1);
            checkPipe(x, y+1);
    	}
    	
    	// 1일때 하,우 체크
    	else if(pipe == '1') {
            checkPipe(x+1, y);
            checkPipe(x, y+1);
    	}
    	
    	// 2일때 상,우 체크
    	else if(pipe == '2') {
    		checkPipe(x-1, y);
            checkPipe(x, y+1);
    	}
    	
    	// 3일때 상,좌 체크
    	else if(pipe == '3') {
    		checkPipe(x-1, y);
            checkPipe(x, y-1);
    	}
    	
    	// 4일때 하,좌 체크
    	else if(pipe == '4') {
            checkPipe(x+1, y);
            checkPipe(x, y-1);
    	}
    	
    	// 현 위치가 '.'일때 (파이프가 끊겼을때) : 파이프 추가
    	else if(pipe == '.') {
    		addPipe(x, y);
    	}
    }
    	
    
    /* 방문한 파이프인지 확인 */
	static void checkPipe(int x, int y) {
		
		// 방문안한 파이프일 경우, 이어지는 파이프 계속 탐색하기
		if(!visited[x][y]) {
            visited[x][y] = true;   
           	searchPipes(x, y, map[x][y]);
        }
	}
	
	/* 파이프 추가 */ 
	static void addPipe(int x, int y) {	
		boolean[] cnnted = {false, false, false, false}; // 파이프 연결이 필요한 방향인지
		
		for(int i=0; i<4; i++) {
			
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			
			if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
			
			// '위'에서 파이프가 연결된 경우
			if(i == 0) {
				if(map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '4') {
					cnnted[i] = true;
				}
			} 
			
			// '아래'에서 파이프가 연결된 경우
			else if(i == 1) {
				if(map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '2' || map[nx][ny] == '3') {
					cnnted[i] = true;
				}
			} 
			
			// '왼쪽'에서 파이프가 연결된 경우
			else if(i == 2) {
				if(map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '2') {
					cnnted[i] = true;
				}
			} 
			
			// '오른쪽'에서 파이프가 연결된 경우
			else if(i == 3) {
				if(map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '3' || map[nx][ny] == '4') {
					cnnted[i] = true;
				}
			}
		}
		
		char pipe = ' ';
		if(cnnted[0] && cnnted[1] && cnnted[2] && cnnted[3]) { // '상,하,좌,우'에서 연결이 필요할때
			pipe = '+';
		} else if(cnnted[0] && cnnted[1]) { // '상,하'에서 연결이 필요할때
			pipe = '|';
		} else if(cnnted[2] && cnnted[3]) { // '좌,우'에서 연결이 필요할때
			pipe = '-';
		} else if(cnnted[1] && cnnted[3]) { // '하,우'에서 연결이 필요할때
			pipe = '1';
		} else if(cnnted[0] && cnnted[3]) { // '상,우'에서 연결이 필요할때
			pipe = '2';
		} else if(cnnted[0] && cnnted[2]) { // '상,좌'에서 연결이 필요할때
			pipe = '3';
		} else if(cnnted[1] && cnnted[2]) { // '하,좌'에서 연결이 필요할때
			pipe = '4';
		}
      
		/* 추가된 파이프 출력 */
        System.out.println((x+1) + " " + (y+1) + " "+pipe);
	} 
} 