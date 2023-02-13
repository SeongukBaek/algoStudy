import java.io.*;
import java.util.*;

public class SectionSum5 {
	static int[][] sumArr;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sumArr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			sumArr[i][0] = 0;
			sumArr[0][i] = 0;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				sumArr[i][j] = Integer.parseInt(st.nextToken()) 
						+ sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1];
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Node start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Node end = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			int sum = getSection(start, end);
			sb.append(sum + "\n");
		}

		System.out.println(sb.toString());

	}

	static int getSection(Node start, Node end) {
		int sum = 0;

		sum = sumArr[end.x][end.y] 
				- sumArr[start.x-1][end.y] 
				- sumArr[end.x][start.y-1] 
				+ sumArr[start.x-1][start.y-1]; 

		return sum;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}