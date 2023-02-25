import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static class Location {
		int x;
		int y;
		Location (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static char[][] map;
	private static int R;
	private static int C;
	private static Location empty;
	private static final Map<Character, List<Integer>> pipeInfo = new HashMap<>();
	private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static final StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		List<Location> pipes = new ArrayList<>();
		initialize();

		for (int x = 0; x < R; x++) {
			String line = br.readLine();
			for (int y = 0; y < C; y++) {
				char current = line.charAt(y);
				map[x][y] = current;
				if (isGasPipe(current)) {
					pipes.add(new Location(x, y));
				}
			}
		}

		// 각 가스관의 사방을 확인하면서, 이어지지 않은 곳이 있는지 확인
		// 이어지지 않은 곳은 empty에 저장
		for (Location pipe : pipes) {
			if (findEmpty(pipe)) {
				break;
			}
		}

		flowFromEmpty();

		System.out.println(result);
	}

	/**
	 * 각 가스관 모양별로 확인해야 하는 방향을 저장
	 * */
	private static void initialize() {
		pipeInfo.put('|', Arrays.asList(0, 2));
		pipeInfo.put('-', Arrays.asList(1, 3));
		pipeInfo.put('+', Arrays.asList(0, 1, 2, 3));
		pipeInfo.put('1', Arrays.asList(1, 2));
		pipeInfo.put('2', Arrays.asList(0, 1));
		pipeInfo.put('3', Arrays.asList(0, 3));
		pipeInfo.put('4', Arrays.asList(2, 3));
	}

	/**
	 * 주어진 가스관이 확인해야 하는 위치를 확인한 후, 모두 연결되었는지 여부 반환
	 * */
	private static boolean findEmpty(Location pipe) {
		int x = pipe.x;
		int y = pipe.y;
		char shape = map[x][y];

		List<Integer> searchRange = pipeInfo.get(shape);

		for (int direction : searchRange) {
			int nx = x + directions[direction][0];
			int ny = y + directions[direction][1];

			if (map[nx][ny] == '.') {
				empty = new Location(nx, ny);
				result.append(nx + 1).append(" ").append(ny + 1).append(" ");
				return true;
			}
		}

		return false;
	}

	/**
	 * 해커가 훔쳐간 파이프 위치에서 사방 탐색을 수행한다.
	 * 사방에 연결될 수 있는 가스관이 있는 방향을 저장하고, 해당 방향으로 연결할 수 있는 가스관을 찾는다.
	 * */
	private static void flowFromEmpty() {
		List<Integer> nearPipes = new ArrayList<>();

		for (int direction = 0; direction < 4; direction++) {
			int nx = empty.x + directions[direction][0];
			int ny = empty.y + directions[direction][1];

			if (!isInBoundary(nx, ny)) {
				continue;
			}

			if (isGasPipe(map[nx][ny]) && canConnect(nx, ny, direction)) {
				nearPipes.add(direction);
			}
		}

		// 가스관 정보를 탐색하면서 현재 저장한 연결해야 하는 방향과 일치하는 가스관을 찾는다.
		for (Map.Entry<Character, List<Integer>> entry : pipeInfo.entrySet()) {
			if (entry.getValue().equals(nearPipes)) {
				result.append(entry.getKey());
			}
		}
	}

	/**
	 * 해커가 훔쳐간 가스관과 주어진 좌표의 가스관을 연결할 수 있는지 여부를 반환
	 * @param x 주어진 x 좌표
	 * @param y 주어진 y 좌표
	 * @param direction 현재 확인하려는 방향
	 * */
	private static boolean canConnect(int x, int y, int direction) {
		int reversedDirection = reverseDirection(direction);

		for (int connect : pipeInfo.get(map[x][y])) {
			if (connect == reversedDirection) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 주어진 방향의 정반대 방향을 반환
	 * @param direction 반대 방향을 구하려는 방향
	 * @return int
	 * */
	private static int reverseDirection(int direction) {
		if (direction < 2) {
			return direction + 2;
		}
		return direction - 2;
	}

	private static boolean isInBoundary(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

	private static boolean isGasPipe(char info) {
		return info != 'M' && info != 'Z' && info != '.';
	}
}