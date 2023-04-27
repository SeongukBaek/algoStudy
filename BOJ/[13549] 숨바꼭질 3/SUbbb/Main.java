import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int TARGET = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] minTimes = new int[100001];

		Arrays.fill(minTimes, Integer.MAX_VALUE);

		Queue<Integer> canReach = new LinkedList<>();
		canReach.add(N);
		minTimes[N] = 0;

		while (!canReach.isEmpty()) {
			int currentLocation = canReach.poll();

			if (currentLocation == K) {
				break;
			}

			// 현재 지점까지 가는 거리
			int prevCost = minTimes[currentLocation];

			// -1로 이동 가능하고, 현재 지점에서 -1 이동한 좌표로의 비용이 갱신되는 경우
			if (currentLocation - 1 >= 0 && prevCost + 1 < minTimes[currentLocation - 1]) {
				minTimes[currentLocation - 1] = prevCost + 1;
				canReach.add(currentLocation - 1);
			}
			// +1로 이동 가능하고, 현재 지점에서 +1 이동한 좌표로의 비용이 갱신되는 경우
			if (currentLocation + 1 <= TARGET && prevCost + 1 < minTimes[currentLocation + 1]) {
				minTimes[currentLocation + 1] = prevCost + 1;
				canReach.add(currentLocation + 1);
			}
			// *2로 이동 가능하고, 현재 지점에서 *2 이동한 좌표로의 비용이 갱신되는 경우
			if (currentLocation * 2 <= TARGET && prevCost < minTimes[currentLocation * 2]) {
				minTimes[currentLocation * 2] = prevCost;
				canReach.add(currentLocation * 2);
			}
		}

		System.out.println(minTimes[K]);
	}
}