package practice.boj;

import java.io.*;
import java.util.*;

public class boj26215 {
	private static int n;
	private static int time;
	private static Queue<Integer> snows = new PriorityQueue<>(Collections.reverseOrder());

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 1440보다 큰 값이 들어오는 경우 종료
			if (num > 1440) {
				System.out.println(-1);
				return;
			}
			snows.add(num);
		}

		while (snows.size() > 1) {
			cleanSnow();
		}

		if (snows.size() > 0)
			time += snows.poll();

		if (time > 1440) {
			System.out.println(-1);
			return;
		}
		System.out.println(time);
	}
	
	private static void cleanSnow() {
		int max = snows.poll() - 1;
		int secondMax = snows.poll() - 1;
		
		if (max > 0)
			snows.add(max);
		if (secondMax > 0)
			snows.add(secondMax);
		time++;
	}
}
