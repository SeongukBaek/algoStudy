import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=0; t<10; t++) {
			int tc = Integer.parseInt(br.readLine());
			String[] sarr = br.readLine().split(" ");
			int[] arr = new int[8];
			for(int i=0; i<8; i++) 
				arr[i] = Integer.parseInt(sarr[i]);
			
			/* 0이 도출될때까지, 각 원소에 num만큼 빼주기 */
			int num = 1;
			int idx = 0;
			for(int i=0; i<8; i++) {
				
				arr[i] -= num;
				
				if(arr[i] <= 0) {
					arr[i] = 0;
					idx = i+1;
					break;
				}
				
				num++;
				
				if(num == 6)
					num = 1;
				
				if(i == 7)
					i = -1;
			}
			
			/* 출력 */
			System.out.print("#" + tc + " ");
			idx %= 8;
			for(int i=idx; i<8; i++) 
				System.out.print(arr[i] + " ");
			for(int i=0; i<idx; i++)
				System.out.print(arr[i] + " ");
		}
	}
}
