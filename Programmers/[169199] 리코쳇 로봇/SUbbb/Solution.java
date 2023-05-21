import java.util.*;

class Solution {
    // 왔던 방향과 좌표에 대한 방문 처리를 수행해서 종료 조건
    private boolean[][][] isVisited;
    // 상, 우, 하, 좌
    private final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private String[] board;
    private int row;
    private int col;

    public int solution(String[] board) {
        this.board = board;
        row = board.length;
        col = board[0].length();
        isVisited = new boolean[row][col][4];

        return findMinMove(findRobot());
    }

    private int[] findRobot() {
        int x = 0;
        int y = 0;
        boolean isRobot = false;
        for (; x < row; x++) {
            for (y = 0; y < col; y++) {
                if (board[x].charAt(y) == 'R') {
                    isRobot = true;
                    break;
                }
            }
            if (isRobot) {
                break;
            }
        }
        return new int[] {x, y};
    }

    private int findMinMove(int[] robot) {
        int cost = 0;
        Queue<int[]> robots = new ArrayDeque<>();
        isVisited[robot[0]][robot[1]][0] = isVisited[robot[0]][robot[1]][1] = isVisited[robot[0]][robot[1]][2] = isVisited[robot[0]][robot[1]][3] = true;
        robots.add(new int[] {robot[0], robot[1], cost});

        while (!robots.isEmpty()) {
            int[] current = robots.poll();

            // 4 방향 탐색 후, 이동 가능한 경우를 큐에 삽입
            // 이때 이미 해당 좌표에 해당 방향으로 방문한 경우는 제외
            for (int direction = 0; direction < 4; direction++) {
                int[] newLocation = move(current[0], current[1], direction);

                // 해당 방향으로 이동했을 때, 이미 해당 좌표에 해당 방향으로 방문한 적 있는 경우는 처리하지 않는다.
                if (isVisited[newLocation[0]][newLocation[1]][direction]) {
                    continue;
                }

                if (board[newLocation[0]].charAt(newLocation[1]) == 'G') {
                    return current[2] + 1;
                }

                isVisited[newLocation[0]][newLocation[1]][direction] = true;
                robots.add(new int[] {newLocation[0], newLocation[1], current[2] + 1});
            }
        }

        return -1;
    }

    private int[] move(int x, int y, int direction) {
        int nx = x + directions[direction][0];
        int ny = y + directions[direction][1];

        // 게임판 안이면서, 장애물을 만나기전까지 이동
        while (isIn(nx, ny) && board[nx].charAt(ny) != 'D') {
            nx += directions[direction][0];
            ny += directions[direction][1];
        }

        return new int[] {nx - directions[direction][0], ny - directions[direction][1]};
    }

    private boolean isIn(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}