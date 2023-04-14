import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 임의의 노트 x에서 가장 먼 거리에 있는 노드 a를 찾는다.
// 노드 a에서 가장 먼 거리에 있는 노드 b를 찾는다.
// 노드 a에서 노드 b를 잇는 경로가 트리의 지름이다.

public class Main{
	static class Vertex{
		int to, w;

		public Vertex(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
	}
	
	static int V, max;
	static List<Vertex>[] linked;
	static boolean[] visited;
	static int y;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		visited = new boolean[V+1];
		linked = new ArrayList[V+1];
		
		for(int j = 0; j <= V; j++) {
			linked[j] = new ArrayList<>();
		}
		
		for(int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());

			while(st.hasMoreTokens()) {
				int to = Integer.parseInt(st.nextToken());
				if(to == -1) {
					break;
				}
				
				int w = Integer.parseInt(st.nextToken());
				linked[from].add(new Vertex(to, w));
			}
		}
		
		// 임의의 정점(x)으로부터 가장 멀리 떨어져 있는 정점(y)은 항상 지름의 양 끝 중 하나이다.
		// 만약 포함되지 않는다면 지름의 길이와 x에서 y정점의 거리가 같아야 함 
		
		// 모든 정점에 대해서 dfs를 진행하는 게 아니라 단 2개의 정점에서 대해서만 진행하게 됨 --> 시간초과 해결 
		findMax(1, 0);	// 아무 임의의 정점에서 가장 먼 정점을 찾기 --> 임의의 정점을 1번 정점으로 함 	
		visited = new boolean[V+1];
		max = 0;
		findMax(y, 0); // y에서 가장 먼 정점 찾기 --> 트리의 지름 
		
		System.out.println(max);
	}
	
	
	static void findMax(int v, int sum) {
		if(max < sum) {
			max = sum;
			y = v;
		}
		
		visited[v] = true;
		
		for(int i = 0; i < linked[v].size(); i++) {
			Vertex next = linked[v].get(i);
			
			if(visited[next.to]) {
				continue;
			}
			
			findMax(next.to, sum+next.w);
		}
	}
}
