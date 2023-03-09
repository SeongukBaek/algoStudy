import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static class Board {
		int sharkIndex;
		int smellTime;

		Board(int sharkIndex, int smellTime) {
			this.sharkIndex = sharkIndex;
			this.smellTime = smellTime;
		}
	}

	static class Shark {
		int index;
		int x;
		int y;
		int direction;
		int[][] order;
		Shark(int index, int x, int y) {
			this.index = index;
			this.x = x;
			this.y = y;
			order = new int[4][4];
		}

		void setOrder(int direction, int[] order) {
			this.order[direction] = order;
		}
	}

	private static Board[][] board;
	private static int N;
	private static int k;
	private static List<Shark> sharks;
	private static Queue<Shark> movedSharks;
	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		sharks = new ArrayList<>();

		board = new Board[N][N];

		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; y++) {
				int number = Integer.parseInt(st.nextToken());
				if (number != 0) {
					// 처음에 바로 냄새 뿌리기
					board[x][y] = new Board(number, k);
					sharks.add(new Shark(number, x, y));
				}
			}
		}

		// 상어를 번호대로 정렬
		sharks.sort(Comparator.comparingInt(o -> o.index));

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < M; index++) {
			sharks.get(index).direction = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int index = 0; index < M; index++) {
			for (int order = 0; order < 4; order++) {
				sharks.get(index).setOrder(order, Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(number -> number - 1).toArray());
			}
		}

		int time = 0;
		while (sharks.size() > 1 && time <= 1000) {
			moveSharks();
			decreaseSmells();
			spreadSmells();
			time++;
		}

		if (time > 1000) {
			time = -1;
		}

		System.out.println(time);
	}

	/**
	 * 상어를 번호대로 이동시킨다.
	 * 먼저 아무 냄새가 없는 칸을 찾고, 없다면 자신의 냄새가 있는 칸으로 이동하는데, 이때 탐색 방향을 상어의 이동 우선순위 방향으로 정한다.
	 * 이후 상어가 이동할 좌표를 번호기준 오름차순인 우선순위 큐에 넣어, 냄새를 뿌리고 상어를 제거하기 위해 사용한다.
	 * */
	private static void moveSharks() {
		movedSharks = new PriorityQueue<>((Comparator.comparingInt(o -> o.index)));

		for (Shark shark : sharks) {
			int x = shark.x;
			int y = shark.y;
			int d = shark.direction;

			boolean hasSelect = false;
			for (int direction : shark.order[d]) {
				int nx = x + directions[direction][0];
				int ny = y + directions[direction][1];

				if (!isInBoard(nx, ny) || board[nx][ny] != null) {
					continue;
				}

				// 아무 냄새가 없는 칸을 찾은 경우
				x = nx;
				y = ny;
				d = direction;
				hasSelect = true;
				break;
			}

			// 아직 이동할 칸을 못 찾은 경우
			if (!hasSelect) {
				for (int direction : shark.order[d]) {
					int nx = x + directions[direction][0];
					int ny = y + directions[direction][1];

					if (!isInBoard(nx, ny) || board[nx][ny] == null || board[nx][ny].sharkIndex != shark.index) {
						continue;
					}

					x = nx;
					y = ny;
					d = direction;
					break;
				}
			}

			// 상어 이동
			shark.x = x;
			shark.y = y;
			shark.direction = d;

			// 큐에 넣어두고, 냄새를 뿌릴 때 만약 동일한 위치에 상어가 여러 마리 있는 경우 없애줌
			// 상어 번호를 기준으로 오름차순 정렬
			movedSharks.add(shark);
		}
	}

	/**
	 * 이동한 상어들을 저장한 큐에서 상어 하나씩 꺼내서, 냄새를 뿌린다.
	 * 이때, 번호가 작은 상어들부터 꺼내기에, 이미 다른 상어가 현재 뿌리려는 자리에 냄새를 뿌린 경우, 현재 상어를 제거한다.
	 * */
	private static void spreadSmells() {
		while (!movedSharks.isEmpty()) {
			Shark currentShark = movedSharks.poll();
			int x = currentShark.x;
			int y = currentShark.y;

			// 이미 다른 상어가 냄새를 뿌린 경우
			// 이 상어는 격자 밖으로 퇴출
			if (board[x][y] != null && board[x][y].sharkIndex != currentShark.index) {
				sharks = sharks.stream().filter(shark -> shark.index != currentShark.index).collect(Collectors.toList());
				continue;
			}

			board[x][y] = new Board(currentShark.index, k);
		}
	}

	/**
	 * 사라져야 할 냄새를 제거
	 * */
	private static void decreaseSmells() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (board[x][y] == null) {
					continue;
				}
				if (board[x][y].smellTime >= 1) {
					board[x][y].smellTime--;
				}
				if (board[x][y].smellTime == 0) {
					board[x][y] = null;
				}
			}
		}
	}

	private static boolean isInBoard(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}