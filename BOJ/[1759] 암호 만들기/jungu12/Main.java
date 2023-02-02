import java.util.*;
import java.io.*;

public class Main {
	static int len;
	static int N;
	static String[] input;
	static String[] pw;
	static BufferedReader br;
	static List<String> vowel = new ArrayList<>(Arrays.asList("a", "e", "i", "o", "u"));
	static List<String[]> outputs = new ArrayList<>();

	static void input() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		len = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		input = new String[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = st.nextToken();
		}
	}

	static void rec_func(int idx, int last) {
		if (idx == len) {
			int count = 0;
			for (int i = 0; i < len; i++) {
				if (vowel.contains(pw[i])) {
					count++;
				}
			}
			if (count >= 1 && len - count >= 2) {
				outputs.add(pw);
			}
			return;
		}
		
		for (int i = last; i <= N - (len - idx); i++) {
			pw[idx] = input[i];
			System.out.println(Arrays.toString(pw));
			System.out.println(outputs.size());
			rec_func(idx + 1, i + 1);
			pw[idx] = null;
		}
	}

	static void pro() {
		pw = new String[len];
		Arrays.sort(input);
		rec_func(0, 0);
		for (String[] output : outputs) {
			for (String s : output) {
				System.out.print(s);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}