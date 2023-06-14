import java.io.*;

public class Main {
	static int N, max;
	static int[] wine, wineSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		wine = new int[N+3];
		wineSum = new int[N+3]; // 첫 잔부터 탐색하기 위해서 앞에 3칸 비우기
		
		for(int i = 3; i < N+3; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(getMax());
	}
	
	static int getMax() {
		
		for(int i = 3; i < N+3; i++) {
			// i번째 잔을 마시는 경우
			int sum1 = wine[i] + wineSum[i-2]; // 전전잔을 마시는 경우
			int sum2 = wineSum[i-3] + wine[i-1] + wine[i]; // 이전잔을 마시는 경우
			int max = Math.max(sum1, sum2);
			
			// i번째 잔을 안마시는 경우와 비교하여 더 큰 값 저장
			wineSum[i] = Math.max(max, wineSum[i-1]);
		}
		
		return wineSum[N+2];
	}
}
