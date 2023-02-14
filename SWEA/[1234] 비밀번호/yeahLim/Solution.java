import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			
			/* 입력 */
			String[] str = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]);
			List<Character> seq = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				seq.add(str[1].charAt(i));
			}
			
			/* 붙어있는 숫자 제거 */
			int i = 0;
			while (!seq.isEmpty()) {

				if (seq.get(i) == seq.get(i + 1)) {
					seq.remove(i);
					seq.remove(i);
					i = (i == 0) ? i : --i; // 인덱스가 0이 아니면 -1
				} else {
					i++;
				}

				if (i == seq.size() - 1) {
					break;
				}

			}
			
			
			/* 출력 */
			System.out.print("#"+ t + " ");
			for (char s : seq) {
				System.out.print(s);
			}
			System.out.println();
		}
	}
}
