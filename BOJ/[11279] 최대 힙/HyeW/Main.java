import java.util.*;
import java.io.*;

public class Main {
	static Queue<Integer> maxHeap;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
		
		for(int i = 0; i < n; i++) {
			int v = Integer.parseInt(br.readLine());
			if(v == 0) {
				getMaxValue();
			}
			maxHeap.add(v);
		}
		System.out.println(sb);
	}

	private static void getMaxValue() {
		if(maxHeap.size() == 0) {
			sb.append(0).append("\n");
			return;
		}
		sb.append(maxHeap.poll()).append("\n");
	}
}