import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Info implements Comparable<Info>{
		int cost, end;

		public Info(int end, int cost) {
			super();
			this.cost = cost;
			this.end = end;
		}

		@Override
		public int compareTo(Info arg0) {
			// TODO Auto-generated method stub
			return this.cost - arg0.cost; // cost 오름차순으로 정렬
		}
	}
	
	static int N, M, X, max;
	static List<Info>[] map, reverseMap;
	static int[] cost1, cost2;
	
	// 가는 길과 오는 길이 다름 주의
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new ArrayList[N+1]; // 순방향 인접리스트
		reverseMap = new ArrayList[N+1]; // 역방향 인접리스트
		
		for(int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
			reverseMap[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[start].add(new Info(end, cost));
			reverseMap[end].add(new Info(start, cost));
		}
		
		cost1 = getCost(X, map);
		cost2 = getCost(X, reverseMap);
		
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, cost1[i]+cost2[i]); // 왕복값이 가장 큰 것이 답이 됨
		}
		
		System.out.println(max);
	}
	
	static int[] getCost(int start, List<Info>[] linked) {
		PriorityQueue<Info> pq = new PriorityQueue<>(); // 최저비용 기준 유선순위 큐
		int[] cost = new int[N+1];
		boolean[] visited = new boolean[N+1];
		initArr(cost); // 무한대로 초기화 
		
		cost[start] = 0;
		pq.add(new Info(start, 0));
		
		while(!pq.isEmpty()) {
			Info current = pq.poll();
			
			// 이미 방문한 점이면 패스
			if(visited[current.end]) {
				continue;
			}
			
			visited[current.end] = true;
			// 현재 정점에 연결된 모든 정점 탐색
			for(int i = 0; i < linked[current.end].size(); i++) {
				Info next = linked[current.end].get(i);
				if(cost[next.end] > next.cost + current.cost) {
					cost[next.end] = next.cost + current.cost;
					pq.add(new Info(next.end, cost[next.end]));
				}
			}
		}
		return cost;
	}
	
	static void initArr(int[] cost) {
		for(int i = 0; i <= N; i++) {
			cost[i] = Integer.MAX_VALUE;
		}
	}
}
