import java.util.Queue;
import java.util.LinkedList;

class Solution {
    // 최소 시간으로 레버가 있는 칸으로 이동한 후, 목적지로 이동하기
    // -> 레버에서 출발해서, 시작 지점과 출구를 모두 만나는 최소 시간 찾기
    private String[] maps;
    private final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int row;
    private int col;

    public int solution(String[] maps) {
        this.maps = maps;
        this.row = maps.length;
        this.col = maps[0].length();

        int startX = 0;
        int startY = 0;

        int leverX = 0;
        int leverY = 0;

        int endX = 0;
        int endY = 0;

        for (int x = 0; x < row; x++) {
            String map = maps[x];
            for (int y = 0; y < col; y++) {
                char current = map.charAt(y);

                if (current == 'S') {
                    startX = x;
                    startY = y;
                }

                if (current == 'L') {
                    leverX = x;
                    leverY = y;
                }

                if (current == 'E') {
                    endX = x;
                    endY = y;
                }
            }
        }

        // 출발점에서 레버까지 가는 최소 시간
        int toLever = findTarget(startX, startY, leverX, leverY);
        if (toLever == -1) {
            return -1;
        }

        // 레버에서 출구까지 가는 최소 시간
        int toEnd = findTarget(leverX, leverY, endX, endY);
        if (toEnd == -1) {
            return -1;
        }

        return toLever + toEnd;
    }

    /**
     * 주어진 좌표에서 목적지까지 가는 데 걸리는 최소 시간을 반환
     * */
    private int findTarget(int x, int y, int targetX, int targetY) {
        boolean[][] isVisited = new boolean[row][col];
        Queue<int[]> locations = new LinkedList<>();
        locations.add(new int[]{x, y});
        isVisited[x][y] = true;

        int cost = 0;
        boolean canMove;

        while (!locations.isEmpty()) {
            int size = locations.size();
            canMove = false;

            for (int count = 0; count < size; count++) {
                int[] current = locations.poll();
                int curX = current[0];
                int curY = current[1];

                for (int[] move : moves) {
                    int nx = curX + move[0];
                    int ny = curY + move[1];

                    if (!isInMap(nx, ny) || isVisited[nx][ny] || maps[nx].charAt(ny) == 'X') {
                        continue;
                    }

                    if (nx == targetX && ny == targetY) {
                        return cost + 1;
                    }

                    canMove = true;
                    // 방문 처리를 미리 해줘야 큐에 중복 값이 들어가지 않음!!
                    isVisited[nx][ny] = true;
                    locations.add(new int[] {nx, ny});
                }
            }

            if (!canMove) {
                break;
            }
            cost++;
        }

        return -1;
    }

    private boolean isInMap(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}