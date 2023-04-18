import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1. post에서 맨 뒷 숫자 -> root
// 2. in에서 찾은 맨 뒷 숫자 위치 찾기
// 3. 해당 위치의 앞은 L, 뒤는 R
// 4. post(해당 위치 index - 1)이 L의 루트, post(length - 1)이 R의 루트
public class Main {
	private static int[] inorder;
	private static int[] postorder;
	// inorder에서의 숫자 인덱스 저장
	private static int[] positions;
	private static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		positions = new int[N + 1];
		for (int index = 0; index < N; index++) {
			positions[inorder[index]] = index;
		}
		
		getPreorder(0, inorder.length - 1, 0, postorder.length - 1);
	}
	
	/**
	 * inorder의 시작 인덱스와 끝 인덱스, postorder의 시작 인덱스와 끝 인덱스를 인자로 받아 preorder 구성
	 * inStart, inEnd는 inorder배열을 탐색할 인덱스, postStart, postEnd는 postorder배열을 탐색할 인덱스
	 * */
	private static void getPreorder(int inStart, int inEnd, int postStart, int postEnd) {
		// 인덱스 범위가 어긋나는 경우
		if (inStart > inEnd || postStart > postEnd) {
			return;
		}
		
		// postorder의 마지막 값은 항상 루트
		int root = postorder[postEnd];
		System.out.print(root + " ");
		
		// root가 inorder의 어느 인덱스에 위치해있는지 찾기
		int rootPosition = positions[root];
		// 왼쪽 자식 노드들의 개수 + 1
		int left = rootPosition - inStart;
		
		// root 기준 왼쪽 자식 트리 확인
		getPreorder(inStart, rootPosition - 1, postStart, postStart + left - 1);
		// root 기준 오른쪽 자식 트리 확인
		getPreorder(rootPosition + 1, inEnd, postStart + left, postEnd - 1);
	}
}