import java.io.*;
import java.util.*;

public class ClassroomAssignment {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> lecture = new PriorityQueue<>();
        PriorityQueue<Integer> room = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lecture.add(new Lecture(start, end));
        }

        room.add(lecture.poll().end);
        while(!lecture.isEmpty()){
            Lecture cur = lecture.poll();

            if(room.peek() <= cur.start)
                room.poll();

            room.add(cur.end);
        }

        System.out.println(room.size());
    }

    static class Lecture implements Comparable<Lecture>{
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start;
        }

    }
}