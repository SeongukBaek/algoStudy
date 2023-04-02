import java.io.*;
import java.util.*;


public class Main {
    static int n, l, r;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Nation> unitedNations; // 같은 연합인 나라들
    static class Nation {
        int x, y, population;
        public Nation(int x, int y) {
            this.x = x;
            this.y = y;
            this.population = map[x][y];
        }
    }

    public static void main(String[] args) throws IOException {

        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        /* 실행 */
        System.out.println(getMovingPeriod());

    }

    /* 이동하는 날짜 구하기 */
    private static int getMovingPeriod() {
        int day = 0;
        while(true) {
            boolean isMoved = false;
            visited = new boolean[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(visited[i][j]) continue;
                    int sum = searchPopulation(i, j);
                    if(unitedNations.size() > 1) {
                        unionNations(sum);
                        isMoved = true;
                    }
                }
            }
            if(!isMoved) return day;
            day++;
        }
    }


    /* bfs : 인접한 나라끼리 연합할 수 있는지 탐색 */
    private static int searchPopulation(int startX, int startY) {
        unitedNations = new ArrayList<>();
        Deque<Nation> queue = new ArrayDeque<>();
        Nation nation = new Nation(startX, startY);
        queue.offer(nation);
        unitedNations.add(nation);
        visited[nation.x][nation.y] = true;
        int sum = nation.population;

        while(!queue.isEmpty()) {
            nation = queue.poll();

            for(int i=0; i<4; i++) {

                int nx = nation.x + dx[i];
                int ny = nation.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(visited[nx][ny]) continue;
                if(!checkPopulation(nation, map[nx][ny])) continue;

                Nation newNation = new Nation(nx, ny);
                sum += newNation.population;
                unitedNations.add(newNation);
                queue.offer(newNation);
                visited[newNation.x][newNation.y] = true;

            }
        }

        return sum;
    }

    /* 두 나라의 인구 차를 확인  */
    private static boolean checkPopulation(Nation nation, int population) {
        int difference = Math.abs(nation.population - population);
        return difference >= l && difference <= r ;
    }

    /* 국경선 열린 나라끼리 연합 */
    private static void unionNations(int sum) {
        int average = sum / unitedNations.size();
        for(Nation nation : unitedNations) {
            map[nation.x][nation.y] = average;
        }
    }

}