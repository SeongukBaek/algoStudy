import java.util.*;
import java.io.*;

public class Main {
	static Queue<Integer> startTimes = new PriorityQueue<>();
	static Queue<Integer> endTimes = new PriorityQueue<>();
	static int result;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			startTimes.add(Integer.parseInt(st.nextToken()));
			endTimes.add(Integer.parseInt(st.nextToken()));
		}
	}

	static void pro() {
		Queue<Integer> lecture = new PriorityQueue<>();
		lecture.add(endTimes.poll());
		startTimes.poll();
		int size = startTimes.size();
		for(int i = 0 ; i < size; i ++) {
			while(!startTimes.isEmpty() && !lecture.isEmpty()) {
				if(lecture.peek() <= startTimes.poll())
					lecture.poll();
			}
			lecture.add(endTimes.poll());
			if(lecture.size() > result)
				result = lecture.size();
		}
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}