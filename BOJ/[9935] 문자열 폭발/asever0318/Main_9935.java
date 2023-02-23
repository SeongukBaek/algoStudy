package BOJ;

import java.io.*;
import java.util.Stack;

// [9935] 문자열 폭발 
public class Main_9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean check = true;
		
		String str = br.readLine();
		String bomb = br.readLine();
		int endindex = bomb.length() - 1; // bomb문자열의 마지막 문자 인덱스 
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			
			if(str.charAt(i) == bomb.charAt(endindex) && stack.size() >= bomb.length()) { // bomb의 마지막 문자와 같으면 
				check = true;
				int start = stack.size() - bomb.length(); // stack에서 탐색 시작할 위치 
				
				for(int j = 0; j < endindex; j++) {
					if(stack.get(start+j) != bomb.charAt(j)) { // 틀린문자가 있으면 false
						check = false;
						break;
					}
				}
				
				if(check == true) { // 폭발 문자열과 일치하면 
					for(int a = 0; a < bomb.length(); a++) {
						stack.pop();
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c : stack) {
			sb.append(c);
		}
		
		System.out.println(sb);
	}
}
