import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		
		/* 입력 및 변수 선언 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 알파벳의 최대 종류
		String str = br.readLine();
		int[] alphCnt = new int[26];
		int maxLen = 0; // 문자열의 최대 길이
		int count = 0;
		int left = 0, right = 0;
		
		/* 투포인터로 문자열의 최대 길이 구하기 */
		while(right < str.length()) {
			
			
			alphCnt[str.charAt(right) - 'a']++;
			
			if(alphCnt[str.charAt(right) - 'a'] == 1) {
				count++;
			}
			
			while(count > n) {
				alphCnt[str.charAt(left) - 'a']--;
				if(alphCnt[str.charAt(left) - 'a'] == 0) {
					count--;
				}
				left++;
			}
			
			right++;
			
			maxLen = (maxLen < right - left) ? right - left : maxLen;
		}
		
		/* 출력 */
		System.out.println(maxLen);
	}
}
