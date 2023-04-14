import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    
    static class Pos{
        int x, y;

        public Pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
    
    static int R, C, T;
    static int[][] map, score;
    static List<Pos> machine;
    static Queue<Pos> dust;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        dust = new LinkedList<>();
        machine = new ArrayList<>();
        score = new int[R][C];
        
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                score[i][j] = map[i][j];
                
                if(map[i][j] == -1) { // 공기청정기 위치 
                    machine.add(new Pos(i, j));
                    score[i][j] = -1;
                }
                
                if(map[i][j] > 0) { // 초기 먼지 위치 큐에 넣기 
                    dust.add(new Pos(i, j));
                }
            }
        }
        
        for(int t = 0; t < T; t++) {

            // 1. 미세먼지 확산
            moveDust(dust); // 다음에 퍼질 먼지 큐 반환 
            copyMap(map, score); 
            
            // 2. 공기청정기 작동 
            airMachineTop();
            airMachineBottom();
            copyMap(score, map);
            findDust(); // 먼지 큐 구하기 
            
        }
        System.out.println(countDust());
    }
    
    static void copyMap(int[][] a, int[][] b) {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                a[i][j] = b[i][j];
            }
        }
    }
    
    // 미세먼지 확산 
    static void moveDust(Queue<Pos> queue) {
                
        while(!queue.isEmpty()) {
            
            Pos current = queue.poll();
            int dustSize = map[current.x][current.y] / 5;
            int count = 0;
            
            for(int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                
                if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                
                if(map[nx][ny] >= 0) { // 빈 공간일때만 확산 
                	score[nx][ny] += dustSize; // 미세먼지 확산 
                    count++; // 몇 칸에 퍼졌는지 카운트 
                }
            }
            
            score[current.x][current.y] -= (dustSize*count);
        }
    }
    
    
    static int[][] up = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 시계방향 
    static int[][] down = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 반시계방향
    
    // 공기청정기 작동 --> 먼지가 공기청정기에 들어가면 없어져야 함
    static void airMachineTop() {
        // 위
        int x = machine.get(0).x;
        int y = machine.get(0).y;
        int d = 0;
        
        map[x-1][y] = 0; // 먼지가 공기청정기에 들어가면 없어져야 함!
        
        while(true) {
            // 공기청정기가 있던 자리로 돌아오면 작동 종료 
            
            int nx = x + up[d][0];
            int ny = y + up[d][1];
            
            // 바람이 부는 루트 전체를 한칸씩 이동 
            
            // 벽을 만나면 방향 틀기 
            if(nx < 0 || nx > machine.get(0).x || ny < 0 || ny >= C ) {
                d++;
                if(d == 4) {
                    d = 0;
                }
                continue;
            }
           
            
            int temp = map[nx][ny];
            map[nx][ny] = map[x][y];
            map[x][y] = temp;
            x = nx;
            y = ny;
            
            if(x == machine.get(0).x && y == machine.get(0).y) {
                break;
            }
        }
    }
    
    static void airMachineBottom() {
        // 아래 
        int x = machine.get(1).x;
        int y = machine.get(1).y;
        int d = 0;
        map[x+1][y] = 0; // 먼지가 공기청정기에 들어가면 없어져야 함!

        while(true) {
            // 공기청정기가 있던 자리로 돌아오면 작동 종료 
            
            int nx = x + down[d][0];
            int ny = y + down[d][1];
            
            // 바람이 부는 루트 전체를 한칸씩 이동 
            
            // 벽을 만나면 방향 틀기 
            if(nx <= machine.get(0).x || nx >= R || ny < 0 || ny >= C ) {
                d++;
                if(d == 4) {
                    d = 0;
                }
                continue;
            }
            
            int temp = map[nx][ny];
            map[nx][ny] = map[x][y];
            map[x][y] = temp;
            x = nx;
            y = ny;
            
            if(x == machine.get(1).x && y == machine.get(1).y) {
                break;
            }
        }
    }
    
    static int countDust() {
        int count = 0;
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 0) {
                    count += map[i][j];
                }
            }
        }
        
        return count;
    }
    
    // 공기청정기 작동 후에 다시 먼지 위치 찾아주기 
    static void findDust() {
    	Queue<Pos> newDust = new LinkedList<>();
    	
    	for(int i = 0; i < R; i++) {
    		for(int j = 0; j < C; j++) {
    			if(map[i][j] > 0) {
    				newDust.add(new Pos(i, j));
    			}
    		}
    	}
    	dust.addAll(newDust);
    }
}


