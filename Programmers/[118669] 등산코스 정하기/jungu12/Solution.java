import java.util.*;

class Node {
	int e;
	int w;

	public Node(int e, int w) {
		this.e = e;
		this.w = w;
	}
}

class Solution {
	int minIntensity = Integer.MAX_VALUE, visitedSummit;
	List<Node>[] nodes;
	Set<Integer> gateSet = new HashSet<>();
	Set<Integer> summitSet = new HashSet<>();

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int[] answer = {};
		
		makeGraph(n, paths, gates, summits);
		answer = findMinIntensity(n, gates, summits);

		return answer;
	}

	private void makeGraph(int n, int[][] paths, int[] gates, int[] summits) {
		nodes = new List[n + 1];

		for (int i = 1; i <= n; i++) {
			nodes[i] = new ArrayList<>();
		}
		
        //출입구 정보를 Set에 저장
		for(int gate : gates) {
			gateSet.add(gate);
		}
		
        //봉우리 정보를 Set에 저장
		for(int summit : summits) {
			summitSet.add(summit);
		}
		
		for(int[] path : paths) {
			//path가 [출입구, 쉼터, 시간], [쉼터, 봉우리, 시간]인 경우
			if(gateSet.contains(path[0]) || summitSet.contains(path[1])) {
				nodes[path[0]].add(new Node(path[1], path[2]));
				continue;
			}
			//path가 [봉우리, 쉼터, 시간], [봉우리, 출입구, 시간]인 경우
			if(summitSet.contains(path[0]) || gateSet.contains(path[1])) {
				nodes[path[1]].add(new Node(path[0], path[2]));
				continue;
			}
            //쉼터에서 쉼터로 이동하는 경우
			nodes[path[0]].add(new Node(path[1], path[2]));
			nodes[path[1]].add(new Node(path[0], path[2]));
		}
	}

	private int[] findMinIntensity(int n, int[] gates, int[] summits) {
		Queue<Node> nodesToVisit = new ArrayDeque<>();
		int[] intensity = new int[n + 1];
		
		Arrays.fill(intensity, Integer.MAX_VALUE);
		
		for(int gate : gates) {
			intensity[gate] = 0;
			nodesToVisit.add(new Node(gate, 0));
		}
		
		while(!nodesToVisit.isEmpty()) {
			Node current = nodesToVisit.poll();
			
			//현재 가중치가 기존 가중치보다 큰 경우 스킵
			if(current.w > intensity[current.e]) {
				continue;
			}
			
            //intensity가 더 작은 경로가 있다면 정보 업데이트
			for(Node next : nodes[current.e]) {
				int curIntensity = Math.max(intensity[current.e], next.w);
				if(intensity[next.e] > curIntensity) {
					intensity[next.e] = curIntensity;
					nodesToVisit.add(new Node(next.e, curIntensity));
				}
			}
		}
		
        //봉우리를 오름차순으로 정렬
		Arrays.sort(summits);
		
        //intensity가 가장 낮은 등산경로를 찾음
		for(int summit : summits) {
			if(minIntensity > intensity[summit]) {
				minIntensity = intensity[summit];
				visitedSummit = summit;
			}
		}
		return new int[] {visitedSummit, minIntensity};
	}
}

