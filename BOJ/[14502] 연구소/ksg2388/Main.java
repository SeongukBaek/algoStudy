import java.io.*;
import java.util.*;

public class Main {
	static int n, m, zeroNum, maxNum;
	static int[][] map;
	static Queue<Virus> queue = new LinkedList<>();
	static List<Index> zeroList = new ArrayList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 2) {
					queue.add(new Virus(i, j));
				}
				if (temp == 0) {
					zeroList.add(new Index(i, j));
				}
				map[i][j] = temp;
			}
		}
		zeroNum = zeroList.size() - 3;	// 3개는 벽이 되기 때문
		
		constructionWall(0, 0);
		System.out.println(maxNum);
	}

	public static void constructionWall(int depth, int start) {
		if (depth == 3) {
			int[][] copy = copyArray(map);
			maxNum = Math.max(maxNum, zeroNum - virusInfect(copy));
			return;
		}

		for (int i = start; i < zeroList.size(); i++) {
			Index index = zeroList.get(i);
				// 방문학적 없고 빈 공간이면
				if (map[index.x][index.y] == 0) {
					map[index.x][index.y] = 1;
					constructionWall(depth + 1, i + 1);
					map[index.x][index.y] = 0;
				}
		}
	}

	// BFS
	public static int virusInfect(int[][] arr) {
		Queue<Virus> testQueue = new LinkedList<>();
		testQueue.addAll(queue);
		int count = 0;

		Virus virus = null;
		while (!testQueue.isEmpty()) {
			virus = testQueue.poll();

			// 사방탐색하며 바이러스 전염
			for (int i = 0; i < 4; i++) {
				int nx = virus.x + dx[i];
				int ny = virus.y + dy[i];

				// 맵 밖으로 나가지 않고 비어있는 공간인 경우
				if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == 0) {
					testQueue.add(new Virus(nx, ny));
					arr[nx][ny] = 2;
				}
			}
			count++;
		}

		return count - queue.size();
	}

	public static int[][] copyArray(int[][] arr) {
		int temp[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}
}

class Virus {
	int x;
	int y;

	Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Index {
	int x;
	int y;

	Index(int x, int y) {
		this.x = x;
		this.y = y;
	}
}