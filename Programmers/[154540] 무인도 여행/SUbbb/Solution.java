import java.util.*;

class Solution {
    // 숫자가 있는 지점 방문 -> BFS로 연속된 구역 카운트 -> 리스트에 추가
    // 리스트 오름차순 정렬해서 배열로 반환

    private int R;
    private int C;

    private String[] maps;
    // 방문 처리
    private boolean[][] isVisited;

    private List<Integer> areas = new ArrayList();

    public int[] solution(String[] maps) {
        this.maps = maps;
        R = maps.length;
        C = maps[0].length();

        isVisited = new boolean[R][C];

        computeUninhabitedIsland();

        if (areas.size() == 0) {
            return new int[] {-1};
        }

        areas.sort(Comparator.naturalOrder());
        return areas.stream().mapToInt(Integer::intValue).toArray();
    }

    private void computeUninhabitedIsland() {
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (isVisited[x][y] || maps[x].charAt(y) == 'X') {
                    continue;
                }

                areas.add(computeConnectedAreas(x, y));
            }
        }
    }

    private final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int computeConnectedAreas(int x, int y) {
        int area = 0;
        Queue<int[]> locations = new ArrayDeque<>();
        locations.add(new int[] {x, y});
        isVisited[x][y] = true;
        area += maps[x].charAt(y) - '0';

        while (!locations.isEmpty()) {
            int[] current = locations.poll();
            int cx = current[0];
            int cy = current[1];

            for (int[] direction : directions) {
                int nx = cx + direction[0];
                int ny = cy + direction[1];

                if (!isIn(nx, ny) || isVisited[nx][ny] || maps[nx].charAt(ny) == 'X') {
                    continue;
                }

                isVisited[nx][ny] = true;
                locations.add(new int[] {nx, ny});
                area += maps[nx].charAt(ny) - '0';
            }
        }

        return area;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}