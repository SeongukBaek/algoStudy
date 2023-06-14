import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static List<Integer>[] buildOrder;
	static int[] indegrees;
	static int[] buildings;
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(checkIsCheatKey());
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		buildOrder = new List[N + 1];
		indegrees = new int[N + 1];
		buildings = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			buildOrder[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			buildOrder[before].add(after);
			indegrees[after]++;
		}
	}
	
	private static String checkIsCheatKey() throws IOException {
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int buildingIdx = Integer.parseInt(st.nextToken());
			
			if(command == 1) {
				if(indegrees[buildingIdx] > 0) {
					return "Lier!";
				} else {
					buildings[buildingIdx]++;
					if(buildings[buildingIdx] == 1) {
						for(int nextBuilding : buildOrder[buildingIdx]) {
							indegrees[nextBuilding]--;
						}
					}
				}
			}
			
			if(command == 2) {
				if(buildings[buildingIdx] == 0) {
					return "Lier!";
				} else {
					buildings[buildingIdx]--;
					if(buildings[buildingIdx] == 0) {
						for(int nextBuilding : buildOrder[buildingIdx]) {
							indegrees[nextBuilding]++;
						}
					}
				}
			}
		}
		return "King-God-Emperor";
	}
}
