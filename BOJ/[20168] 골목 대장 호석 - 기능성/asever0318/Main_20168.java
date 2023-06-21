import java.util.*;
import java.io.*;

public class Main {
	static class Info{
		int v, cost;
		public Info(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
	
	static int N, M, A, B, C, min;
	static List<Info>[] map;
	static boolean[] visited;
	static boolean check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		
		for(int i = 0; i < N+1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프 
			map[v1].add(new Info(v2, cost));
			map[v2].add(new Info(v1, cost));
		}
		
		min = Integer.MAX_VALUE;
		visited = new boolean[N+1];
		visited[A] = true;
		goAToB(A, 0, 0);
		
		if(!check) {
			min = -1;
		}
		
		System.out.println(min);
	}
	
	static void goAToB(int start, int max, int sum) {
		if(sum > C) {
			return;
		}
		
		if(start == B) { // 도착지에 도착하면 min값 갱신 
			min = Math.min(max, min);
			check = true;
			return;
		}
		
		for(int i = 0; i < map[start].size(); i++) {
			int next = map[start].get(i).v;
			int cost = map[start].get(i).cost;
			
			if(visited[next]) {
				continue;
			}
			
			visited[next] = true;
			goAToB(next, Math.max(max, cost), sum+cost);
			visited[next] = false;
		}
	}
}
