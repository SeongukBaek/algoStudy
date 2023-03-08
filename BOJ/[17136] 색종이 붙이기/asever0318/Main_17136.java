import java.io.*;
import java.util.StringTokenizer;

public class Main_17136 {
	static int[][] matrix;
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		matrix = new int[10][10];
		
		// 맵 입력 
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		
		dfsPaper(0, 0, 0);
		
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		
		System.out.println(min);
	}
	
	static void dfsPaper(int x, int y, int count) {
		
		if(x >= 9 && y >= 10) { // 오른쪽 끝까지 탐색 끝 
			// 남은 게 있는 지 확인 
			min = Math.min(min, count);
			return;
		}
		
		if(y >= 10) { // y 끝까지 다 탐색했으면 다음행으로 넘어가기
			x++;
			y = 0;
		}
		
		if(matrix[x][y] == 1) { // 1을 만나면 
			for(int p = 1; p <= 5; p++) { // 색종이 하나씩 넣어보기 
				if(checkPaper(x, y, p) && paper[p] > 0) { // 해당 사이즈 색종이 넣을 수 있으면, 색종이가 남아있으면  
					paper[p]--; // 종이 하나 쓰고
					attachPaper(x, y, p);
					dfsPaper(x, y+1, count+1);
					paper[p]++; // 갔다오면 다시 돌려주기 
					removePaper(x, y, p); // 색종이 다시 떼기 
				}
			}
		}else {
			dfsPaper(x, y+1, count);
		}
	}
	
	// 색종이 넣을 수 있는지 
	static boolean checkPaper(int x, int y, int p) {
		
		for(int r = x; r < x + p; r++) {
			for(int c = y; c < y + p; c++) {
				
				// 색종이가 종이를 벗어나면 x
				if(r >= 10 || c >= 10) {
					return false;
				}
				
				// 색종이 크기가 안맞으면 x
				if(matrix[r][c] == 0) {
					return false;
				}
			}
		}
		
		return true; // 넣을 수 있음 
	}
	
	
	// 색종이 붙이기 
	static void attachPaper(int x, int y, int p) {
		
		for(int i = x; i < x + p; i++) {
			for(int j = y; j < y + p; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	// 색종이 다시 떼기 
	static void removePaper(int x, int y, int p) {
		
		for(int i = x; i < x + p; i++) {
			for(int j = y; j < y + p; j++) {
				matrix[i][j] = 1;
			}
		}
	}
}
