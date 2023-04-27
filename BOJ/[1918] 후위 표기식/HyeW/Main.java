import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String infix = br.readLine();
		
		System.out.println(makePostNotation(infix));
	}

	private static String makePostNotation(String infix) {
		StringBuilder sb = new StringBuilder();
		Deque<Character> operators = new ArrayDeque<>();
		
		for(int i = 0; i < infix.length(); i++) {
			char cur = infix.charAt(i);
			// 변수면 바로 씀
			if(isVariable(cur)) {
				sb.append(cur);
				continue;
			}
			
			// 연산자일 경우
			if(cur ==')') {
				popAll(sb, operators);
				continue;
			}
            // 현재 +,-이고 이전 연산자가 *,/일 경우
			if(operators.size() > 0 && isPlusOrMinus(cur) && isMultOrDivide(operators.peek())) {
				sb.append(operators.pop());
			}
            // 현재 연산자와 이전 연산자의 우선순위가 같은 경우
            // +,-일 경우
            if(operators.size() > 0 && isPlusOrMinus(cur) && isPlusOrMinus(operators.peek())) {
				sb.append(operators.pop());
			}
            // *,/일 경우
            if(operators.size() > 0 && isMultOrDivide(cur) && isMultOrDivide(operators.peek())) {
				sb.append(operators.pop());
			}
			operators.push(cur);
		}
		popAll(sb, operators);
		
		return sb.toString();
	}

	private static void popAll(StringBuilder sb, Deque<Character> operators) {
		while(!operators.isEmpty()) {
			char temp = operators.pop();
			if(temp == '(') {
				break;
			}
			sb.append(temp);
		}
	}

	private static boolean isMultOrDivide(char cur) {
		return cur == '*' || cur == '/';
	}
	
	private static boolean isPlusOrMinus(char cur) {
		return cur == '+' || cur == '-';
	}
	private static boolean isVariable(char cur) {
		return cur >= 'A' && cur <= 'Z';
	}
}
