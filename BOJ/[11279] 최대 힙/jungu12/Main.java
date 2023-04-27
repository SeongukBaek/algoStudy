import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		
		int N = Integer.parseInt(br.readLine());
		int x;
		for(int i = 0; i < N; i++) {
			x = Integer.parseInt(br.readLine());
			if(x != 0) {
				pq.add(x);
				continue;
			}
			if(!pq.isEmpty()) {
				System.out.println(pq.poll());
				continue;
			}
			System.out.println(0);
		}
	}
}