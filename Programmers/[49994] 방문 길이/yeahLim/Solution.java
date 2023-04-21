class Solution {
    public int solution(String dirs) {
                int curX = 5;
        int curY = 5;
        int nextX, nextY;
        boolean[][][] visited = new boolean[11][11][4];
        int d;
        int answer = 0;

        for (char dir : dirs.toCharArray()) {
            nextX = curX;
            nextY = curY;

            if (dir == 'U' && nextX < 10) {
                nextX += 1;
                d = 0;
            } else if (dir == 'D' && nextX > 0) {
                nextX -= 1;
                d = 1;
            } else if (dir == 'R' && nextY < 10) {
                nextY += 1;
                d = 2;
            } else if (dir == 'L' && nextY > 0) {
                nextY -= 1;
                d = 3;
            }
            else continue;


            if (!visited[nextX][nextY][d]) {
                visited[nextX][nextY][d] = true;
                
                // 위, 오른쪽일 때
                if(d == 0 || d == 2) {
                    visited[curX][curY][d+1] = true;
                }
                // 아래, 왼쪽일 때
                else visited[curX][curY][d-1] = true;
                answer++;
            }
            curX = nextX;
            curY = nextY;

        }
        return answer;
    }
}