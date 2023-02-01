import java.util.*;
import java.io.*;

public class InequalitySign {
	static double max = -1;
	static double min = Double.MAX_VALUE;
	static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static char[] inequal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		inequal = new char[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			inequal[i] = st.nextToken().charAt(0);
		}

		perm(0, n + 1, new int[n + 1], new boolean[num.length]);
		if(max < Math.pow(10,n))
			System.out.print("0");
		System.out.printf("%.0f\n",max);
		if(min < Math.pow(10,n))
			System.out.print("0");
		System.out.printf("%.0f\n",min);
	}

	public static void perm(int d, int r, int[] output, boolean[] visited) {

		if (d == r) {
			double temp = changeDo(output);
			max = Math.max(max, temp);
			min = Math.min(min, temp);
			return;
		}

		for (int i = 0; i < num.length; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			output[d] = num[i];

			//부등호 불일치일 경우 가지치기
			if (d > 0)
				if (!validate(output[d - 1], output[d], inequal[d - 1])) {
					visited[i] = false;
					continue;
				}

			perm(d + 1, r, output, visited);
			visited[i] = false;
		}

	}

	public static boolean validate(int first, int second, char sign) {
		if (sign == '>') {
			return first > second;
		} else {
			return first < second;
	}

	public static double changeDo(int[] arr) {
		double result = 0;

		for (int i = 1; i <= arr.length; i++) {
			result += arr[i - 1] * (Math.pow(10, arr.length - i));
		}

		return result;
	}

}
