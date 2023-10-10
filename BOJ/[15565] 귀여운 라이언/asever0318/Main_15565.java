package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15565 {
	static int N, K;
	static int[] doll;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		doll = new int[N];
		
		int i = 0;
		while(st.hasMoreTokens()) {
			doll[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		
		System.out.println(getRyanCount());
	}
	
	static int getRyanCount() {
		int count = 0;
		int ryan = 0;
		int min = Integer.MAX_VALUE;
		int left = 0; 
		int right = 0;
		
		// 첫 요소가 2라면 처음 1이 나오는 위치까지 이동
		if(doll[right] == 2) { 
			while(true) {
				right++;
				if(right >= N || doll[right] == 1) {
					left = right;
					break;
				}
			}
		}
		
		// 1이 나오는 위치부터 진행 
		while(left < N && right < N) {
			count++;
			if(doll[right] == 1) { // 만약 1을 만나면 라이언 추가
				ryan++;
				
				if(ryan == K) { // 만약 라이언이 K개만큼 포함되어 있다면
					min = Math.min(min, count); // min값 갱신 후
					
					while(true) { // left좌표를 다음 1이 나오는 위치로 옮겨주기
						left++;
						count--; // 이동하는 만큼 count에서도 제거 
						if(left >= N || doll[left] == 1) {
							break;
						}
					}
					ryan--; // 다음 좌표로 옮겼기 때문에 라이언 수는 -1
				}
			}
			right++;
		}
		
		if(min == Integer.MAX_VALUE) { // min값에 변화가 없으면 K개 이상의 연속된 집합을 못찾은 것 
			min = -1;
		}
		
		return min;
	}
}
