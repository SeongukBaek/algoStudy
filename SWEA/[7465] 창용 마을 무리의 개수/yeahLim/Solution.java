import java.io.*;
import java.util.*;

public class Solution {
	static int[] map;
	static int count;

	public static void main(String[] args) throws IOException {
		
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			count = n;
			map = new int[n + 1];
			for (int i = 1; i < n + 1; i++) {
				map[i] = i;
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				union(num1, num2);
			}
			
			
			/* 출력 */
			System.out.println("#"+t+" "+count);
		}
	}

	/* 다른 무리를 한 무리로 합치기 */
	static void union(int num1, int num2) {
		int root1 = searchRoot(num1);
		int root2 = searchRoot(num2);
		if(root1 != root2) {
			map[root2] = root1;
			count--;
		}
	}
	
	/* 루트값 찾기 */
	static int searchRoot(int num) {
		if(map[num] == num) {
			return num;
		}
		return map[num] = searchRoot(map[num]);
	}
}
