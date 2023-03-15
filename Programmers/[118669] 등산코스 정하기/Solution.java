import java.util.*;

public class Solution {
	static class Node {
		int edge, intensity;

		public Node(int edge, int intensity) {
			this.edge = edge;
			this.intensity = intensity;
		}
	}

	static List<Node>[] nodes;
	static int[] d;
	static Set<Integer> gateSet = new HashSet<>();
	static Set<Integer> summitSet = new HashSet<>();
	static int resultNode = Integer.MAX_VALUE;
	static int minIntensity = Integer.MAX_VALUE;

	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		nodes = new List[n + 1];
		d = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int gate : gates) {
			gateSet.add(gate);
		}

		for (int summit : summits) {
			summitSet.add(summit);
		}

		for (int[] path : paths) {
			if (gateSet.contains(path[0]) || summitSet.contains(path[1])) {
				nodes[path[0]].add(new Node(path[1], path[2]));
			}

			else if (gateSet.contains(path[1]) || summitSet.contains(path[0])) {
				nodes[path[1]].add(new Node(path[0], path[2]));
			}
			// summit가 포함되지않는 경우
			else {
				nodes[path[0]].add(new Node(path[1], path[2]));
				nodes[path[1]].add(new Node(path[0], path[2]));
			}
		}

		searchRoute(n, gates, summits);
		return new int[] {resultNode, minIntensity};
	}

	static void searchRoute(int n, int[] gates, int[] summits) {
		Queue<int[]> q = new ArrayDeque<>();
		Arrays.fill(d,  Integer.MAX_VALUE);
		
		for(int gate: gates) {
			q.add(new int[] {gate, 0});
			d[gate] = 0;
		}
		
		while(!q.isEmpty()) {
			int edge = q.peek()[0];
			int intensity = q.peek()[1];
			q.poll();
			
			if(d[edge] < intensity) continue;
			
			for(Node nd : nodes[edge]) {
				int distance = Math.max(d[edge], nd.intensity);
				if(d[nd.edge] > distance) {
					d[nd.edge] = distance;
					q.add(new int[] {nd.edge, distance});
				}
			}
		}
		
		Arrays.sort(summits);
		for(int summit : summits) {
			if(minIntensity > d[summit]) {
				minIntensity = d[summit];
				resultNode = summit;
			}
		}
	}
}
