import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		Queue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

		for (int count = 0; count < T; count++) {
			int number = Integer.parseInt(br.readLine());
			if (number == 0) {
				if (heap.isEmpty()) {
					answer.append(0);
				} else {
					answer.append(heap.poll());
				}
				answer.append("\n");
			}

			if (number > 0) {
				heap.add(number);
			}
		}

		System.out.print(answer);
	}
}