import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				sb.append(c);
				continue;
			}
			if (c == '(') {
				stack.add(c);
				continue;
			}
			if (c == ')') {
				while (stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.pop();
				continue;
			}
			while (!stack.isEmpty() && (prior(stack.peek()) >= prior(c))) {
				sb.append(stack.pop());
			}
			stack.add(c);

		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}

	private static int prior(char c) {
		if (c == '*' || c == '/') {
			return 1;
		}
		if (c == '+' ||  c == '-') {
			return 0;
		}
		return -1;
	}
}