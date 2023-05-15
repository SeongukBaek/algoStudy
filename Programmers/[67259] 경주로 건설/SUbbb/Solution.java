import java.util.*;

class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int[][] costs;
    private int size;
    private int minCost = Integer.MAX_VALUE;
    private int[][] board;

    public int solution(int[][] board) {
        this.board = board;
        size = board.length;
        costs = new int[size][size];

        dfs(-1, 0, 0);

        return minCost;
    }

    private void dfs(int prevDir, int x, int y) {
        // 경주로 건설 완료
        if (x == size - 1 && y == size - 1) {
            minCost = Math.min(costs[x][y], minCost);
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int nx = x + directions[direction][0];
            int ny = y + directions[direction][1];

            // 다시 원점이거나, 맵 밖이거나, 벽인 경우 패스
            if (nx == 0 && ny == 0 || !isIn(nx, ny) || board[nx][ny] == 1) {
                continue;
            }

            // 건설 비용 계산
            int tmpCost = costs[x][y];

            // 방향에 따라 코너 계산
            if (prevDir != -1 && Math.abs(prevDir - direction) % 2 == 1) {
                tmpCost += 500;
            }

            // 두 좌표를 잇기 위해서는 직선 도로 하나가 사용됨
            tmpCost += 100;

            // 새로운 건설 비용보다 크거나, 해당 좌표까지의 건설 비용이 아직 갱신되지 않았다면 갱신
            if (costs[nx][ny] >= tmpCost || costs[nx][ny] == 0) {
                costs[nx][ny] = tmpCost;
                dfs(direction, nx, ny);
            }
        }
    }

    private boolean isIn(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}