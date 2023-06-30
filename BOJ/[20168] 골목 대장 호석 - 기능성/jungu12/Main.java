package eclipse_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_20168 {
	static int N, M, A, B, C;
	static List<int[]>[] roads;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		init();
		findMinCost();
        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		roads = new List[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			roads[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			roads[start].add(new int[] { end, cost });
			roads[end].add(new int[] { start, cost });
		}
	}

	private static void findMinCost() {
		visited[A] = true;
		goRoad(A, 0, 0);
	}

	private static void goRoad(int start, int max, int sum) {
		if(sum > C) {
			return;
		}
		if(start == B) {
		answer = Math.min(max, answer);
		return;
	}
		for (int[] destination : roads[start]) {
			if (!visited[destination[0]]) {
				visited[destination[0]] = true;
				goRoad(destination[0], Math.max(max, destination[1]), sum + destination[1]);
				visited[destination[0]] = false;
			}
		}
	}
}
