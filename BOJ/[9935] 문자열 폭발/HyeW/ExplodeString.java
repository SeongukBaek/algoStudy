import java.io.*;
import java.util.*;

public class ExplodeString {
	static Stack<Character> chars;
	static String origin;
	static String bomb;
	static int bomb_size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		chars = new Stack<>();

		origin = br.readLine();
		bomb = br.readLine();
		bomb_size = bomb.length();

		char last_char = bomb.charAt(bomb_size - 1);
		for (int i = 0; i < origin.length(); i++) {
			if (origin.charAt(i) != last_char) {
				chars.push(origin.charAt(i));
				continue;
			}
			if (checkBomb()) {
				removeString();
			} else {
				chars.push(origin.charAt(i));
			}
		}

		System.out.println(stackToString());

	}

	static String stackToString() {
		StringBuilder sb = new StringBuilder();

		for (char a : chars) {
			sb.append(a);
		}
		if (sb.length() == 0)
			return "FRULA";
		return sb.toString();
	}

	static boolean checkBomb() {

		for (int i = 1; i < bomb_size; i++) {
			if (chars.size() < bomb_size - 1) {
				return false;
			}
			if (chars.get(chars.size() - i) != bomb.charAt(bomb_size - i - 1)) {
				return false;
			}
		}

		return true;
	}

	static void removeString() {

		for (int i = 0; i < bomb_size - 1; i++) {
			chars.pop();
		}
	}

}