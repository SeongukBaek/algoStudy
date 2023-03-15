import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    static class Node{
		int e, w; // 도착지와 시간 
		
		Node(int e, int w){
			this.e = e;
			this.w = w;
		}
	}
	
	static List<List<Node>> graph;
	
	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        graph = new ArrayList<>();
        // 리스트는 사용전에 만들어놔야 함
        for(int i = 0; i <= n; i++) {
        	graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < paths.length; i++) {
        	int n1 = paths[i][0];
        	int n2 = paths[i][1];
        	int w = paths[i][2];
        	
        	
        	// 만약 출입구라면 출입구 --> 다른 정점으로 가는 단방향 입력
        	// 만약 산봉우리라면 다른 정점 --> 산봉우리로 가는 단방향 입력
        	if(isGate(n1, gates) || isSummit(n2, summits)) {
        		// 만약 n1이 출입문이거나 또는 n2가 산봉우리라면 : n1 --> n2 방향으로 연결 
        		graph.get(n1).add(new Node(n2, w));
        	}else if(isGate(n2, gates) || isSummit(n1, summits)) {
        		// 만약 n2 가 출입문이거나 또는 n1이 산봉우리라면 : n2 --> n1 방향으로 연결 
        		graph.get(n2).add(new Node(n1, w));
        	}else {
        		// 출입문 또는 산봉우리가 없으면 양방향 연결 
        		graph.get(n1).add(new Node(n2, w));
        		graph.get(n2).add(new Node(n1, w));        		
        	}
        }
        
        answer = findPath(n, gates, summits);
        
        return answer;
    }
	
	// 출입구이면 true, 아니면 false
	static boolean isGate(int num, int[] gates) {
		for(int i = 0 ; i < gates.length; i++) {
			if(num == gates[i]) {
				return true;
			}
		}
		return false;
	}
	
	// 산봉우리이면 true, 아니면 false
	static boolean isSummit(int num, int[] summits) {
		for(int i = 0 ; i < summits.length; i++) {
			if(num == summits[i]) {
				return true;
			}
		}
		return false;
	}
	
	static int[] findPath(int n, int[] gates, int[] summits) {
		int num = Integer.MAX_VALUE;
		int min = Integer.MAX_VALUE;
		
		int[] intensity = new int[n+1]; // 1 ~ n번까지 번호가 있으니까 
		for(int i = 0; i <= n; i++) { 
			intensity[i] = Integer.MAX_VALUE; // 최소값 찾을거니까 
		}
		
		// 출입구에서 산봉우리까지 intensity 구하기 
		// 한 번에 모든 출입구 다 탐색하기 위해 
		Queue<Node> searchNode = new LinkedList<>();
		for(int i : gates) {
			searchNode.add(new Node(i, 0)); // 출입구는 거리가 0
			intensity[i] = 0; 
		}
		
		
		
		while(!searchNode.isEmpty()) {
			Node current = searchNode.poll(); 
			
			if(current.w > intensity[current.e]) {
				continue;
			}
			
			// 해당 노드에서 갈 수 있는 모든 노드 탐색
			for(int i = 0; i < graph.get(current.e).size(); i++) {
				Node node = graph.get(current.e).get(i);
				
				// 이전 정점까지의 최대값과 이전 정점부터 이번 노드까지의 시간을 비교해서 더 큰 수 찾기
				int max = intensity[current.e];
				if(intensity[current.e] < node.w) {
					max = node.w;
				}
				
				// 원래 저장된 최소값과 비교하여 최소값 갱신 
				if(intensity[node.e] > max) {
					intensity[node.e] = max;
					// 다음 탐색을 위해 최소값을 가진 노드를 큐에 넣어주기 
					searchNode.add(new Node(node.e, max));
				}
			}
		}

		Arrays.sort(summits);
		
		// 산봉우리의 intensity 최소값 찾아주기 
		for(int i = 0; i < summits.length; i++) {
			if(intensity[summits[i]] < min) {
				min = intensity[summits[i]];
				num = summits[i];
			}
		}
		
		return new int[] {num, min};
	}
}