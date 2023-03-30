import java.util.*;
import java.io.*;


public class Sticker{
	static final int ROW = 2;
	static int[][] stickers;
	static int[][] scores;
	static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < TC; t++) {
			n = Integer.parseInt(br.readLine());
			
			stickers = new int[ROW][n];
			for(int i = 0; i < ROW; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			getStickers();
			System.out.println(Math.max(scores[0][n-1], scores[1][n-1]));
		}
	}

    /* 세번째 열부터 마지막까지 각 열의 최대 점수를 구한다. */
	private static void getStickers() {
		initScores();
		
		for(int col = 2; col < n; col++) {
			scores[0][col] = Math.max(scores[1][col-1],scores[1][col-2]) + stickers[0][col];
			scores[1][col] = Math.max(scores[0][col-1],scores[0][col-2]) + stickers[1][col];
		}
		
	}

    /* 첫번째 열과 두번째 열의 최대 점수를 구한다. */
	private static void initScores() {
		scores = new int[ROW][n];
		
		scores[0][0] = stickers[0][0];
		scores[1][0] = stickers[1][0];
		
		if (n > 1) {
			scores[0][1] = scores[1][0] + stickers[0][1];
			scores[1][1] = scores[0][0] + stickers[1][1];
		}
	}
	
}