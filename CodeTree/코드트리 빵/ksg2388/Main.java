import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int x;
		int y;
		int startDirection;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Person {
		int x;
		int y;
		boolean isArrive;

		Person(int x, int y, boolean isArrive) {
			this.x = x;
			this.y = y;
			this.isArrive = isArrive;
		}
	}

	// 최단거리 알고리즘 구현 : 상좌우하 순서로 BFS를 이용하여 목표에 도착했을 때 가장 처음 움직인 위치가 어디였는지를 반환해줌.
	static int n, m, time, remindPersonCount;
	static Node[] store;
	static Person[] people;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		visited = new boolean[n][n];
		store = new Node[m];
		people = new Person[m];

		// 베이스 캠프 위치 초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 편의점 위치 초기화
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			store[i] = new Node(x, y);
		}

		start();
		System.out.println(time);
	}

	static void start() {
		remindPersonCount = m;

		while (remindPersonCount > 0) {
			// 아직 남은 사람이 있으면 1초씩 증가
			timePass();
		}
	}

	static void timePass() {
		time++;

		// 1. 격자에 있는 사람이 가고 싶은 편의점 방향을 향해서 1칸 움직인다.
		// 최단거리로 움직이는 방향을 선택한다.
		for (int i = 0; i < m; i++) {
			// 베이스캠프를 선택한 경우
			if (people[i] != null && !people[i].isArrive) {
				// 최적의 경로로 이동하는 방향 선택 후 이동
				int d = choiceOptimalPath(i, visited);
				people[i].x += dx[d];
				people[i].y += dy[d];

			}
		}

		// 2. 편의점에 도착한다면 해당 편의점에서 멈추고, 그 칸을 방문처리한다.
		for (int i = 0; i < m; i++) {
			if (people[i] != null && !people[i].isArrive && people[i].x == store[i].x && people[i].y == store[i].y) {
				visited[people[i].x][people[i].y] = true;
				people[i].isArrive = true;
				remindPersonCount--;
//				System.out.println("편의점 도착 시간 : " + time + " " + i + "번째");
			}
		}

		// 3. 현재 시간이 t분이고 t <= m을 만족한다면 베이스캠프로 들어간다. 이때 베이스캠프는 방문처리한다.
		if (time <= m) {
			setBaseCamp(time, visited);
		}
	}

	static void setBaseCamp(int num, boolean[][] visited) {
		boolean[][] newVisited = arrayCopy(visited);
		// 편의점 위치 저장
		Node storePosition = store[num - 1];

		// 베이스 캠프 배정 : 편의점에서 가장 가까운 베이스 캠프를 찾음 (bfs)
		Deque<Node> next = new LinkedList<>();
		next.offer(storePosition);
		
		while(!next.isEmpty()) {
			Node cur = next.poll();
			
			// 베이스 캠프에 도착한 경우
			if (map[cur.x][cur.y] == 1) {
				people[num - 1] = new Person(cur.x, cur.y, false);
				// 베이스 캠프 방문 처리
				visited[cur.x][cur.y] = true;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (isOutMap(nx, ny) || newVisited[nx][ny]) {
					continue;
				}
				
				next.offer(new Node(nx, ny));
				newVisited[nx][ny] = true;
			}
		}
	}

	static int choiceOptimalPath(int idx, boolean[][] visited) {
		boolean[][] newVisited = arrayCopy(visited);
		Deque<Node> next = new LinkedList<>();
		boolean isFirst = true;

		next.offer(new Node(people[idx].x, people[idx].y));

		while (!next.isEmpty()) {
			Node cur = next.poll();

			if (cur.x == store[idx].x && cur.y == store[idx].y) {
				return cur.startDirection;
			}

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (isOutMap(nx, ny) || newVisited[nx][ny]) {
					continue;
				}
				Node nextPoint = new Node(nx, ny);
				// 초기 이동의 경우 방향을 넣어둔다
				if (isFirst) {
					nextPoint.startDirection = i;
				} else {
					nextPoint.startDirection = cur.startDirection;
				}
				next.offer(nextPoint);
				newVisited[nx][ny] = true;
			}
			isFirst = false;
		}
		return -1;
	}

	static boolean[][] arrayCopy(boolean[][] arr) {
		boolean[][] temp = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = arr[i][j];
			}
		}

		return temp;
	}

	static boolean isOutMap(int x, int y) {
		return x < 0 || y < 0 || x >= n || y >= n;
	}
}