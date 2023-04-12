import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static List<int[]>[] tree;
	static boolean[] visited;
	static int answer = 0;
	static int endNode = 0;
	
	public static void main(String[] args) throws IOException {
		
		/* 입력 및 변수 초기화 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()) + 1;
		StringTokenizer st;
		visited = new boolean[n];
		tree = new List[n];
		for(int i=1; i<n; i++) {
			tree[i] = new ArrayList<>();
		}
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB;
			while(true) {
				nodeB = Integer.parseInt(st.nextToken());
				if(nodeB == -1) {
					break;
				}
				tree[nodeA].add(new int[] {nodeB, Integer.parseInt(st.nextToken())});
			}	
		}
		
		
		visited[1] = true;
		searchTreeDiameter(1, 0); // 임의의 한 노드(노드 1)에서 가장 멀리 있는 노드 구하기
		visited = new boolean[n];
		visited[endNode] = true; 
		searchTreeDiameter(endNode, 0); // 가장 멀리있는 노드에서 가장 멀리 있는 노드 구하기
		
		System.out.println(answer);
	}

	/* DFS : 트리의 지름 구하기 */
	private static void searchTreeDiameter(int node, int diameter) {
		if(answer < diameter) {
			answer = diameter;
			endNode = node;
		}
		
		for(int i=0; i<tree[node].size(); i++) {
			int[] infor = tree[node].get(i);
			if(visited[infor[0]]) continue;
			visited[infor[0]] = true;
			searchTreeDiameter(infor[0], diameter + infor[1]);
		}
	}
}