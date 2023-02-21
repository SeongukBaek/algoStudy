import java.io.*;
import java.util.*;

public class Main {
	static String inputStr;
	static String bombStr;
	static Stack<Character> strStack;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inputStr = br.readLine();
		bombStr = br.readLine();
		strStack = new Stack<>();
	}

	static void removeAllBomb() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inputStr.length(); i++) {
			strStack.push(inputStr.charAt(i));
            //스택에 폭탄 문자열 길이만큼 push가 되었다면 비교 시작
			if (strStack.size() >= bombStr.length()) {
				for (int j = 0; j <= bombStr.length(); j++) {
					if (j == bombStr.length()) {
						removeBomb();
						break;
					}
					if (strStack.get(strStack.size() - bombStr.length() + j) != bombStr.charAt(j)) {
						break;
					}
				}
			}
		}
		if (strStack.isEmpty()) {
			sb.append("FRULA");
		} else {
			for (char c : strStack) {
				sb.append(c);
			}
		}
		System.out.println(sb);
	}

	static void removeBomb() {
		for (int i = 0; i < bombStr.length(); i++) {
			strStack.pop();
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		removeAllBomb();
	}
}