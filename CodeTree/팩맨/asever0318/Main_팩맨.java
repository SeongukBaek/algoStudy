import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// m개의 몬스터, 1개의 팩맨
// 1. 몬스터 복제 시도 --> 해당 자리에 알(같은 방향)
// 2. 몬스터 이동 --> 방향대로 1칸 이동 (if, 몬스터 시체 or 팩맨이 있으면 반시계 45회전)
// 3. 팩맨 이동 --> 상하좌우 3번 이동 (몬스터를 가장 많이 먹을 수 있는 방향) -> 이동하는 동안 칸에 있는 몬스터 모두 삭제, 시체 남김
// 4. 몬스터 시체 소멸 --> 2턴 동안만 유지 
// 5. 몬스터 복제 완성 
public class Main {
    static class Monster{
        int x, y, d; // 방향 8방향

        public Monster(int r, int c, int d) {
            super();
            this.x = r;
            this.y = c;
            this.d = d;
        }
    }
    
    static int[] pacman;
    static int m, t;
    static List<Monster> monsters, eggs;
    static int[][] deadTime, mCount;
    static int[][] comb;
    
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 몬스터 수 
        t = Integer.parseInt(st.nextToken()); // 턴의 수 
        
        deadTime = new int[5][5]; // 시체 시간 맵 --> 경과 시간 
        mCount = new int[5][5]; // 몬스터 수 카운트 
        pRoute = new int[3]; // 팩맨이 3번 갈 방향 저장 
        maxRoute = new int[3]; // 가장 많이 먹을 수 있는 최종 루트 저장 
        comb = new int[64][3];
        // 팩맨 초기 위치 
        st = new StringTokenizer(br.readLine());
        pacman = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        monsters = new ArrayList<>();

