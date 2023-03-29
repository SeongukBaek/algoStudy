import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static List<List<Position>> moveLines = new ArrayList<>();
	static List<Position> moveLine = new ArrayList<>();
	static List<Integer> teamSize = new ArrayList<>();
	static Member[][] board;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Member {
		int teamIdx;
		int type;

		Member(int type) {
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new Member[N][N];
		visited = new boolean[N][N];
		int score = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = new Member(Integer.parseInt(st.nextToken()));
			}
		}

		// 팀 별 이동선들의 좌표 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// List에 머리부터 저장함
				if (board[i][j].type == 1) {
					moveLine = new ArrayList<>();
					findMoveLine(i, j);
					moveLines.add(moveLine);
				}
			}
		}

		// board에 팀Idx 정보 추가
		for (int i = 0; i < M; i++) {
			List<Position> moveLine = moveLines.get(i);
			for (int j = 0; j < moveLine.size(); j++) {
				Position cur = moveLine.get(j);
				board[cur.x][cur.y].teamIdx = i + 1;
			}
		}

		for (int i = 0; i < K; i++) {
			moveAll();
			score += shootBall(i);
		}

		System.out.println(score);
	}

	private static void findMoveLine(int i, int j) {
		moveLine.add(new Position(i, j));
		visited[i][j] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			if (!isIn(nx, ny)) {
				continue;
			}
			if (board[nx][ny].type == 0 || visited[nx][ny]) {
				continue;
			}

			// 머리 -> 몸통 -> 꼬리 순으로 저장하기 위해 moveLine에 머리만 있는 경우, 몸통이 나올 때 까지 스킵
			if (moveLine.size() == 1 && board[nx][ny].type != 2) {
				continue;
			}

			// 머리부터 꼬리까지의 moveLine의 size가 팀의 인원수. 해당 값을 저장.
			if (board[nx][ny].type == 3) {
				teamSize.add(moveLine.size() + 1);
			}

			findMoveLine(nx, ny);
		}
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}

	private static void moveAll() {
		for (int i = 0; i < M; i++) {
            //현재 팀의 이동 선
			List<Position> currentLine = moveLines.get(i);
            //머리의 좌표
			Position headPos = currentLine.get(currentLine.size() - 1);
			for (int j = currentLine.size() - 1; j > 0; j--) {
				currentLine.set(j, currentLine.get(j - 1));
			}
			currentLine.set(0, headPos);
			moveLines.set(i, currentLine);

            //변경한 좌표 정보를 바탕으로 board에도 갱신
			int length = teamSize.get(i);
			for (int j = 0; j < currentLine.size(); j++) {
				Position current = currentLine.get(j);
				if (j == 0) {
					board[current.x][current.y].type = 1;
					continue;
				}
				if (j == length - 1) {
					board[current.x][current.y].type = 3;
					continue;
				}
				if (j >= length) {
					board[current.x][current.y].type = 4;
					continue;
				}
				board[current.x][current.y].type = 2;
			}
		}
	}

	private static int shootBall(int round) {
		round %= (4 * N);
		round += 1;
		if (round <= N) {
			for (int i = 0; i < N; i++) {
				if (board[round - 1][i].teamIdx != 0 && board[round - 1][i].type != 4) {
					return getScore((round - 1) % N, i);
				}
			}
		} else if (round <= 2 * N) {
			for (int i = N - 1; i >= 0; i--) {
				if (board[i][(round - 1) % N].teamIdx != 0 && board[i][(round - 1) % N].type != 4) {
					return getScore(i, (round - 1) % N);
				}
			}
		} else if (round <= 3 * N) {
			for (int i = N - 1; i >= 0; i--) {
				if (board[N - ((round - 1) % N) - 1][i].teamIdx != 0 && board[N - ((round - 1) % N) - 1][i].type != 4) {
					return getScore(N - (round - 1) % N - 1, i);
				}
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (board[i][N - ((round - 1) % N) - 1].teamIdx != 0 && board[i][N - ((round - 1) % N) - 1].type != 4) {
					return getScore(i, N - ((round - 1) % N) - 1);
				}
			}
		}
		return 0;
	}

	private static int getScore(int x, int y) {
        //현재 팀의 이동 선
		List<Position> currentLine = moveLines.get(board[x][y].teamIdx - 1);
        //이동 선을 따라가며, 공을 맞은 사람과 같은 좌표를 찾아낸다.
		for (int cnt = 0; cnt < currentLine.size(); cnt++) {
			Position current = currentLine.get(cnt);
			if (x == current.x && y == current.y) {
                //좌표를 찾아낸다면 reverse해준다.
				reverse(board[x][y].teamIdx);
				return (cnt + 1) * (cnt + 1);
			}
		}
		return 0;
	}

	private static void reverse(int teamIdx) {
        //현재 팀의 인원 수
		int currentTeamSize = teamSize.get(teamIdx - 1);
        //현재 팀의 이동 선
		List<Position> currentLine = moveLines.get(teamIdx - 1);
        //현재 팀의 이동 선 길이
		int currentLineSize = currentLine.size();

        //좌표 정보 갱신을 위해 복사한 List
		List<Position> tmp = new ArrayList<>();
		for (Position current : currentLine) {
			tmp.add(current);
		}

        //꼬리부터 머리까지 좌표 순차적으로 저장
		for (int i = currentTeamSize - 1, idx = 0; i >= 0; i--, idx++) {
			currentLine.set(idx, tmp.get(i));
		}

        //바뀐 꼬리의 다음 칸부터 바뀐 머리가 나오기 전까지 좌표 순차적으로 저장
		for (int i = currentLineSize - 1, idx = currentTeamSize; i >= currentTeamSize; i--, idx++) {
			currentLine.set(idx, tmp.get(i));
		}

        //변경한 좌표 정보를 바탕으로 board에도 갱신
		for (int j = 0; j < currentLine.size(); j++) {
			Position current = currentLine.get(j);
			if (j == 0) {
				board[current.x][current.y].type = 1;
				continue;
			}
			if (j == currentTeamSize - 1) {
				board[current.x][current.y].type = 3;
				continue;
			}
			if (j >= currentTeamSize) {
				board[current.x][current.y].type = 4;
				continue;
			}
			board[current.x][current.y].type = 2;
		}
	}
}