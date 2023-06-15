import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K; // 건물 종류, 관계, 게임 정보
	static List<Integer>[] buildings;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		inDegree = new int[N + 1];
		buildings = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			buildings[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			inDegree[e]++;
			buildings[s].add(e);
		}

		int[] builted = new int[N + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (order == 1) {
				// 영향을 주는 건물이 건설되지 않았을 경우
				if (inDegree[num] > 0) {
					System.out.println("Lier!");
					return;
				}
				// 처음 짓는 건물일 경우
				if (builted[num] == 0) {
					for (int nxt : buildings[num]) {
						inDegree[nxt]--;
					}
				}
				builted[num]++;
				continue;
			}
			// 지어진 건물이 없는 경우
			if (builted[num] < 1) {
				System.out.println("Lier!");
				return;
			}
			builted[num]--;
			// 해당 번호 건물이 다 무너졌을 경우
			if (builted[num] == 0) {
				for (int nxt : buildings[num]) {
					inDegree[nxt]++;
				}
			}
		}
		System.out.println("King-God-Emperor");
	}

}
