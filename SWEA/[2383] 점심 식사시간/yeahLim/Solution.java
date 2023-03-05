import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n;
    static int[][] map;
    static class Person {
        int x, y;
        int time = 0; // 이동 시간
        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Person> people;
    static class Stair {
        int x, y;
        int length = 0; // 계단 길이
        Queue<Person> ppl = new PriorityQueue<>(((o1, o2) -> {return o1.time - o2.time;}));
        public Stair(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
    static Stair[] stairs;
    static int minTime;

    public static void main(String[] args) throws IOException {

        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int t=1; t<=tc; t++) {
            n = Integer.parseInt(br.readLine());
            stairs = new Stair[2];
            people = new ArrayList<>();
            int pplCnt = 0;
            map = new int[n][n];
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    pplCnt++;
                    // 사람일때
                    if(map[i][j] == 1) {
                        people.add(new Person(i, j));
                    }
                    // 계단일때
                    else if(map[i][j] > 1) {
                        if(stairs[0] == null) {
                            stairs[0] = new Stair(i, j, map[i][j]);
                        }
                        else {
                            stairs[1] = new Stair(i, j, map[i][j]);
                        }

                    }
                }
            }
            minTime = Integer.MAX_VALUE;

            /* 실행 */
            for(int i=0; i<pplCnt; i++) {
                combination(0, 0, i, new boolean[pplCnt]);
            }

            /* 출력 */
            System.out.println("#" +t + " " + minTime);
        }
    }

    /* n명의 사람을 뽑는다 */
    static void combination(int start, int count, int n, boolean[] selected) {
        if(count == n) {
            goToStairEntrance(selected);
            return;
        }

        for(int i=start; i<n; i++) {
            if(!selected[i]) {
                selected[i] = true;
                combination(i+1, count+1, n, selected);
                selected[i] = false;
            }

        }
    }

    /* selected인 사람은 stair[0]으로 가고, 아닌 사람은 stair[1]로 간다 */
    static void goToStairEntrance(boolean[] selected) {
        for(int i=0; i<people.size(); i++) {

            Person p = people.get(i);

            // stair[0]까지의 이동 시간
            if(selected[i]) {
                p.time = Math.abs(p.x - stairs[0].x) + Math.abs(p.y - stairs[0].y);
                stairs[0].ppl.add(p);
            }

            // stair[1]까지의 이동 시간
            else {
                p.time = Math.abs(p.x - stairs[0].x) + Math.abs(p.y - stairs[0].y);
                stairs[1].ppl.add(p);
            }
        }
        goDownStairs(selected);
    }
    
    /* 계단을 내려간다 */
    static void goDownStairs(boolean[] selected) {
        for(int i=0; i<people.size(); i++){
            if(selected[i]) {
                people.get(i).time += stairs[0].length + 1;

            }
            else {
                people.get(i).time += stairs[1].length + 1;
            }
        }
        searchMinTime();
    }

    /* 최소 시간 탐색 */
    static void searchMinTime() {
        int totalTime = 0; // 전체 이동 시간
        for(int i=0; i<people.size(); i++) {
            totalTime = Math.max(totalTime, people.get(i).time);
        }
        // 전체 이동 시간중에 가장 짧은 시간
        minTime = Math.min(minTime, totalTime);
    }
}
