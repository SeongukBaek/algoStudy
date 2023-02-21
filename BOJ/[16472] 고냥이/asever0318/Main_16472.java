package week4;

import java.io.*;

// 최대 N개의 연속된 문자열 --> 투포인터 
public class Main_16472 {
	static char[] clist;
	static int max;
	static int N;
	static int[] check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 문자열을 char 배열로 저장 
		clist = br.readLine().toCharArray();
		check = new int[26]; // 알파벳 크기만큼 
		
		catsMessage();
		
		System.out.println(max);
	}
	
	public static void catsMessage() {
		
		int left = 0, right = 0;
		check[clist[0] - 'a']++;
		int charcnt = 1; // 인식할 수 있는 문자수 
		int count = 0;

		while(right < clist.length-1) {
			
			// 등록된 문자가 N개보다 적으면
			right++;
			
			check[clist[right] - 'a']++;
			
			// 만약에 처음 등록된 거면 char개수 +1
			if(check[clist[right] - 'a'] == 1) {
				charcnt++; 
			}
			
			while(charcnt > N) { // N개가 될때까지 반복 (문자 하나 지울 때까지)
				
				check[clist[left] - 'a']--;
				
				if(check[clist[left] - 'a'] == 0) {
					charcnt--;
				}
				
				left++;
			}
			
			count = right - left + 1; // 인덱스 0부터니까 +1
			if(max < count) {
				max = count;
			}
		}
	}
}
