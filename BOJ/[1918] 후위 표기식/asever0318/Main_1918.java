import java.io.*;
import java.util.Stack;

public class Main {
	
	static char[] str;
	static Stack<Character> oper; // 연산자 담을 스택
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		oper = new Stack<>();
		
		System.out.println(postfix());
		
	}
	
	// 연산자 우선순위 : 	2 -- *, / 
	//				1 -- +, -
	//				0 -- (, )
	static String postfix() {
		String answer = null;
		StringBuilder sb = new StringBuilder(); // 정답 문자열
		
		// 문자열 끝까지 돌면서 
		for(int i = 0; i < str.length; i++) {
			
			// 1. 숫자(알파벳)면 그냥 출력
			if('A' <= str[i]) {
				sb.append(str[i]);
				continue;
			}

			// 2. 숫자가 아니면 --> 연산자이면 
			// 여는 괄호이면 스택에 담기
			if('(' == str[i]) {
				oper.push(str[i]); // 스택에 삽입
			}
			
			// 닫는 괄호이면 여는 괄호까지 스택에 있는 거 빼기 
			else if(')' == str[i]) {
				while(!oper.isEmpty() && oper.peek() != '(') {
					sb.append(oper.pop());
				}
				if(!oper.isEmpty()) {					
					oper.pop(); // 여는 괄호 빼주기
				}
			}
			
			// 일반 연산자면
			else { // 연산자 스택이 비지않고 스택에 담긴 연산자의 순위가 더 높거나 같으면 먼저 출력해야하므로 꺼내줌
				while(!oper.isEmpty() && priority(str[i]) <= priority(oper.peek())) {
					sb.append(oper.pop());
				}
				oper.push(str[i]);
			}
		}
		
		while(!oper.isEmpty()) {
			sb.append(oper.pop());
		}
		
		answer = sb.toString();
		return answer;
	}
	
	// 우선자 연산순위 반환
	static int priority(char op) {
		if(op == '*' || op == '/') {
			return 2;
		}
		
		if(op == '+' || op == '-') {
			return 1;
		}
		
		return 0; // 괄호 
	}
}
