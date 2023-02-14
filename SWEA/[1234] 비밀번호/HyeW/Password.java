import java.util.*;
import java.io.*;

public class Password {
	static final int TEST_CASE = 10;
	static List<Integer> number;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;

		for (int t = 1; t <= TEST_CASE; t++) {
			st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();

			n = Integer.parseInt(st.nextToken());
			String numbers = st.nextToken();
			number = new LinkedList<>();

			for(int i = 0; i < n; i++) {
				number.add(numbers.charAt(i) -'0');
			}
			
			removeDup();

			sb.append("#" + t + " ");
			for (int num : number) {
				sb.append(num);
			}
			System.out.println(sb.toString());
		}

	}

	static void removeDup() {

		for (int i = 1; i < number.size(); i++) {
			i = removeNum(i - 1, i);
		}
	}

	static int removeNum(int left, int right) {

		while ((left >= 0) && (right < number.size())) {

			if (number.get(left) != number.get(right)) {
				break;
			}	

			number.remove(left);
			number.remove(left);
			
			left--;
			right--;
		}
		return left+1;

	}

}