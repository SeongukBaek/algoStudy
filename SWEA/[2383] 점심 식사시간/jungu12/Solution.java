import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static final int MAX_MOVE = 3;
	static int N;
	static int[][] room;
	static List<Person> people;
	static List<Stair> stairs;
	static int min, peopleNum;
	static boolean[] useStair1;
	static List<Integer> peopleUsingStair1;
	static List<Integer> peopleUsingStair2;

	static class Person {
		int x;
		int y;
		int usingStair;
		int distance;

		Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Stair {
		int x;
		int y;
		int length;

		Stair(int x, int y, int length) {
			this.x = x;
			this.y = y;
			this.length = length;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			room = new int[N][N];
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
					if (room[i][j] == 0) {
						continue;
					}
					if (room[i][j] == 1) {
						people.add(new Person(i, j));
					}
					if (room[i][j] > 1) {
						stairs.add(new Stair(i, j, room[i][j]));
					}
				}
			}
			peopleNum = people.size();
			useStair1 = new boolean[peopleNum];
			selectStairAndMove(0, peopleNum);
			System.out.println("#" + tc + " " + min);
		}
	}

	private static void selectStairAndMove(int depth, int peopleNum) {
		if (peopleNum == depth) {
			divideByStairAndCalDistance();
			min = Math.min(min, calFinishTime());
			return;
		}

		Person currentPerson = people.get(depth);
		currentPerson.usingStair = 1;
		selectStairAndMove(depth + 1, peopleNum);
		currentPerson.usingStair = 2;
		selectStairAndMove(depth + 1, peopleNum);
	}

	private static void divideByStairAndCalDistance() {
		peopleUsingStair1 = new ArrayList<>();
		peopleUsingStair2 = new ArrayList<>();
		for (int i = 0; i < peopleNum; i++) {
			Person currentPerson = people.get(i);
			Stair usingStair = null;
			if (currentPerson.usingStair == 1) {
				usingStair = stairs.get(0);
				currentPerson.distance = Math.abs(currentPerson.x - usingStair.x)
						+ Math.abs(currentPerson.y - usingStair.y);
				peopleUsingStair1.add(currentPerson.distance);
			}
			if (currentPerson.usingStair == 2) {
				usingStair = stairs.get(1);
				currentPerson.distance = Math.abs(currentPerson.x - usingStair.x)
						+ Math.abs(currentPerson.y - usingStair.y);
				peopleUsingStair2.add(currentPerson.distance);
			}
		}
		Collections.sort(peopleUsingStair1);
		Collections.sort(peopleUsingStair2);
	}

	private static int calFinishTime() {
		Queue<Integer> stair = new LinkedList<>();
		int wait = 0;
		int usingStair1Num = peopleUsingStair1.size();
		int stair1Len = stairs.get(0).length;
		int index = 0;
		int minute1 = 1;
		int arrive = 0;
		while (true) {
			// 계단을 다 내려갔다면 stair에 다시 삽입 안해줌
			// 계단을 다 내려갔다면 stair에 다시 삽입 안해줌
			int size = stair.size();
			for (int i = 0; i < size; i++) {
				int current = stair.poll() - 1;
				if (current != 0) {
					stair.add(current);
				}
				if (current == 0) {
					arrive++;
				}
			}
			while (stair.size() < MAX_MOVE && wait != 0) {
				stair.add(stair1Len);
				wait--;
			}
			if (arrive == usingStair1Num) {
				break;
			}
			while (index < usingStair1Num && peopleUsingStair1.get(index) == minute1) {
				wait++;
				index++;
			}
			minute1++;
		}

		int usingStair2Num = peopleUsingStair2.size();
		int stair2Len = stairs.get(1).length;
		wait = 0;
		index = 0;
		int minute2 = 1;
		arrive = 0;
		while (true) {
			// 계단을 다 내려갔다면 stair에 다시 삽입 안해줌
			int size = stair.size();
			for (int i = 0; i < size; i++) {
				int current = stair.poll() - 1;
				if (current != 0) {
					stair.add(current);
				}
				if (current == 0) {
					arrive++;
				}
			}
			while (stair.size() < MAX_MOVE && wait != 0) {
				stair.add(stair2Len);
				wait--;
			}
			if (arrive == usingStair2Num) {
				break;
			}
			while (index < usingStair2Num && peopleUsingStair2.get(index) == minute2) {
				wait++;
				index++;
			}
			minute2++;
		}
		return Math.max(minute1, minute2);
	}
}