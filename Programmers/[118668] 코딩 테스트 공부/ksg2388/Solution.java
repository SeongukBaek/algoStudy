import java.util.*;

class Solution {
    static class Problem {
        int x;
        int y;
        int time;

        Problem(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp, maxCop = cop;
        Queue<Problem> next = new PriorityQueue<>((o1, o2) -> {
            return o1.time - o2.time;
        });

        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        int[][] status = new int[maxAlp + 2][maxCop + 2];
        boolean[][] visited = new boolean[maxAlp + 2][maxCop + 2];
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                status[i][j] = Integer.MAX_VALUE;
            }
        }

        status[alp][cop] = 0;
        next.add(new Problem(alp, cop, 0));

        while (!next.isEmpty()) {
            Problem cur = next.poll();

            if (visited[cur.x][cur.y]) {
                continue;
            }
            visited[cur.x][cur.y] = true;

            if (status[cur.x + 1][cur.y] > status[cur.x][cur.y] + 1) {
                status[cur.x + 1][cur.y] = status[cur.x][cur.y] + 1;
                next.add(new Problem(cur.x + 1, cur.y, status[cur.x + 1][cur.y]));
            }
            if (status[cur.x][cur.y + 1] > status[cur.x][cur.y] + 1) {
                status[cur.x][cur.y + 1] = status[cur.x][cur.y] + 1;
                next.add(new Problem(cur.x, cur.y + 1, status[cur.x][cur.y + 1]));
            }

            for (int[] problem : problems) {
                // 현재 알고력과 코딩력이 모두 만족하는 경우
                if (problem[0] <= cur.x && problem[1] <= cur.y) {
                    int nx = cur.x + problem[2];
                    int ny = cur.y + problem[3];

                    if (nx > maxAlp) {
                        nx = maxAlp;
                    }
                    if (ny > maxCop) {
                        ny = maxCop;
                    }
                    if (status[nx][ny] > status[cur.x][cur.y] + problem[4]) {
                        status[nx][ny] = status[cur.x][cur.y] + problem[4];
                        next.add(new Problem(nx, ny, status[nx][ny]));
                    }
                }
            }
        }

        return status[maxAlp][maxCop];
    }
}