import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static String[] symbols;
	static boolean[] Used;
	static int[] nums;
	static long max = 0, min = Long.MAX_VALUE;
	static String maxStr, minStr;
	static BufferedReader br;
	static StringTokenizer st;

	static void input() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		symbols = new String[N];

		for (int i = 0; i < N; i++) {
			symbols[i] = st.nextToken();
		}
	}

	static void rec_func(int idx) {
		if (idx == N + 1) {
			StringBuilder sb = new StringBuilder();
			for(int n : nums) {
				sb.append(n);
			}
			Long num = Long.parseLong(sb.toString());
			if(num > max) {
				max = num;
				maxStr = sb.toString();
			}
			if(num < min) {
				min = num;
				minStr = sb.toString();
			}
			return;
		} else if (idx == 0) {
			for (int i = 0; i <= 9; i++) {
				nums[0] = i;
				Used[i] = true;
				rec_func(1);
				Used[i] = false;
			}
		} else {
			for (int i = 0; i <= 9; i++) {
				if (!Used[i]) {
					if (symbols[idx - 1].equals(">")) {
						if(nums[idx-1] > i) {
							nums[idx] = i;
							Used[i] = true;
							rec_func(idx+1);
							Used[i] = false;
						}
					}
					else {
						if(nums[idx-1] < i) {
							nums[idx] = i;
							Used[i] = true;
							rec_func(idx+1);
							Used[i] = false;
						}
					}
				}
			}
		}
	}
	
	static void pro() {
		nums = new int[N+1];
		Used = new boolean[10];
		rec_func(0);
		System.out.println(maxStr);
		System.out.println(minStr);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
