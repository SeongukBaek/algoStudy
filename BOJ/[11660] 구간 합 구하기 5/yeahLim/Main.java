import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		/* 입력, 변수 선언 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n+1][n+1];
		for (int i = 0; i < n; i++) {
			int sum = 0;
			String[] nstr = br.readLine().split(" ");
			// 누적값 넣어주기
			for (int j = 0; j < n; j++) {
				sum += Integer.parseInt(nstr[j]);
				arr[i+1][j+1] = sum;
			}
		}
		
		/* 큰 누적값에서 작은 누적값 빼서 합구하기 */
		int x1, y1, x2, y2;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			int res = 0;
			for(int j=x1; j<=x2; j++) {
				res += arr[j][y2] - arr[j][y1-1];
			}
		/* 출력 */
			sb.append(res).append("\n");
		}
		System.out.println(sb);

	}
}
