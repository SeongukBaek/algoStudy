import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] alphabat = new int[26];
		int n = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int start = 0, end = 0, count = 1, result = 1;
		
		// 문자열의 가장 앞의 값을 +1 해준다.
		alphabat[str.charAt(0) - 'a']++;
		while (end < str.length() - 1) {
			end++;
			char temp = str.charAt(end);
			// 새로 들어온 문자가 기존에 없는 경우
			if (alphabat[temp - 'a'] == 0) {
				count++;
				if (count <= n) {
					alphabat[temp - 'a']++;
				}
				// 알파벳의 종류의 최대 개수를 넘은 경우
				else {
					while (true) {
						alphabat[str.charAt(start) - 'a']--;
						if (alphabat[str.charAt(start) - 'a'] == 0) {
							start++;
							break;
						}
						start++;	
					}
					alphabat[temp - 'a']++;
					count--;
				}
			}
			// 새로 들어온 문자가 기존에 존재하는 경우
			else {
				alphabat[temp - 'a']++;
			}
			// 문자열 길이 비교
			result = Math.max(result, end - start + 1);
		}
		System.out.println(result);
	}
}