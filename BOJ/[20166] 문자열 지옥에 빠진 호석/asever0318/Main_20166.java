import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static char map[][];
	static String[] godStr;
	static Map<String, Integer> strCount;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		godStr = new String[K];
		strCount = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < K ; i++) {
			godStr[i] = br.readLine();
			strCount.put(godStr[i], 0);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				casesCount(i, j, ""+map[i][j], 1); // 경우의 수 카운트 
			}
		}
		
		for(int i = 0; i < K; i++) {
			int count = strCount.get(godStr[i]);
			System.out.println(count);
		}
	}
	
	static void casesCount(int x, int y, String nStr, int cnt) {
		
		// map에 저장된 신이 좋아하는 문자열일 경우만 카운트 한다
		if(strCount.containsKey(nStr)) {
			strCount.put(nStr, strCount.get(nStr)+1);
		}
		
		if(cnt == 5) {
			return;
		}
		
		for(int d = 0; d < 8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 격자를 벗어나면 방향에 따라 환형 다음 위치로 좌표 바꿔주기 
			int[] nPos = checkAnnular(nx, ny);
			nx = nPos[0];
			ny = nPos[1];
			
			casesCount(nx, ny, nStr+map[nx][ny], cnt+1);
		}
	}
	
	static int[] checkAnnular(int nx, int ny) {
		
		if(nx < 0) {
			nx = N-1;
		}
		
		if(nx >= N) {
			nx = 0;
		}
		
		if(ny < 0) {
			ny = M-1;
		}
		
		if(ny >= M) {
			ny = 0;
		}
		
		return new int[] {nx, ny};
	}
}
