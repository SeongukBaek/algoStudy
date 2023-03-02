import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] map;
	static Pos mos;
	static Pos zag;
	static Pos deletedBlockPos;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int x;
		int y;
		int dir;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Pos(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == 'M') {
					mos = new Pos(i, j);
				}
				if (map[i][j] == 'Z') {
					zag = new Pos(i, j);
				}
			}
		}
	}

    //블럭이 없어진 곳의 좌표를 찾는 method. 파이프 관을 따라가다가 .이 나온다면 여기가 블럭이 없어진 곳. 
	static void findDeletedBlockPos(Pos pos) {
		Pos nextPos = findNextPos(pos);
        //현재 pos가 모스크바면 다른 pos와 다른 task 수행
		if (pos == mos) {
			nextPos = findNextPosAtMos(mos);
            //findNextPos()에서 더이상 이동할 곳이 없다면 현재 좌표를 반환
			if (nextPos == pos) {
				deletedBlockPos = new Pos(nextPos.x, nextPos.y, nextPos.dir);
				return;
			}
		} else {
			if (map[nextPos.x][nextPos.y] == '.') {
				deletedBlockPos = new Pos(nextPos.x, nextPos.y, nextPos.dir);
				return;
			}
		}
		findDeletedBlockPos(nextPos);
	}

	static Pos findNextPos(Pos pos) {
		Pos nextPos = null;
		if (map[pos.x][pos.y] == '|') {
			if (pos.dir == 0) {
				nextPos = new Pos(pos.x - 1, pos.y, 0);
			}
			if (pos.dir == 1) {
				nextPos = new Pos(pos.x + 1, pos.y, 1);
			}
		}
		if (map[pos.x][pos.y] == '+') {
			if (pos.dir == 0) {
				nextPos = new Pos(pos.x - 1, pos.y, 0);
			}
			if (pos.dir == 1) {
				nextPos = new Pos(pos.x + 1, pos.y, 1);
			}
			if (pos.dir == 2) {
				nextPos = new Pos(pos.x, pos.y - 1, 2);
			}
			if (pos.dir == 3) {
				nextPos = new Pos(pos.x, pos.y + 1, 3);
			}
		}
		if (map[pos.x][pos.y] == '-') {
			if (pos.dir == 2) {
				nextPos = new Pos(pos.x, pos.y - 1, 2);
			}
			if (pos.dir == 3) {
				nextPos = new Pos(pos.x, pos.y + 1, 3);
			}
		}
		if (map[pos.x][pos.y] == '1') {
			if (pos.dir == 0) {
				nextPos = new Pos(pos.x, pos.y + 1, 3);
			}
			if (pos.dir == 2) {
				nextPos = new Pos(pos.x + 1, pos.y, 1);
			}
		}
		if (map[pos.x][pos.y] == '2') {
			if (pos.dir == 1) {
				nextPos = new Pos(pos.x, pos.y + 1, 3);
			}
			if (pos.dir == 2) {
				nextPos = new Pos(pos.x - 1, pos.y, 0);
			}
		}
		if (map[pos.x][pos.y] == '3') {
			if (pos.dir == 1) {
				nextPos = new Pos(pos.x, pos.y - 1, 2);
			}
			if (pos.dir == 3) {
				nextPos = new Pos(pos.x - 1, pos.y, 0);
			}
		}
		if (map[pos.x][pos.y] == '4') {
			if (pos.dir == 0) {
				nextPos = new Pos(pos.x, pos.y - 1, 2);
			}
			if (pos.dir == 3) {
				nextPos = new Pos(pos.x + 1, pos.y, 1);
			}
		}
		return nextPos;
	}

	static Pos findNextPosAtMos(Pos pos) {
		for (int i = 0; i < 4; i++) {
			Pos nextPos = new Pos(pos.x + dx[i], pos.y + dy[i]);
			if (nextPos.x < 0 || nextPos.x >= R || nextPos.y < 0 || nextPos.y >= C
					|| map[nextPos.x][nextPos.y] == '.') {
				continue;
			}
			if (canMoveUp(i, nextPos) || canMoveDown(i, nextPos) || canMoveLeft(i, nextPos)
					|| canMoveRight(i, nextPos)) {
				return new Pos(nextPos.x, nextPos.y, i);
			}
		}
		// 더이상 이동할 파이프가 없다면 현재 좌표를 return
		return pos;

	}

	static boolean canMoveUp(int dir, Pos curPos) {
		if (dir == 0 && map[curPos.x][curPos.y] == '|' || map[curPos.x][curPos.y] == '+'
				|| map[curPos.x][curPos.y] == '1' || map[curPos.x][curPos.y] == '4') {
			return true;
		}
		return false;
	}

	static boolean canMoveDown(int dir, Pos curPos) {
		if (dir == 1 && map[curPos.x][curPos.y] == '|' || map[curPos.x][curPos.y] == '+'
				|| map[curPos.x][curPos.y] == '2' || map[curPos.x][curPos.y] == '3') {
			return true;
		}
		return false;
	}

	static boolean canMoveLeft(int dir, Pos curPos) {
		if (dir == 2 && map[curPos.x][curPos.y] == '-' || map[curPos.x][curPos.y] == '+'
				|| map[curPos.x][curPos.y] == '1' || map[curPos.x][curPos.y] == '4') {
			return true;
		}
		return false;
	}

	static boolean canMoveRight(int dir, Pos curPos) {
		if (dir == 3 && map[curPos.x][curPos.y] == '-' || map[curPos.x][curPos.y] == '+'
				|| map[curPos.x][curPos.y] == '2' || map[curPos.x][curPos.y] == '3') {
			return true;
		}
		return false;
	}

    //사방탐색하여 현재 위치에 올 수 없는 블럭들은 remove
	static void findDeletedBlockType() {
		Set<Character> pipes = new HashSet<>(Arrays.asList('|', '-', '+', '1', '2', '3', '4'));
		for (int i = 0; i < 4; i++) {
			Pos nextPos = new Pos(deletedBlockPos.x + dx[i], deletedBlockPos.y + dy[i]);
			if (nextPos.x < 0 || nextPos.x >= R || nextPos.y < 0 || nextPos.y >= C) {
				continue;
			}
			if (map[nextPos.x][nextPos.y] == '.' || map[nextPos.x][nextPos.y] == 'M') {
				pipes.remove('+');
			}
			if (i == 0 && (map[nextPos.x][nextPos.y] == '|' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '1' || map[nextPos.x][nextPos.y] == '4')) {
				pipes.remove('1');
				pipes.remove('4');
				pipes.remove('-');
			} if (i == 1 && (map[nextPos.x][nextPos.y] == '|' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '2' || map[nextPos.x][nextPos.y] == '3')) {
				pipes.remove('2');
				pipes.remove('3');
				pipes.remove('-');
			} if (i == 2 && (map[nextPos.x][nextPos.y] == '-' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '1' || map[nextPos.x][nextPos.y] == '2')) {
				pipes.remove('|');
				pipes.remove('1');
				pipes.remove('2');
			}
			if (i == 3 && (map[nextPos.x][nextPos.y] == '-' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '3' || map[nextPos.x][nextPos.y] == '4')) {
				pipes.remove('|');
				pipes.remove('3');
				pipes.remove('4');
			}
		}
        //파이프가 없어진 pos가 자그레브와 인접한 경우
		if(pipes.size() == 0) {
			for(int i = 0 ; i < 4; i++) {
				Pos nextPos = new Pos(deletedBlockPos.x + dx[i], deletedBlockPos.y + dy[i]);
				if(map[nextPos.x][nextPos.y] == 'Z') {
					if(i == 0) {
						if(deletedBlockPos.dir == 0) {
							pipes.add('|');
						}
						if(deletedBlockPos.dir == 2) {
							pipes.add('2');
						}
						if(deletedBlockPos.dir == 3) {
							pipes.add('3');
						}
					}
					if(i == 1) {
						if(deletedBlockPos.dir == 1) {
							pipes.add('|');
						}
						if(deletedBlockPos.dir == 2) {
							pipes.add('1');
						}
						if(deletedBlockPos.dir == 3) {
							pipes.add('4');
						}
					}
					if(i == 2) {
						if(deletedBlockPos.dir == 2) {
							pipes.add('-');
						}
						if(deletedBlockPos.dir == 1) {
							pipes.add('3');
						}
						if(deletedBlockPos.dir == 0) {
							pipes.add('4');
						}
					}
					if(i == 3) {
						if(deletedBlockPos.dir == 3) {
							pipes.add('-');
						}
						if(deletedBlockPos.dir == 1) {
							pipes.add('2');
						}
						if(deletedBlockPos.dir == 0) {
							pipes.add('1');
						}
					}
				}
			}
		}
		sb.append(pipes.iterator().next());
	}

	public static void main(String[] args) throws IOException {
		input();
		findDeletedBlockPos(findNextPosAtMos(mos));
		sb.append(deletedBlockPos.x + 1).append(" ").append(deletedBlockPos.y + 1).append(" ");
		findDeletedBlockType();
		System.out.println(sb);
	}
}