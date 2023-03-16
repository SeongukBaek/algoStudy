import java.util.*;

public class Solution {
    static class Node {
        int edge, intensity; // 노드의 목적지, intensity(최대시간)

        public Node(int edge, int intensity) {
            this.edge = edge;
            this.intensity = intensity;
        }
    }

    static List<Node>[] nodes;
    static Set<Integer> gateSet = new HashSet<>();
    static Set<Integer> summitSet = new HashSet<>();

    public static void main(String[] args) {
        int[] ans = solution(7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{1}, new int[]{2,3,5});
        System.out.println(ans[0] + " "+ans[1]);
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        nodes = new List[n + 1];
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

            // 산봉우리가 포함될 때
            if (gateSet.contains(path[0]) || summitSet.contains(path[1])) {
                nodes[path[0]].add(new Node(path[1], path[2])); // 단방향만 추가
            }

            else if (gateSet.contains(path[1]) || summitSet.contains(path[0])) {
                nodes[path[1]].add(new Node(path[0], path[2])); // 단방향만 추가
            }

            // 포함되지 않을 때
            else {
                nodes[path[0]].add(new Node(path[1], path[2])); // 양방향 모두 추가
                nodes[path[1]].add(new Node(path[0], path[2]));
            }
        }

      
        return searchRoute(n, gates, summits);
    }

    /* intensity가 가장 작은 경로 구하기 */
    static int[] searchRoute(int n, int[] gates, int[] summits) {
       
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        
        Queue<int[]> q = new ArrayDeque<>();

        for(int gate: gates) {
            q.add(new int[] {gate, 0});
            d[gate] = 0; // 입구 초기값 설정
        }

        while(!q.isEmpty()) {
            int[] data = q.poll();
            int edge = data[0];
            int intensity = data[1];
            
            // intensity가 더 크면 넘어간다
            if(d[edge] < intensity) continue;
            
            // 작으면 그 다음 경로를 찾는다
            for(Node nd : nodes[edge]) {
                int dist = Math.max(d[edge], nd.intensity); // intensity 업데이트
                // 다음 경로의 intensity가 더 작으면 
                if(d[nd.edge] > dist) {
                    d[nd.edge] = dist; // 그 경로의 intensity 업데이트
                    q.add(new int[] {nd.edge, dist});
                }
            }
        }
        
        // intensity가 가장 작은 경로의 산봉우리 찾기
        int resultSummit = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits);
        for(int summit : summits) {
            if(minIntensity > d[summit]) {
                minIntensity = d[summit];
                resultSummit = summit;
            }
        }
        
        return new int[] {resultSummit, minIntensity};
    }
}
