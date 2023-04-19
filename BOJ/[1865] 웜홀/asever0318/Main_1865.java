package algo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1865 {
	static class Point{
		int end, w;

		public Point(int end, int w) {
			super();
			this.end = end;
			this.w = w;
		}
	}
	static int N, M, W;
	static List<Point>[] linked;
	static int[] dist;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		for(int t = 0; t < TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			dist = new int[N+1];
			linked = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				linked[i] = new ArrayList<>();
			}
			
			// 도로 입력 --> 양방향
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				linked[S].add(new Point(E, T));
				linked[E].add(new Point(S, T));
			}
			
			// 웜홀 입력 --> 단방향
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				linked[S].add(new Point(E, -T)); // 줄어드는 시간이므로 마이너스 
			}
			
			boolean checkCycle = false;
			for(int i = 1; i <= N; i++) {
				if(bellmanFord(i)) { // true면 음의 사이클 o
					System.out.println("YES");
					checkCycle = true;
					break;
				}
			}
			
			if(!checkCycle) { // 음의 사이클 x --> 도착 x
				System.out.println("NO");
			}
		}
	}
	
	// 벨만포드 알고리즘 : 한 정점에서 다른 모든 정점으로 가는 최단 경로 (음의 간선 가능)
	// 저장해놓은 최소 비용을 이용해서 새로운 최소 비용을 갱신 
	// A에서 B까지의 최단 거리는 최대 v-1 개의 정점을 지나기 때문에 최단 거리 갱신은 v-1번만큼 진행
	// 음수사이클 확인을 위해 v번까지 진행 --> v번째에도 최소비용 갱신이 일어나면 음의 사이클 발생한 것
	static boolean bellmanFord(int start) {
		Arrays.fill(dist, INF); // 거리 배열 무한대로 초기화
		dist[start] = 0; // 출발지는 자기자신이므로 0으로 초기화
		boolean update = false; // 최소비용이 갱신되었는지 확인 
		
		for(int i = 0; i < N-1; i++) { // (정점의 수-1)번 반복
			update = false;
			
			for(int j = 1; j <= N; j++) { // 모든 정점 탐색 
				for(int k = 0; k < linked[j].size(); k++) { // j 정점에 연결된 모든 정점 탐색
					Point next = linked[j].get(k);
					
					if(dist[j] == INF) { // 해당 정점까지 갈 수 없으면 패스
						continue;
					}
					
					// next에 이미 저장된 최소 비용보다 현재 정점에서 다음 정점으로 가는 비용이 더 작으면 갱신 
					if(dist[next.end] > dist[j] + next.w) {
						dist[next.end] = dist[j] + next.w;
						update = true;
					}
				}
			}
			
			if(!update) { // 최소 비용 갱신이 일어나지 않았으면 끝
				return false; // 음의 사이클 x
			}
		}
		
		if(update) {
			// n-1만큼 업데이트가 일어났으면 마지막 한 번 더 검사 --> 음의 사이클 체크
			for(int j = 1; j <= N; j++) { // 모든 정점 탐색 
				for(int k = 0; k < linked[j].size(); k++) { // j 정점에 연결된 모든 정점 탐색
					Point next = linked[j].get(k);
					
					if(dist[j] == INF) { // 해당 정점까지 갈 수 없으면 패스
						continue;
					}
					
					// next에 이미 저장된 최소 비용보다 현재 정점에서 다음 정점으로 가는 비용이 더 작으면 갱신 
					if(dist[next.end] > dist[j] + next.w) {
						return true; // 음의 사이클 o
					}
				}
			}
		}
		
		return false; // 음의 사이클 x
	}
}
