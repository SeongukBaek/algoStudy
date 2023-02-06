package BOJ;

import java.util.*;
import java.io.*;

// Greedy 알고리즘 & 정렬 
public class Main_26215 {
	
	// 내림차순 정렬 하려면 Integer배열로 만들어 줘야함 
	static Integer[] snow;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N, cnt;
		
		N =Integer.parseInt(bf.readLine());

		
		snow = new Integer[N];
		String array[] = bf.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			snow[i] = Integer.parseInt(array[i]);
		}
		
		// 두 집 동시에 치우는 시간 최대여야 함 
		// 먼저 정렬 
		Arrays.sort(snow, Collections.reverseOrder());
		
		cnt = shoveled(N);
		
		System.out.println(cnt);
		
		bf.close();
	}

	// 눈 치우기 
	static int shoveled(int N) {
		int count = 0;
		
		if(N == 1) {
			count = snow[0];
		}
		else {
			while(snow[1] != 0) {
				// 한 번 count 할 때마다 다시 정렬 
				snow[0]--;
				snow[1]--;
				count++;
				Arrays.sort(snow, Collections.reverseOrder());
			}
			
			// 마지막 남은 한 집에 남은 눈까지 카운트 
			count += snow[0];
		}
		
		if(count > 1440)
			count =  -1;
		
		return count;
	}
}
