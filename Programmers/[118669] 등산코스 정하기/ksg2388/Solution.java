import java.util.*;

class Solution {
    static class Node {
        int num;
        int w;

        Node(int num, int w) {
            this.num = num;
            this.w = w;
        }
    }

    private List<List<Node>> location = new ArrayList<>();
    private List<Integer> gateSet = new ArrayList<>();
    private List<Integer> summitSet = new ArrayList<>();
    private int[] intensity;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        intensity = new int[50001];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // 등산로 초기화
        for (int i = 0; i < n + 1; i++) {
            location.add(new ArrayList<>());
        }
        // 게이트 초기화
        for (int gate : gates) {
            gateSet.add(gate);
        }
        // 산봉우리 초기화
        for (int summit : summits) {
            summitSet.add(summit);
        }

        // 지점 연결
        for (int i = 0; i < paths.length; i++) {
            location.get(paths[i][0]).add(new Node(paths[i][1], paths[i][2]));
            location.get(paths[i][1]).add(new Node(paths[i][0], paths[i][2]));
        }
        return checkOptimalCourse();
    }

    int[] checkOptimalCourse() {
        int[] result = { -1, Integer.MAX_VALUE };
        int count = summitSet.size();
        Queue<Node> nextCourse = new PriorityQueue<>((o1, o2) -> {
            return o1.w - o2.w;
        });

        for (int gate : gateSet) {
            nextCourse.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!nextCourse.isEmpty() && count > 0) {
            Node cur = nextCourse.poll();
            // 산인 경우 무시
            if (summitSet.contains(cur.num)) {
                count--;
                continue;
            }
            for (Node next : location.get(cur.num)) {
                // 게이트인 경우 무시
                if (gateSet.contains(next.num)) {
                    continue;
                }

                int dist = Math.max(intensity[cur.num], next.w);
                // 지금 갈 수 있는 거리보다 짧은 경우
                if (intensity[next.num] > dist) {
                    intensity[next.num] = dist;
                    nextCourse.add(new Node(next.num, dist));
                }
            }
        }
        Collections.sort(summitSet);
        for (int summit : summitSet) {
            if (result[1] > intensity[summit]) {
                result[0] = summit;
                result[1] = intensity[summit];
            }
        }
        return result;
    }
}