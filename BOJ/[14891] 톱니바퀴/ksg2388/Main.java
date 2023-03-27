import java.io.*;
import java.util.*;

// 12시를 기준으로 인덱스 0, 3시 방향 +2, 9시 방향 -2

public class Main {
	static int[][] gears;
	static int[] start;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		gears = new int[4][8];
		start = new int[4]; // 초기 방향 지정

		// 톱니바퀴 초기화
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = str.charAt(j) - '0';
			}
		}

		n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int type = Integer.parseInt(st.nextToken());
            runCommand(idx, type);
            
        }

		System.out.println(calculatePoint());
	}

	static void runCommand(int idx, int type) {
		int left = 0;
		int right = 0;

		// 왼쪽확인
		for (int i = idx; i > 0; i--) {
			// 같은 극이면
			if (gears[i][leftDirection(start[i])] == gears[i - 1][rightDirection(start[i - 1])]) {
				break;
			}
			// 다른 극이면
			left++;
		}

		// 오른쪽 확인
		for (int i = idx; i < 3; i++) {
			// 같은 극이면
			if (gears[i][rightDirection(start[i])] == gears[i + 1][leftDirection(start[i + 1])]) {
				break;
			}
			// 다른 극이면
			right++;
		}

		// 톱니바퀴 돌리기
		int temp = type;
		rotate(idx, type);
		// 왼쪽
		for (int i = 1; i <= left; i++) {
			temp = typeChange(temp);
			rotate(idx - i, temp);
		}

		temp = type;
		// 오른쪽
		for (int i = 1; i <= right; i++) {
			temp = typeChange(temp);
			rotate(idx + i, temp);
		}
	}

	static int rightDirection(int num) {
		num += 2;
		return num >= 8 ? num - 8 : num;
	}

	static int leftDirection(int num) {
		num -= 2;
		return num < 0 ? num + 8 : num;
	}

	static int typeChange(int type) {
		return type == 1 ? -1 : 1;
	}

	static void rotate(int idx, int command) {
		// 반시계 방향 회전
		if (command == -1) {
			start[idx]++;
			if (start[idx] >= 8) {
				start[idx] -= 8;
			}
			return;
		}
		// 시계 방향 회전
		start[idx]--;
		if (start[idx] < 0) {
			start[idx] += 8;
		}
	}

	static int calculatePoint() {
		int sum = 0;

		for (int i = 0; i < 4; i++) {
			if (gears[i][start[i]] == 1) {
				sum += Math.pow(2, i);
			}

		}
		return sum;
	}
}