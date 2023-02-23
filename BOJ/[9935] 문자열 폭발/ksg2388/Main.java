import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		String boomWord = br.readLine();
		int interval = boomWord.length();
		
		Stack<Character> result = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			char temp = str.charAt(i);
			result.add(temp);
			
			// 마지막 문자열이 같으면
			if (temp == boomWord.charAt(interval - 1) && result.size() >= interval) {
				boolean isValidate = true;
				for (int j = 0; j < interval - 1; j++) {
					if (result.get(result.size() - interval + j) != boomWord.charAt(j)) {
						isValidate = false;
					}
				}
				
				if (isValidate) {
					for (int j = 0; j < interval; j++) {
						result.pop();
					}
				}
			}
			
			
		}
		
		for (char c: result) {
			sb.append(c);
		}
		
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}
}