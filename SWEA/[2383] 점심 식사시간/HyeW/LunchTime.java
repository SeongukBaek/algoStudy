import java.io.*;
import java.util.*;

public class LunchTime {
    static final int MAX_MOVING_COUNT = 3;
    static int N;
    static List<Person> people;
    static List<Stair> stairs;
    static int peopleSize;
    static boolean[] isSelected;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            N = Integer.parseInt(br.readLine());
            people = new ArrayList<>();
            stairs = new ArrayList<>();
            min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());

                    if (tmp == 1) {
                        people.add(new Person(i, j));
                    } else if (tmp > 1) {
                        stairs.add(new Stair(i, j, tmp));
                    }
                }
            }

            peopleSize = people.size();
            setPeople();

            isSelected = new boolean[peopleSize];
            downStair(0);

            sb.append("#" + t + " ").append(min).append("\n");
        }
        System.out.println(sb);

    }

    static void setPeople() {

        for (int i = 0; i < peopleSize; i++) {
            Person person = people.get(i);
            person.d1 = Math.abs(stairs.get(0).x - person.x) + Math.abs(stairs.get(0).y - person.y);
            person.d2 = Math.abs(stairs.get(1).x - person.x) + Math.abs(stairs.get(1).y - person.y);
        }

    }

    static void downStair(int r) {
        if (r == peopleSize) {
            min = Math.min(getTotalMovingTime(), min);
            return;
        }

        isSelected[r] = true;
        downStair(r + 1);
        isSelected[r] = false;
        downStair(r + 1);
    }

    static int getTotalMovingTime() {
        List<Move> stair1 = new LinkedList<>();
        List<Move> stair2 = new LinkedList<>();

        for (int i = 0; i < peopleSize; i++) {
            if (isSelected[i]) {
                stair1.add(new Move(people.get(i).d1, stairs.get(0).len));
            } else {
                stair2.add(new Move(people.get(i).d2, stairs.get(1).len));
            }
        }

        Collections.sort(stair1);
        Collections.sort(stair2);

        int stair1Time = getMovingTime(stair1);
        int stair2Time = getMovingTime(stair2);

        return Math.max(stair1Time, stair2Time);
    }

    static int getMovingTime(List<Move> stair) {
        int time = 0;
        int end = stair.size() - 1;

        while (stair.size() > 0) {
            if (stair.size() <= MAX_MOVING_COUNT) {
                end = stair.size() - 1;
                time += stair.get(end).room + stair.get(end).stair;
                break;
            }

            // 이동하기
            time += stair.get(0).room;
            moveAll(stair, stair.get(0).room);

            // 계단 내려가기
            time += stair.get(0).stair;
            moveAll(stair, stair.get(0).stair);

            // 다 내려간 사람 리스트에서 제거
            arrivalFloor(stair);
        }

        return time + 1;
    }

    static void moveAll(List<Move> stair, int dis) {

        for (int i = 0; i < stair.size(); i++) {
            int gap = stair.get(i).room - dis;
            // room은 음수가 되면 안됨
            stair.get(i).room = (gap > 0) ? gap : 0;

            if (i >= MAX_MOVING_COUNT) {
                continue;
            }

            if (gap < 0) {
                // stair은 음수가 될 수 있음
                stair.get(i).stair -= Math.abs(gap);
            }
        }
    }

    static void arrivalFloor(List<Move> stair) {

        for (int i = 0; i < MAX_MOVING_COUNT; i++) {
            if (stair.get(0).room == 0 && stair.get(0).stair <= 0) {
                stair.remove(0);
            } else {
                break;
            }
        }

    }

    static class Person {
        int x;
        int y;
        int d1 = 0;
        int d2 = 0;

        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Stair {
        int x;
        int y;
        int len;

        public Stair(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    static class Move implements Comparable<Move> {
        int room;
        int stair;

        public Move(int room, int stair) {
            this.room = room;
            this.stair = stair;
        }

        @Override
        public int compareTo(Move o) {
            return this.room - o.room;
        }
    }

}