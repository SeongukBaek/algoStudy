
import java.util.*;
import java.io.*;

class Solution {

	static class Stair {
		int x;
		int y;
		int level;

		Stair(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}

	static class People {
		int x;
		int y;
		int useStairNum;
		int arrivalTime;

		People(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// 이용하는 계단까지 도착하는 시간 계산
		void calculateArrivalTime() {
			this.arrivalTime = Math.abs(stairs.get(useStairNum).x - this.x)
					+ Math.abs(stairs.get(useStairNum).y - this.y);
		}
	}

	static int n, peopleNum, minTime;
	static int[][] map;
	static List<People> people;
	static List<Stair> stairs;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			minTime = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			people = new ArrayList<>();
			stairs = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if (temp == 1) {
						people.add(new People(i, j));
					}
					// 계단인 경우
					if (temp > 1) {
						stairs.add(new Stair(i, j, temp));
					}
					map[i][j] = temp;
				}
			}
			peopleNum = people.size();
			makeGroup(0, 0);
			
			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}
		System.out.println(sb);
	}

	// 사람들의 그룹을 둘으로 나누기
	public static void makeGroup(int depth, int start) {
		if (depth == peopleNum) {
			// 탈출 시간 계산
			int time = CalculateEscapeTime();
			minTime = Math.min(time, minTime);
			
			return;
		}
		
		for (int i = start; i < peopleNum; i++) {
			// 첫번째 계단을 이용하는 경우
			people.get(i).useStairNum = 0;
			people.get(i).calculateArrivalTime();
			makeGroup(depth + 1, i + 1);
			// 두번째 계단을 이용하는 경우
			people.get(i).useStairNum = 1;
			people.get(i).calculateArrivalTime();
			makeGroup(depth + 1, i + 1);
		}
	}

	public static int CalculateEscapeTime() {
		int time = 0;
		List<Queue<Integer>> qList = new ArrayList<>();
		qList.add(new LinkedList<>());
		qList.add(new LinkedList<>());
		List<People> copyPeople = new ArrayList<>(people);

		// 계단에 도착하는 시간을 기준으로 정렬
		copyPeople.sort((o1, o2) -> {
			return o1.arrivalTime - o2.arrivalTime;
		});

		// 처음 계단에 도착하는 사람 시간을 넣음
		time = copyPeople.get(0).arrivalTime;
		// 계단을 다 내려가는 시간을 저장
		qList.get(copyPeople.get(0).useStairNum).add(time + 1 + stairs.get(copyPeople.get(0).useStairNum).level);

		for (int i = 1; i < peopleNum; i++) {
			int arrivalTime = copyPeople.get(i).arrivalTime;
			int stairNum = copyPeople.get(i).useStairNum;
			
			if (time < arrivalTime) {
				time = arrivalTime;
			}
			
			// 계단을 내려갈 수 있는 경우
			if (qList.get(stairNum).size() < 3) {
				qList.get(stairNum).add(arrivalTime + 1 + stairs.get(stairNum).level);
			}

			// 계단을 내려갈 수 없는 경우
			else {
				int temp = qList.get(stairNum).poll();
				if (arrivalTime < temp) {
					time = temp;
					// 1분의 대기 시간이 필요 없음
					qList.get(stairNum).add(time + stairs.get(stairNum).level);
				}
				else { 
					qList.get(stairNum).add(time + 1 + stairs.get(stairNum).level);
				}
			}
		}
		
		// 계단에 남아 있는 사람들을 다 내려보냄
		while (!qList.get(0).isEmpty()) {
			int temp = qList.get(0).poll();
			time = Math.max(time, temp);
		}
		
		while (!qList.get(1).isEmpty()) {
			int temp = qList.get(1).poll();
			time = Math.max(time, temp);
		}
		return time;
	}
}
