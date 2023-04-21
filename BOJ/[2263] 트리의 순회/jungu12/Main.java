import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] inOrder;
	static int[] inOrderIdx; 
	static int[] postOrder;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		inOrder = new int[N + 1];
		inOrderIdx = new int[N + 1];
		postOrder = new int[N + 1];

		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			inOrderIdx[inOrder[i]] = i;
		}

		getPreOrder(1, N, 1, N);
		System.out.println(sb.toString());
	}

	private static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
		if (inStart > inEnd) {
			return;
		}

		int root = postOrder[postEnd];
		sb.append(root + " ");

		int rootIdx = inOrderIdx[root];

		int left = rootIdx - inStart;

		getPreOrder(inStart, rootIdx - 1, postStart, postStart + left - 1);

		getPreOrder(rootIdx + 1, inEnd, postStart + left, postEnd - 1);
	}
}