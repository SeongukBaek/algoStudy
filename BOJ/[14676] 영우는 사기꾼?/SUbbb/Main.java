import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final String NO_CHEAT = "King-God-Emperor";
	private static final String CHEAT = "Lier!";

	// 각 번호별 건설된 건물 수
	private static int[] buildings;
	// 각 번호별 진입 차수 저장
	private static int[] inDegrees;
	// 건물별 선행 관계 정보 저장
	private static final List<List<Integer>> buildingInfo = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");

		int N = Integer.parseInt(info[0]);
		int M = Integer.parseInt(info[1]);
		int K = Integer.parseInt(info[2]);

		buildings = new int[N];
		inDegrees = new int[N];
		for (int index = 0; index < N; index++) {
			buildingInfo.add(new ArrayList<>());
		}

		for (int index = 0; index < M; index++) {
			info = br.readLine().split(" ");

			int a = Integer.parseInt(info[0]) - 1;
			int b = Integer.parseInt(info[1]) - 1;

			buildingInfo.get(a).add(b);
			inDegrees[b]++;
		}

		String answer = NO_CHEAT;

		for (int index = 0; index < K; index++) {
			info = br.readLine().split(" ");

			int command = Integer.parseInt(info[0]);
			int building = Integer.parseInt(info[1]) - 1;

			if (isUsedCheat(command, building)) {
				answer = CHEAT;
				break;
			}
		}

		System.out.println(answer);
	}

	/**
	 * 해당 명령의 치트키 사용 여부 반환
	 * */
	private static boolean isUsedCheat(int command, int building) {
		if (command == 1) {
			if (!canBuiltBuilding(building)) {
				return true;
			}
		}
		if (command == 2) {
			if (!canDestroyBuilding(building)) {
				return true;
			}
		}

		return false;
	}

	private static boolean canBuiltBuilding(int building) {
		// 건설할 수 없다 == 현재 건물을 건설하기 위해 바로 이전에 건설해야 하는 건물을 모두 건설하지 않은 경우 == 진입 차수가 0이 아니다!
		if (inDegrees[building] != 0) {
			return false;
		}

		// 건설할 수 있다면 건설
		buildings[building]++;

		// 진입 차수 갱신은 해당 건물이 처음 세워졌을 경우에만 처리하면 되기에 나머지 경우는 제외!
		if (buildings[building] > 1) {
			return true;
		}

		// 해당 건물과 순서상 연결된 건물들의 진입 차수 - 1
		for (int next : buildingInfo.get(building)) {
			if (inDegrees[next] == 0) {
				continue;
			}
			inDegrees[next]--;
		}

		return true;
	}

	private static boolean canDestroyBuilding(int building) {
		// 파괴할 수 없다 == 건설되지 않은 건물이다.
		if (buildings[building] == 0) {
			return false;
		}

		// 파괴할 수 있다면 파괴
		buildings[building]--;

		// 만약 해당 건물이 0개인 경우만 다음 건물의 진입 차수 갱신
		if (buildings[building] != 0) {
			return true;
		}

		// 다음 건물의 진입 차수 + 1
		for (int next : buildingInfo.get(building)) {
			inDegrees[next]++;
		}

		return true;
	}
}