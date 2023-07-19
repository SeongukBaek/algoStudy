import java.util.*;

class Solution {
    static class Time {
        int start;
        int end;

        Time(String start, String end) {
            this.start = parseTime(start);
            this.end = parseTime(end);
        }

        int parseTime(String time) {
            int[] values = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
            return values[0] * 60 + values[1];
        }
    }

    private List<Integer> endTimes;

    public int solution(String[][] book_time) {
        Queue<Time> times = new PriorityQueue<>(Comparator.comparingInt(t -> t.start));
        endTimes = new ArrayList<>();

        for (String[] bookTime : book_time) {
            times.add(new Time(bookTime[0], bookTime[1]));
        }

        int answer = 0;
        while (!times.isEmpty()) {
            if (!canAssignPrevRoom(times.poll())) {
                answer++;
            }
        }

        return answer;
    }

    /**
     * 이전에 사용한 방들의 퇴실 시각과 비교해서, 이전에 사용한 방의 청소를 끝낸 후 사용할 수 있는지 반환
     * */
    private boolean canAssignPrevRoom(Time time) {
        if (!endTimes.isEmpty()) {
            for (int index = endTimes.size() - 1; index >= 0; index--) {
                if (endTimes.get(index) + 10 <= time.start) {
                    endTimes.set(index, time.end);
                    return true;
                }
            }
        }

        endTimes.add(time.end);
        return false;
    }
}