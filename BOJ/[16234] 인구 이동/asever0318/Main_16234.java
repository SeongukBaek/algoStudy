import java.io.*;
import java.util.ArrayList;
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
    
    static int N, L, R;
    static int[][] map;
    static int days;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        days = 0;
        
        while(true) {
            visited = new boolean[N][N];
            int[][] preMap = new int[N][N];
            copyMap(preMap, map); // 현재 맵 복사해놓기 
            
            for(int i = 0; i < N; i++) { // 전체 나라에 대해 확인하면서 
                for(int j = 0; j < N; j++) {
                    
                    if(visited[i][j]) { // 방문한 나라이면 패스
                        continue;
                    }
                    
                    // 방문하지 않은 나라이면(연합에 속하지 않은 나라이면)
                    List<Pos> u = findUnion(i, j); // 연합찾고 
                    
                    movePopulation(u); // 인구이동하고 
                }
            }

            if(isSame(preMap)) { // 인구 이동이 없다면 종료 
            	
                break;
            }
            days++;
        }
        
        System.out.println(days);
    }
    
    // 연합을 찾는 함수
    static List<Pos> findUnion(int i, int j) {
        
        // 4방향으로 인접한 나라들 확인하면서 인구수의 차이가 L이상 R이하라면 맵에 표시 
        Queue<Pos> queue = new LinkedList<>();
        List<Pos> union = new ArrayList<>();
        
        queue.add(new Pos(i, j));
        union.add(new Pos(i, j));
        visited[i][j] = true;
        
        while(!queue.isEmpty()) {
            Pos current = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) { // 맵 벗어나는지 확인하기 
                    continue;
                }
                
                if(!open(current, nx, ny)) { // 국경 열지 확인하기 
                    continue;
                }
                
                if(visited[nx][ny]) {
                	continue;
                }
                
                visited[nx][ny] = true;
                queue.add(new Pos(nx, ny));
                union.add(new Pos(nx, ny));
            }
        }
        return union;
    }
    
    
    static boolean open(Pos current, int nx, int ny) {
        int x = current.x;
        int y = current.y;
        int num = Math.abs(map[x][y] - map[nx][ny]);
        
        if(L <= num && num <= R) {
            return true; // 국경선 열기 
        } 
        return false; // 국경선 열지 않기 
    }
    
    static void movePopulation(List<Pos> union) {
        int size = union.size();
        int sum = 0;
        
        // 인구 총 합 구하기 
        for(int i = 0; i < size; i++) {
            sum += map[union.get(i).x][union.get(i).y];
        }
        
        int p = sum / size; // 인구 이동 후 새로운 인구값 
        for(int i = 0; i < size; i++) {
            map[union.get(i).x][union.get(i).y] = p; // 맵에 새로운 인구값으로 갱신 
        }
    }
    
    static void copyMap(int[][] preMap, int[][] map) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
            	preMap[i][j] = map[i][j];
            }
        }
    }
    
    static boolean isSame(int[][] preMap) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(preMap[i][j] != map[i][j]) { // 변경된 인구 수가 있으면 
                    return false;
                }
            }
        }
        return true;
    }

}

