import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Node {
        int to;
        int time;
        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
    
    private static List<List<Node>> adjInfo = new ArrayList<>();
    // 해당 지점의 상태를 저장, 0 : 쉼터, 1 : 시작점, 2 : 산봉우리
    private static int[] nodeInfo;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
    	nodeInfo = new int[n + 1];
        
        // 인접 리스트 초기화
        for (int index = 0; index <= n; index++) {
            adjInfo.add(new ArrayList<>());
        }
        
        // 산봉우리 체크
        for (int summit : summits) {
            nodeInfo[summit] = 2;
        }
        // 시작점 체크
        for (int gate : gates) {
        	nodeInfo[gate] = 1;
        }
        
        for (int[] path : paths) {
        	// 출발점이거나, 산봉우리라면 단방향 지정
        	if (nodeInfo[path[0]] == 1 || nodeInfo[path[1]] == 2) {
                adjInfo.get(path[0]).add(new Node(path[1], path[2]));
        	}
        	// 산봉우리거나, 출발점이라면 단방향 지정
        	else if (nodeInfo[path[1]] == 1 || nodeInfo[path[0]] == 2) {
                adjInfo.get(path[1]).add(new Node(path[0], path[2]));
        	} else {
                adjInfo.get(path[0]).add(new Node(path[1], path[2]));
                adjInfo.get(path[1]).add(new Node(path[0], path[2]));
        	}
        }
        
        return dijkstra(n, gates, summits);
    }
    
    // 주어진 시작점으로부터 이동하면서 산봉우리를 만나면 해당 산봉우리 반환
    private static int[] dijkstra(int n, int[] gates, int[] summits) {
        // 각 산봉우리까지 도달하는 최소 intensity
    	int[] intensitys = new int[n + 1];
        Arrays.fill(intensitys, Integer.MAX_VALUE);

    	Queue<Node> nodes = new LinkedList<>();
    	for (int gate : gates) {
        	nodes.add(new Node(gate, 0));
        	// 출발점의 intensity는 0
        	intensitys[gate] = 0;
    	}
    	
    	while (!nodes.isEmpty()) {
    		Node current = nodes.poll();
    		
    		// 이미 해당 정점까지의 intensity보다 큰 경우는 패스
    		if (current.time > intensitys[current.to]) {
    			continue;
    		}
        	
    		for (Node next : adjInfo.get(current.to)) {
    			// intensity 갱신
    			// 현재 정점까지의 intensity와 현재 정점에서 갈 수 있는 다음 정점까지의 intensity 중 큰 값을 저장
        		int intensity = Math.max(intensitys[current.to], next.time);
        		
        		// 현재 정점에서 갈 수 있는 다음 정점까지의 intensity보다 위에서 갱신한 값이 더 작다면,
                // 최소 intensity 갱신 -> next.to까지의 intensity를 최소로 갱신해주고 큐에 삽입
        		if (intensitys[next.to] > intensity) {
        			intensitys[next.to] = intensity;
            		nodes.add(new Node(next.to, intensity));
        		}
        	}
    	}
    	
    	int minSummit = Integer.MAX_VALUE;
    	int minIntensity = Integer.MAX_VALUE;
    	
    	// 가장 작은 번호의 산봉우리를 반환하기 위함
    	Arrays.sort(summits);
    	
    	for (int summit : summits) {
    		if (intensitys[summit] < minIntensity) {
    			minSummit = summit;
    			minIntensity = intensitys[summit];
    		}
    	}
    	
    	return new int[] {minSummit, minIntensity};
    }
}