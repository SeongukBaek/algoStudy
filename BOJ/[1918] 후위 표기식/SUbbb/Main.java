import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expressions = br.readLine();

		Stack<Character> operators = new Stack<>();
		StringBuilder result = new StringBuilder();

		for (int index = 0; index < expressions.length(); index++) {
			char current = expressions.charAt(index);

			// 알파벳이면
			if (Character.isAlphabetic(current)) {
				result.append(current);
			}

			// 연산자이면
			if (current == '*' || current == '/' || current == '+' || current == '-') {
				while (!operators.isEmpty() && isLeftFirst(operators.peek(),current)) {
					result.append(operators.pop());
				}
				operators.push(current);
			}

			if (current == '(') {
				operators.push(current);
			}

			if (current == ')') {
				while (!operators.isEmpty() && operators.peek() != '(') {
					result.append(operators.pop());
				}
				// ( 빼기
				operators.pop();
			}
		}

		while (!operators.isEmpty()) {
			result.append(operators.pop());
		}

		System.out.println(result);
	}

	/**
	 * 왼쪽의 연산자가 우선순위를 가진다면 true 반환
	 * */
	private static boolean isLeftFirst(char left, char right) {
		return getPriority(left) >= getPriority(right);
	}

	private static int getPriority(char operator) {
		if (operator == '*' || operator == '/') {
			return 2;
		}
		if (operator == '+' || operator == '-') {
			return 1;
		}
		return 0;
	}
}