        // 초기 몬스터 리스트 
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            monsters.add(new Monster(x, y, d));
        }
        
        findcomb(0, 0);
       
        // t턴만큼 반복 
        for(int i = 0; i < t; i++) {
        	// 1. ---------------------------------------------
            copyMonster(); // 몬스터 복제 
          /*  System.out.println("pacman: "+pacman[0]+" "+pacman[1]);
            for(int j = 1; j < 5; j++) {
	            for(int k = 1; k < 5; k++) {
	                System.out.print(mCount[j][k]+" ");
	            }
	            System.out.println();
	        }
	        System.out.println();*/
            // 2. ---------------------------------------------
            moveMonster(); // 몬스터 이동 
            
            // 3. ---------------------------------------------
            maxEat = -1;
           // System.out.println("pacman: "+pacman[0]+" "+pacman[1]);
            findPacmanRoute(); // 팩맨이 몬스터를 가장 많이 먹는 루트 찾기 
           /* System.out.println("maxEat: "+maxEat);
            System.out.println(Arrays.toString(maxRoute));*/
            
           /* 
            for(int j = 1; j < 5; j++) {
	            for(int k = 1; k < 5; k++) {
	                System.out.print(mCount[j][k]+" ");
	            }
	            System.out.println();
	        }
	        System.out.println();*/
            killMonster(); // 루트대로 몬스터 죽이고 시체 처리 
            /*System.out.println("pacman: "+pacman[0]+" "+pacman[1]);
            for(int j = 1; j < 5; j++) {
	            for(int k = 1; k < 5; k++) {
	                System.out.print(mCount[j][k]+" ");
	            }
	            System.out.println();
	        }
	        System.out.println();*/
            // 4. ---------------------------------------------
            removeDead(); // 시체 소멸 --> deadTime이 2이면 삭제 
            
            // 5. ---------------------------------------------
            bornMonster(); // 몬스터 복제 완성 
            timePlus();
             /*for(int j = 1; j < 5; j++) {
	            for(int k = 1; k < 5; k++) {
	                System.out.print(mCount[j][k]+" ");
	            }
	            System.out.println();
	        }
	        System.out.println();*/
            
        }
        System.out.println(monsters.size());
    }
    
    // 1. 몬스터 복제 (알)
    static void copyMonster() {
        eggs = new ArrayList<>();

        for(int i = 0; i < monsters.size(); i++) {
            Monster m = monsters.get(i);
            eggs.add(new Monster(m.x, m.y, m.d));
        }
    }
    
    
    // 반시계 45도씩 8방향 
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    
    // 2. 몬스터 이동 
    static void moveMonster() {
        int[][] copyMap = new int[5][5];
        
        for(int i = 0; i < monsters.size(); i++) {
            
            Monster m = monsters.get(i);            
            int nd = m.d;
            
            while(true) {
                
                int nx = m.x + dx[nd]; // 다음 이동 위치 
                int ny = m.y + dy[nd];
                
                // 시체 x && 팩맨 x && 격자 벗어나지 x
                if(isVaildIndex(nx, ny) && deadTime[nx][ny] == 0 && !isPacman(nx, ny)) {
                    // 이동할 수 있으면 이동 --> 좌표 변경
                	//System.out.println(m.x+" "+m.y +" "+m.d+ "-->"+nx+" "+ny);
                    monsters.get(i).y = ny;
                    monsters.get(i).x = nx;
                    monsters.get(i).d = nd;
                    copyMap[nx][ny]++; // 맵에 추가 

                    break;
                }
                
                // 시계 방향 45도 회전 
                nd = rotate(nd);
                
                // 8방향 다 돌았는데도 움직일 수 없으면 
                if(nd == m.d) {
                    copyMap[m.x][m.y]++; // 맵에 추가 
                    break;
                }
            }
        }
        
        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {
                mCount[i][j] = copyMap[i][j];
            }
        }
    }
    
    static int count;
    static int[] list;
    
    static void findcomb(int n, int index) {
    	if(n == 3) {
    		for(int i = 0; i < 3; i++) {
    			comb[count][i] = pRoute[i];
    		}
    		count++;
    		return;
    	}
    	
    	for(int i = 1; i <= 4; i++) {
    		pRoute[n] = i;
            findcomb(n+1, index+1);
        }
    }
    
    static int maxEat;
    static int[] pRoute, maxRoute; // 팩맨이 갈 루트 
    // 팩맨 이동 우선순위 --> 상좌하우 
    static int[] px = {0, -1, 0, 1, 0};
    static int[] py = {0, 0, -1, 0, 1};
    
    // 팩맨 이동할 길 찾기  --> 상하좌우(1234)
    static void findPacmanRoute() {
        
    	for(int i = 0; i < 64; i++) {
    		
    		int cnt = countEat(comb[i]);
    		if(maxEat < cnt) {
                maxEat = cnt;
                
                // max 갱신 될때마다 해당 루트도 저장해놓기 
                for(int j = 0; j < 3; j++) {
                    maxRoute[j] = comb[i][j];
                }
            }
    	}
       
    }

    static int countEat(int[] arr) {
    	
        boolean[][] visited = new boolean[5][5];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                visited[i][j] = false;
            }
        }
        
        int count = 0;
        // 현재 팩맨 위치 
        int nx = pacman[0];
        int ny = pacman[1];
        
        
        for(int i = 0; i < 3; i++) {
            nx = nx + px[arr[i]];
            ny = ny + py[arr[i]];
            
            if(!isVaildIndex(nx, ny)) { // 팩맨이 이동할 곳이 격자 밖이면 x
                return -1;
            }
            
            if(!visited[nx][ny]) { // 방문하지 않았으면 
                visited[nx][ny] = true;
                 
                 // 몬스터 수만큼 먹고 카운트 
                 count += mCount[nx][ny];
            }
        }
        return count;
    }
    
    // 몬스터 죽이고 시체표시하고 시체시간 표시 
    static void killMonster() {
        // 루트 따라서 길에 몬스터 있으면 죽이기 --> 리스트에서 삭제 
        int nx = pacman[0];
        int ny = pacman[1];
        
        for(int i = 0; i < 3; i++) {
            nx = nx + px[maxRoute[i]];
            ny = ny + py[maxRoute[i]];
            
            if(i == 2) {
                pacman[0] = nx;
                pacman[1] = ny;
            }
            
            if(mCount[nx][ny] > 0) { // 몬스터 있으면
                mCount[nx][ny] = 0; // 죽이고 
                deadTime[nx][ny] = 1; // 시체 시간 1로 초기화 
                // 몬스터 리스트에서 삭제
                for(int j = 0; j < monsters.size(); j++) {
                    Monster m = monsters.get(j);
                    if(m.y == ny && m.x == nx) { // 해당 좌표에 있는 몬스터 리스트에서 삭제 
                        monsters.remove(j);
                        j--;
                    }
                }
            }
        }
    }
    
    // 2턴 지난 시체는 삭제 
    static void removeDead() {
        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {
                
            	if(deadTime[i][j] >= 3) { // 2턴 지났으면 삭제 
                    deadTime[i][j] -= 3;
                }
            }
        }
    }
    
    // 몬스터 부화
    static void bornMonster() {
        for(int i = 0; i < eggs.size(); i++) {
            Monster m = eggs.get(i);
            monsters.add(new Monster(m.x, m.y, m.d)); // 몬스터 리스트에 추가 
            mCount[m.x][m.y]++;
        }
    }
    
    // 해당 좌표에 팩맨이 있는지 없는지 
    static boolean isPacman(int nx, int ny) {
        if(nx == pacman[0] && ny == pacman[1]) {
            return true;
        }
        return false;
    }
    
    static boolean isVaildIndex(int nx, int ny) {
        if(nx < 1 || nx >= 5 || ny < 1 || ny >= 5) {
            return false;
        }
        return true;
    }
    
    // 시계방향 45도 회전 
    static int rotate(int d) {
        d++;
        if(d == 9) {
            d = 1;
        }
        return d;
    }
    
    static void timePlus() {
        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {
                if(deadTime[i][j] > 0) {
                    deadTime[i][j]++;
                }
            }
        }
    }
}