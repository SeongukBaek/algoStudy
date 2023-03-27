import java.io.*;
import java.util.*;

public class CodeTreeBread {
	static int n, m;
	static int[][] map;
	static List<Position> baseCamps;
	static List<Person> people;
	static int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		baseCamps = new ArrayList<>();
		for (int x = 0; x < n; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < n; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				if (map[x][y] == 1) {
					baseCamps.add(new Position(x, y));
				}
			}
		}

		people = new ArrayList<>();
		for (int mart = 0; mart < m; mart++) {
			st = new StringTokenizer(br.readLine());
			people.add(new Person(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
		}

		int time = 0;
		int arrivedPerson = 0;
		// 모든 사람이 도착할때까지 반복
		while (arrivedPerson < m) {
			// 1. 격자판 위에 있는 모든 사람들 1칸 움직임
			int arriveCnt = move();
			arrivedPerson += arriveCnt;

			// 2. 편의점 도착 -> 다른 사람들 칸을 지날 수 없음
				//이때 map에 변화줘야 함
			for(Person person : people) {
				if(!person.isArrived) {
					continue;
				}
				map[person.mart.x][person.mart.y] = -1;
			}
			
			// 3. 격자 박에 있는 사람은 베이스캠프에 들어감 
			//(t분에 t사람이 베캠에 있을 수 있음 -> 그 이후로 다른 사람 못지나감)
			if(time < m) {
				getCloseCamp(people.get(time));
			}
			time++;
		}
		
		System.out.println(time);
	}
	
	private static int move() {
		int arriveCnt = 0;
		
		for(int index = 0; index < people.size(); index++) {
			Person person = people.get(index);
			if(person.isArrived) {
				continue;
			}
			
			//사람들 한 칸씩 이동
			Deque<Position> path = people.get(index).curPosition;
			int parent = path.size();
			
			while (parent-- > 0 && !person.isArrived) {
				Position cur = path.poll();

				for (int i = 0; i < 4; i++) {
					int dx = cur.x + dir[i][0];
					int dy = cur.y + dir[i][1];

					if (!arrayBoundsValidation(dx, dy)) {
						continue;
					}
					if (person.visited[dx][dy] || map[dx][dy] == -1) {
						continue;
					}

					if(dx == person.mart.x && dy == person.mart.y) {
						person.isArrived = true;
						arriveCnt++;
						break;
					}

					path.add(new Position(dx, dy));
					person.visited[dx][dy] = true;
				}
			}
		}
		
		return arriveCnt;
	}

	private static void getCloseCamp(Person person) {
		Deque<Position> path = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		visited[person.mart.x][person.mart.y] = true;
		path.add(person.mart);

		while (!path.isEmpty()) {
			Position cur = path.poll();

			for (int i = 0; i < 4; i++) {
				int dx = cur.x + dir[i][0];
				int dy = cur.y + dir[i][1];

				if (!arrayBoundsValidation(dx, dy)) {
					continue;
				}
				if (visited[dx][dy] || map[dx][dy] == -1) {
					continue;
				}

				if (map[dx][dy] == 1) {
					map[dx][dy] = -1;
					person.curPosition.add(new Position(dx, dy));
					person.visited = new boolean[n][n];
					person.visited[dx][dy] = true;
					return;
				}

				path.add(new Position(dx, dy));
				visited[dx][dy] = true;
			}
		}

	}

	private static boolean arrayBoundsValidation(int x, int y) {
		return (x >= 0 && y >= 0 && x < n && y < n);
	}

	static class Person {
		Position mart;
		boolean isArrived = false;
		Deque<Position> curPosition = new ArrayDeque<>();
		boolean[][] visited;

		public Person(int x, int y) {
			this.mart = new Position(x, y);
		}
	}

	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}