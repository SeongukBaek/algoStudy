import java.io.*;
import java.util.*;

public class Laboratory {
    static final int WALLCOUNT = 3;
    static int[][] lab;
    static int N, M;
    static int cleanSpace;
    static int max;
    
    static List<Node> virus;
    static List<Node> wallSpace;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
    
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        virus = new ArrayList<>();
        wallSpace = new ArrayList<>(); 
        
        lab = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                
                if(lab[i][j] == 2) {
                    virus.add(new Node(i, j));
                }else if(lab[i][j] == 0) {
                    wallSpace.add(new Node(i,j));
                }
            }
        }
        cleanSpace = wallSpace.size()- WALLCOUNT;
        
        buildWall(0, 0);
        
        System.out.println(max);
    }
    
    static void buildWall(int d, int start) {
    	
        if( d == WALLCOUNT ) {
            spreadVirus();
            return;
        }
        
        for(int i = start; i < wallSpace.size(); i++) {
            int x = wallSpace.get(i).x;
            int y = wallSpace.get(i).y;
            
            if(lab[x][y] != 0) {
                continue;
            }
            
            lab[x][y] = 1;
            buildWall(d+1, i+1);
            lab[x][y] = 0;
        }
    }
    
    static void spreadVirus() {
        Queue<Node> viruses = new LinkedList<>(virus);
        int[][] tempLab = copyLab();
        int tempCleanSpace = cleanSpace;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while(!viruses.isEmpty()) {
        	Node cur = viruses.poll();
        	
        	for(int i = 0; i < 4; i++) {
        		int dx = cur.x + dr[i];
        		int dy = cur.y + dc[i];
        		
        		if(!arrayBoundsValidation(dx, dy)) {
        			continue;
        		}
        		if(tempLab[dx][dy] == 1 || tempLab[dx][dy] == 2) {
        			continue;
        		}
        		
        		viruses.add(new Node(dx, dy));
        		tempLab[dx][dy] = 1;
        		tempCleanSpace--;
        	}
        }
        
        max = Math.max(max, tempCleanSpace);
    }
    
    static int[][] copyLab(){
    	int[][] tempLab = new int[N][M];
    	
    	for(int i = 0; i < N; i++) {
    		tempLab[i] = lab[i].clone();
    	}
    	
    	return tempLab;
    }
    
    static boolean arrayBoundsValidation(int x, int y) {
    	return (x < N && y < M && x >= 0 && y >= 0);
    }
    
    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}