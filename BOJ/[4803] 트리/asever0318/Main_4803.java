import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] link;
	static boolean[] visited;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		int t = 1;
		
		while(true) {
			int total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) {
				break;
			}
			
			link = new ArrayList[N+1];
			visited = new boolean[N+1];
			parent = new int[N+1];
			
			for(int i = 0; i <= N; i++) {
				link[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				// 그래프 간선 등록 (양방향)
				link[v1].add(v2);
				link[v2].add(v1);
			}
			
			for(int i = 1; i <= N; i++) { // 정점 번호는 1~N
				if(visited[i]) { // 방문한 정점이면 패스
					continue;
				}
				total += countTree(i);
			}
			
			printAnswer(t, total);
			t++;
		}
	}
	
	static void printAnswer(int t, int total) {
		System.out.print("Case "+t+": ");
		if(total == 0) {
			System.out.println("No trees.");
		}else if(total == 1) {
			System.out.println("There is one tree.");
		}else {
			System.out.println("A forest of "+total+" trees.");
		}
	}
	
	static int countTree(int n) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		visited[n] = true;
		
		// 이미 방문한 정점을 재방문하려고 할 때, 그 노드가 현재 정점의 부모가 아니라면 사이클		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			// 해당 정점에서 연결된 정점들 모두 탐색
			for(int i = 0; i < link[current].size(); i++) {
				int next = link[current].get(i);
				
				if(visited[next]) { // 방문한 정점을 다시 방문할 떄 
					if(next != parent[current]) {
						return 0; // 부모가 아니라면 사이클이므로 0 반환 
					}
					else { // 부모면 다음 정점으로 넘어가기 
						continue;
					}
				}
				
				// 방문한 점이 아닐 때
				visited[next] = true; // 방문표시
				queue.add(next); // 큐에 넣기
				parent[next] = current; // 부모노드 저장 
			}
		}
		
		return 1;
	}
}
