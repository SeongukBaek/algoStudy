import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static List<Node>[] graph;
	static int endPoint;
	static int maxWeight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		graph = new ArrayList[N];		
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			
			while(true) {
				int v = Integer.parseInt(st.nextToken());
				if(v == -1) {
					break;
				}
				graph[start-1].add(new Node(v-1, Integer.parseInt(st.nextToken())));
			}
		}
		
        // 지름의 한 쪽 끝점을 구하기
		visited[0] = true;
		getEndPoint(0, 0);
		visited[0] = false;
		
        // 지름 구하기
		visited[endPoint] = true;
		getEndPoint(endPoint, 0);
		
		System.out.println(maxWeight);
	}
	
	private static void getEndPoint(int v, int w) {
		if(w > maxWeight) {
			maxWeight = w;
			endPoint = v;
		}
	
		for(Node nxt : graph[v]) {
			if(visited[nxt.v]) {
				continue;
			}
			
			visited[nxt.v] = true;
			getEndPoint(nxt.v, w+nxt.w);
			visited[nxt.v] = false;
		}
		
	}

	static class Node{
		int v;
		int w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
