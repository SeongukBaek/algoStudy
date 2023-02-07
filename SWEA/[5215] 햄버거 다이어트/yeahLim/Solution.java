import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static int cal;
	static int[][] arr;
	static int maxScore;

	public static void main(String[] args) throws Exception {

		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			/* 입력 */
			String[] sarr = br.readLine().split(" ");
			n = Integer.parseInt(sarr[0]);
			cal = Integer.parseInt(sarr[1]);
			arr = new int[n][2];
			maxScore = 0;
			for (int i = 0; i < n; i++) {
				String[] sa = br.readLine().split(" ");
				arr[i][0] = Integer.parseInt(sa[0]);
				arr[i][1] = Integer.parseInt(sa[1]);
			}

			backtrck(0, 0, 0);

			System.out.println("#" + (t+1) + " " + maxScore);

		}

	}

	static void backtrck(int idx, int scoreSum, int calSum) {

		if (calSum > cal)
			return;
		
		if(idx == n) {
			if(maxScore < scoreSum)			
				maxScore = scoreSum;
			return;
		}
		
		// 추가했을 경우
		backtrck(idx+1, scoreSum+arr[idx][0], calSum+arr[idx][1]);
		// 추가하지 않았을 경우
		backtrck(idx+1, scoreSum, calSum);
	}
}
