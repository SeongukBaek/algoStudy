import java.util.*;
import java.io.*;

public class Main {
	static List<List<Integer>> nodes;
	static int n = 1, m = 1;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int caseCnt = 0;
		while(true) {
			caseCnt++;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if(n == 0 && m == 0) {
				break;
			}
			
			nodes = new ArrayList<>();
			for(int node = 0; node <= n; node++) {
				nodes.add(new ArrayList<>());
			}

			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				nodes.get(v1).add(v2);
				nodes.get(v2).add(v1);
			}
			
			int treeCnt = getTreeCount();
			
			System.out.print("Case "+caseCnt+": ");
			if(treeCnt == 0) {
				System.out.println("No trees.");
			}else if(treeCnt == 1) {
				System.out.println("There is one tree.");
			}else {
				System.out.println("A forest of "+ treeCnt +" trees.");
			}
		}
	}
	
	/*몇개의 트리가 있는지 계산하기*/
	private static int getTreeCount() {
		int treeCnt = 0;
		visited = new boolean[n+1];
		
		for(int node = 1; node <= n; node++) {
			if(visited[node]) {
				continue;
			}
			if(isTree(node)) {
				treeCnt++;
			}
		}
		return treeCnt;
	}
	
	/*트리가 되는지 아닌지 확인하기*/
	private static boolean isTree(int node) {
		boolean tree = true;
	    Deque<Integer> graph = new ArrayDeque<>();
        // 부모 노드 저장하기
	    Map<Integer, Integer> parent = new HashMap<>(); 
	    graph.add(node);
        // 시작점의 부모는 -1로 설정한다.
	    parent.put(node, -1); 
	    visited[node] = true;

	    while (!graph.isEmpty()) {
	        int cur = graph.poll();

	        for (int nxt : nodes.get(cur)) {
	            if (visited[nxt]) {
                    // 부모노드인지 판단 (1->2 순으로 이미 확인을 해서 2->1로 다시 체크를 안하기 위해)
	                if (parent.get(cur) != nxt) { 
                        // 순환 그래프임을 표시
	                    tree = false;
	                }
	            } else {
	                graph.add(nxt);
	                parent.put(nxt, cur); 
	            }
	            visited[nxt] = true;
	        }
	    }
	    return tree;
	}

}
