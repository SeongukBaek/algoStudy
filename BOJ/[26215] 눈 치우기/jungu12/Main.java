import java.util.*;
import java.io.*;

public class Main {
	static Queue<Integer> snows = new PriorityQueue<>(Collections.reverseOrder());
	static int time;
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			snows.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	static void clean() {
		if(snows.peek() > 1440) {
			time = -1;
			return;
		}
		while(!snows.isEmpty()) {
			time++;
			cleanSnow();
		}
	}
	
	static void cleanSnow() {
		List<Integer> tmp = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			if(snows.isEmpty())
				break;
			int snow = snows.poll() -1;
			if(snow == 0)
				continue;
			tmp.add(snow);
		}
		snows.addAll(tmp);
	}
	
	static void pro() {
		clean();
		if(time>1440)
			time = -1;
		System.out.println(time);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
