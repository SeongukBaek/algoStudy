import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long cnt;
	static int[][] map;
	static long[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new long[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0][0] = 1;
		searchPath();
		System.out.println(visited[N-1][N-1]);
	}

	private static void searchPath() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int jump = map[i][j];
				if(jump == 0) {
					break;
				}

        //아래로 점프
				if(arrayBoundsValidation(i+jump, j)) {
					visited[i+jump][j] += visited[i][j];
				}
				
        //오른쪽으로 점프
				if(arrayBoundsValidation(i, j+jump)) {
					visited[i][j+jump] += visited[i][j];
				}
			}
		}
	}
	
	private static boolean arrayBoundsValidation(int x, int y) {
		return (x < N && y < N && x >=0 && y >= 0);
	}

}