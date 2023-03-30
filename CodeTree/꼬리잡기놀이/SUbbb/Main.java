import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Location {
        int x;
        int y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Team {
        Deque<Location> members = new ArrayDeque<>();
        boolean isOrigin;

        Team() {
            isOrigin = true;
        }
    }

    private static int n;
    private static int m;
    private static int k;
    private static int[][] map;
    private static List<Team> teams;
    private static final int[][] moves = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        teams = new ArrayList<>();
        for (int index = 0; index < m; index++) {
            teams.add(new Team());
        }
        int index = 0;

        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < n; y++) {
                int number = Integer.parseInt(st.nextToken());
                map[x][y] = number;
                if (number == 1) {
                    teams.get(index++).members.add(new Location(x, y));
                }
            }
        }

        // 경로를 Deque으로 저장
        boolean[][] isVisited = new boolean[n][n];
        for (index = 0; index < m; index++) {
            Location head = teams.get(index).members.getFirst();
            Queue<Location> locations = new LinkedList<>();
            locations.add(head);
            isVisited[head.x][head.y] = true;

            while (!locations.isEmpty()) {
                Location current = locations.poll();

                for (int[] move : moves) {
                    int nx = current.x + move[0];
                    int ny = current.y + move[1];

                    if (!isIn(nx, ny) || isVisited[nx][ny] || map[nx][ny] == 0 || map[nx][ny] == 1) {
                        continue;
                    }

                    Location nL = new Location(nx, ny);
                    if (map[nx][ny] <= map[current.x][current.y] + 1) {
                        teams.get(index).members.addLast(nL);
                        locations.add(nL);
                        isVisited[nx][ny] = true;
                    }
                }
            }
        }

        for (int round = 0; round < k; round++) {
            // 1. 각 팀별 한칸이동
            moveTeams();
            // 2. 공 던지고, 점수 계산
            computeScore(throwBall(round));
        }

        System.out.print(score);
    }

    /**
     * 각 팀을 이동시킨다. 이 때, 제일 앞 사람부터 이동시킬지, 꼬리 사람부터 이동시킬지에 따른 함수 호출
     * */
    private static void moveTeams() {
        for (Team team : teams) {
            // 1번부터 이동
            if (team.isOrigin) {
                moveForward(team.members);
            }
            // 3번부터 이동
            if (!team.isOrigin) {
                moveBackward(team.members);
            }
        }
    }

    /**
     * 해당 팀의 경로 전체를 머리 사람 방향으로 한 칸씩 이동시킨다.
     * */
    private static void moveForward(Deque<Location> members) {
        Location movePoint = members.pollFirst();
        int firstValue = map[movePoint.x][movePoint.y];
        Location lastPoint = members.getLast();
        Location current;

        int size = members.size();
        while (size-- > 0) {
            current = members.pollFirst();

            map[movePoint.x][movePoint.y] = map[current.x][current.y];
            members.addLast(new Location(movePoint.x, movePoint.y));

            movePoint = current;
        }

        map[lastPoint.x][lastPoint.y] = firstValue;
        members.addFirst(lastPoint);
    }

    /**
     * 해당 팀의 경로 전체를 꼬리 사람 방향으로 한 칸씩 이동시킨다.
     * */
    private static void moveBackward(Deque<Location> members) {
        Location movePoint = members.pollLast();
        int lastValue = map[movePoint.x][movePoint.y];
        Location firstPoint = members.getFirst();
        Location current;

        int size = members.size();
        while (size-- > 0) {
            current = members.pollLast();

            map[movePoint.x][movePoint.y] = map[current.x][current.y];
            members.addFirst(new Location(movePoint.x, movePoint.y));

            movePoint = current;
        }

        map[firstPoint.x][firstPoint.y] = lastValue;
        members.addLast(firstPoint);
    }

    /**
     * 라운드별 공의 위치를 정하고, 해당 공에 맞는 지점을 반환
     * */
    private static int[] throwBall(int round) {
        while (4 * n <= round) {
            round -= (4 * n);
        }
        int direction = round / n;
        int remain = round % n;
        Location ball = initBall(direction, remain);

        int nx = ball.x;
        int ny = ball.y;

        if (0 < map[nx][ny] && map[nx][ny] < 4) {
            return new int[] {nx, ny};
        }
        for (int index = 0; index < n; index++) {
            nx += moves[direction][0];
            ny += moves[direction][1];

            if (!isIn(nx, ny)) {
                continue;
            }
            if (0 < map[nx][ny] && map[nx][ny] < 4) {
                break;
            }
        }

        // nx, ny가 공에 맞는 지점
        return new int[] {nx, ny};
    }

    private static Location initBall(int direction, int remain) {
        if (direction == 0) {
            return new Location(remain, 0);
        }
        if (direction == 2) {
            return new Location(n - (remain + 1), n - 1);
        }
        if (direction == 1) {
            return new Location(n - 1, remain);
        }
        return new Location(0, n - (remain + 1));
    }

    /**
     * 각 팀마다 탐색하면서, 공이 맞는 지점에 있는 사람을 찾고, 해당 사람이 팀 내에서 몇 번째인지 구해 점수를 계산
     * */
    private static void computeScore(int[] location) {
        int x = location[0];
        int y = location[1];
        boolean findTarget = false;

        // 점수 계산
        for (Team team : teams) {
            Deque<Location> members = team.members;
            int index = 1;
            Iterator<Location> member = null;

            // 1번부터 차례 계산
            if (team.isOrigin) {
                member = members.iterator();
            }
            // 3번부터 차례 계산
            if (!team.isOrigin) {
                member = members.descendingIterator();
            }

            while (member.hasNext()) {
                Location current = member.next();
                if (map[current.x][current.y] == 4) {
                    continue;
                }

                if (current.x == x && current.y == y) {
                    score += index * index;
                    team.isOrigin = !team.isOrigin;
                    findTarget = true;
                    break;
                }
                index++;
            }

            if (findTarget) {
                break;
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}