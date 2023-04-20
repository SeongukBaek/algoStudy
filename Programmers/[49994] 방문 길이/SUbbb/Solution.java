class Solution {
    private int[][] map = new int[11][11];
    // 각 방향별로 온 적 있는지 저장
    private boolean[][][] isVisited = new boolean[11][11][4];
    private int x = 5;
    private int y = 5;
    private int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int length;

    public int solution(String dirs) {
        traverse(dirs);
        return length;
    }

    private void traverse(String dirs) {
        for (int index = 0; index < dirs.length(); index++) {
            int direction = convertToInt(dirs.charAt(index));

            int nx = x + moves[direction][0];
            int ny = y + moves[direction][1];

            // 맵밖으로 나가는 경우, 무시
            if (!isIn(nx, ny)) {
                continue;
            }

            // 방문한 적 없는 경우
            if (!isVisited[nx][ny][direction]) {
                length++;
                isVisited[nx][ny][direction] = true;
                isVisited[x][y][oppositeDirection(direction)] = true;
            }

            x = nx;
            y = ny;
        }
    }

    private int convertToInt(char current) {
        if (current == 'U') {
            return 0;
        }
        if (current == 'D') {
            return 1;
        }
        if (current == 'R') {
            return 2;
        }
        return 3;
    }

    // 0 <-> 1 / 2 <-> 3
    private int oppositeDirection(int direction) {
        if (direction % 2 == 0) {
            return direction + 1;
        }
        return direction - 1;
    }

    private boolean isIn(int x, int y) {
        return x >= 0 && x < 11 && y >= 0 && y < 11;
    }
}