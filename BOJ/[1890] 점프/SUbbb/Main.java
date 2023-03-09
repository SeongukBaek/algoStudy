import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] map;
	private static long[][] paths;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        paths = new long[N][N];
        
        for (int x = 0; x < N; x++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int y = 0; y < N; y++) {
        		map[x][y] = Integer.parseInt(st.nextToken());
        	}
        }
        
        if (map[0][0] == 0) {
        	System.out.println(0);
        	return;
        }
        
        paths[0][0] = 1;
        
        for (int x = 0; x < N; x++) {
        	for (int y = 0; y < N; y++) {
        		int jump = map[x][y];
        		if (jump == 0) {
        			continue;
        		}
        		
        		// 오른쪽
        		if (y + jump < N) {
        			paths[x][y + jump] += paths[x][y];
        		}
        		
        		// 아래
        		if (x + jump < N) {
        			paths[x + jump][y] += paths[x][y];
        		}
        	}
        }
        
        System.out.println(paths[N - 1][N - 1]);
    }
}   