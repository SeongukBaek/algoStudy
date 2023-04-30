import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, size, left, right;
	static int[] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[100001];
		Arrays.fill(map, -1);
		
		System.out.println(findSister());
	}
	
	static int findSister() {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		map[N] = 0;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			if(current == K) {
				return map[K];
			}
			
			
			// 2*x
			int newIndex = 2*current;
			if(isValid(newIndex)) {
				queue.add(newIndex);
				map[newIndex] = map[current];
			}
			
			// x-1
			newIndex = current-1;
			if(isValid(newIndex)) {
				queue.add(newIndex);
				map[newIndex] = map[current]+1;
			}
			
			// x+1
			newIndex = current+1;
			if(isValid(newIndex)) {
				queue.add(newIndex);
				map[newIndex] = map[current]+1;
			}

		}

		return -1;
	}
	
	static boolean isValid(int newIndex) {
		if(0 <= newIndex && newIndex < map.length && map[newIndex] == -1) {
			return true;
		}
		return false;
	}
}
