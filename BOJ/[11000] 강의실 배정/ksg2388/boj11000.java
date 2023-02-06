package practice.boj;

import java.util.*;
import java.io.*;

// 우선순위 큐를 사용해서 강의 종료 시간을 기준으로 오름차순 정렬
// 1. 배열을 시작 시간 기준으로 정렬
// 2. 큐가 빈 경우 데이터 입력
// 3. 큐에 값이 있는 경우 새로 들어갈 강의의 시작 시간이 큐의 가장 앞의 값의 종료 시간이후라면 기존의 큐의 값을 빼고 새로 큐에 삽입
// 4. 아니라면 기존 데이터를 놔두고 새로운 큐를 삽입
// 5. 과정을 반복하고 마지막에 큐에 남아있는 값의 길이 반환

public class boj11000 {
	static int n;
	static int[][] lecture;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		lecture = new int[n][2];

		// 낮은 숫자가 우선 순위인 int 형 우선순위 큐 선언
		PriorityQueue<Integer> lectureRoom = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				lecture[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(lecture, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];
		});

		for (int i = 0; i < n; i++) {
			if (lectureRoom.isEmpty()) {
				lectureRoom.add(lecture[i][1]);
				continue;
			}
			;

			if (lecture[i][0] >= lectureRoom.peek()) {
				lectureRoom.remove();
			}
			lectureRoom.add(lecture[i][1]);
		}

		System.out.println(lectureRoom.size());
	}
}
// 1 3. 2 4. 3 5