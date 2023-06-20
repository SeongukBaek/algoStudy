import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X;
	static List<int[]>[] rightRoadInfo;
	static List<int[]>[] reverseRoadInfo;

	public static void main(String[] args) throws IOException {
		init();
		findMax();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		rightRoadInfo = new List[N + 1];
		reverseRoadInfo = new List[N + 1];
		
		for (int i = 0; i <= N; i++) {
			rightRoadInfo[i] = new ArrayList<>();
			reverseRoadInfo[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			rightRoadInfo[start].add(new int[] { end, time });
			reverseRoadInfo[end].add(new int[] { start, time });
		}
	}
	
	private static int[] dijkstra(List<int[]>[] roadInfos) {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		boolean visited[] = new boolean[N + 1];
		int distance[] = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;
		
		pq.add(new int[] { X, 0 });
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(visited[cur[0]]) {
				continue;
			}
			
			visited[cur[0]] = true;
			for (int[] roadInfo : roadInfos[cur[0]]) {
				if(!visited[roadInfo[0]] && distance[roadInfo[0]] > distance[cur[0]] + roadInfo[1]) {
					distance[roadInfo[0]] = distance[cur[0]] + roadInfo[1];
					pq.add(new int[] { roadInfo[0], distance[roadInfo[0]] });
				}
			}
		}
		return distance;
	}
	
	private static void findMax() {
		int rightResult[] = dijkstra(rightRoadInfo);
		int reverseResult[] = dijkstra(reverseRoadInfo);
		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, rightResult[i] + reverseResult[i]);
		}
		
		System.out.println(max);
	}
}