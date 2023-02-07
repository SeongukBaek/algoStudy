package practice.sw;

import java.util.*;
import java.io.*;

class sw1225 {
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		for (int tc = 1; tc <= 10; tc++) {
			int n = Integer.parseInt(br.readLine());
			List<Integer> codes = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean isComplete = true;
			
			for (int i = 0; i < 8; i++)
				codes.add(Integer.parseInt(st.nextToken()));
			
			while(isComplete) {
				for(int i = 1; i <= 5; i++) {
					int temp = codes.remove(0) - i;
					if (temp <= 0) {
						codes.add(0);
						isComplete = false;
						break;
					}
					codes.add(temp);
				}
			}
			
			
			System.out.print("#" + n + " ");
			for (int code: codes)
				System.out.print(code + " ");
			System.out.println();
		}
	}
}