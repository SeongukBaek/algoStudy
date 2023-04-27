import java.util.*;
import java.io.*;

public class Main {
	static final int MAX_SIZE = 100000;
	static int target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int subin = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		if (target <= subin) {
			System.out.println(subin - target);
			return;
		}

		System.out.println(getTime(subin));
	}

	private static int getTime(int start) {
		Deque<Integer> positions = new ArrayDeque<>();
		boolean[] visited = new boolean[MAX_SIZE+1];
		int time = 0;
		if(warp(start, visited, positions)) {
			return time;
		}

		while (!positions.isEmpty()) {
			int parentSize = positions.size();
			time++;

			while (parentSize-- > 0) {
				int cur = positions.poll();

                // X-1 이동
				int back = cur - 1;
                // X+1 이동
				int go = cur + 1;

				if (back > 0) {
					if(warp(back, visited, positions)) {
						return time;
					}
				}

				if (go <= 100000) {
					if(warp(go, visited, positions)) {
						return time;
					}
				}
			}
		}
		return -1;
	}
	
	// 2*X로 순간이동하기
	private static boolean warp(int cur, boolean[] visited, Deque<Integer> positions) {
		for (int i = cur; i <= MAX_SIZE; i *= 2) {
			if (i == target) {
				return true;
			}
			if (visited[i]) {
				break;
			}
			visited[i] = true;
			positions.add(i);
		}
		return false;
	}
	
}