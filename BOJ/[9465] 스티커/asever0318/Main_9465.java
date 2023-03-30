package algo;

import java.io.*;
import java.util.StringTokenizer;

public class Main_9465 {
	static int T, N;
	static int[][] sticker;
	static int[][] score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			sticker = new int[2][N+2];
			score = new int[2][N+2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 2; i < N+2; i++) {
				sticker[0][i] = Integer.parseInt(st.nextToken());
				score[0][i] = sticker[0][i];
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 2; i < N+2; i++) {
				sticker[1][i] = Integer.parseInt(st.nextToken());
				score[1][i] = sticker[1][i];
			}

			System.out.println(takeSticker());
		}
	}
	
	static int takeSticker() {		
		
		for(int i = 2; i < N+2; i++) {
			// 1. 윗줄 스티커 - 아래줄에서 선택
			score[0][i] = Math.max(score[1][i-1], score[1][i-2]) + sticker[0][i];
			// 2. 아랫줄 스티커  - 윗줄에서 선택
			score[1][i] = Math.max(score[0][i-1], score[0][i-2]) + sticker[1][i];
		}
		
		return Math.max(score[0][N+1], score[1][N+1]);
	}
}
