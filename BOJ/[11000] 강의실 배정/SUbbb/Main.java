import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class ClassTime implements Comparable<ClassTime> {
        int start;
        int end;

        ClassTime(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * 강의 시작 시간 기준으로 오름차순, 강의 종료 시간 기준으로 오름차순 정렬
         * @param ct 비교대상
         * */
        @Override
        public int compareTo(ClassTime ct) {
            if (this.start == ct.start) {
                return this.end - ct.end;
            }
            return this.start - ct.start;
        }
    }
    private static final Queue<ClassTime> times = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for (int index = 0; index < N; index++) {
            st = new StringTokenizer(br.readLine());
            times.add(new ClassTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(countRooms());
    }

    /**
     * 정렬된 강의 시간 정보를 탐색하면서, 가장 먼저 끝나는 강의 시간에 이어서 진행할 수 있다면, 큐를 갱신한다.
     * 그렇지 않은 경우는 현재 강의의 끝나는 시간을 큐에 삽입한다.
     * 
     * @return int
     * */
    private static int countRooms() {
        Queue<Integer> endTimes = new PriorityQueue<>();
        endTimes.add(times.poll().end);
        while (!times.isEmpty()) {
            ClassTime current = times.poll();
            if (current.start >= endTimes.peek()) {
                endTimes.poll();
            }
            endTimes.add(current.end);
        }
        return endTimes.size();
    }
}