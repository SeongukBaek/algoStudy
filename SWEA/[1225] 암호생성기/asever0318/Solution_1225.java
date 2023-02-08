package SWEA;

import java.util.*;
import java.io.*;

// [1225] 암호 생성기
public class Solution_1225 {
	
	// queue는 LinkedList로 생성해야 함 
	static Queue<Integer> queue = new LinkedList<Integer>();
			
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		
		for(int i = 1; i<= 10; i++) {
			int N = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			 
			// 큐에 집어넣기 
			for(int j = 0; j<8; j++) {
				queue.offer(Integer.parseInt(str[j])); // 실패시 false 반환 
			}
			
			int count = 1;
			int num =  queue.poll() - count;
			
			while(num > 0) {
				queue.offer(num);
				count++;
				
				if(count > 5)
					count = 1;
				
				num = queue.poll() - count;
			}
			
			queue.offer(0); // 마지막 요소 0 삽입 
			
			printQueue(N);
				
			queue.clear(); //queue 비우기 
		}
		
		br.close();
	}
	
	
	public static void printQueue(int N) {
		Iterator iter = queue.iterator();
		
		System.out.print("#"+N+" ");
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println();
	}
}
