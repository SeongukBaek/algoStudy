import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int W;
	static int dis[];
	static List<edge>[] road;
	
	static class edge{
		int node; // 노드
		int time; // 걸리는 시간
		
		edge(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); //  테스트케이스의 개수 TC (1 ≤ TC ≤ 5)
		
		for(int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지점의 수 N (1 ≤ N ≤ 500)
			M = Integer.parseInt(st.nextToken()); // 도로의 개수 M (1 ≤ M ≤ 2500)
			W = Integer.parseInt(st.nextToken()); // 웜홀의 개수 W (1 ≤ W ≤ 200)
		
			dis = new int[N+1];
			Arrays.fill(dis, 5000001);
			road = new ArrayList[N+1];
			
			for(int j = 0; j < N+1; j++) {
				road[j] = new ArrayList<>();
			}
			
			for(int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken()); 
				int c = Integer.parseInt(st.nextToken());
				road[a].add(new edge(b, c));
				road[b].add(new edge(a, c));
			}
			
			for(int j = 0; j < W; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken()); 
				int c = Integer.parseInt(st.nextToken());
				road[a].add(new edge(b, -c));
			}
			
			if(hasMinusCycle()) System.out.println("YES");
			else System.out.println("NO");
			
		}
		

	}
	
	static boolean hasMinusCycle() {
		dis[1] = 0;
		
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < road.length; j++) {
				for(edge next: road[j]) {
					if(dis[j] + next.time < dis[next.node]) {
						dis[next.node] = dis[j] + next.time;
					}
				}
			}
		}
		
		for(int j = 1; j < road.length; j++) {
			for(edge next: road[j]) {
				if(dis[j] + next.time < dis[next.node]) {
					return true;
				}
			}
		}
		return false;
	}
}