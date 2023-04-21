import java.io.*;
import java.util.*;

public class Main {
    static String answer;

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            String[] number = br.readLine().split(" ");
            convertNumber(Integer.parseInt(number[0]), Integer.parseInt(number[1]));
            System.out.println(answer);
        }
    }

    /* BFS : 시작 숫자에서 끝 숫자를 목표로 숫자 변환하기 */
    private static void convertNumber(int startNum, int endNum) {
        Deque<String> oq = new ArrayDeque<>(); // 명령어 큐
        Deque<Integer> nq = new ArrayDeque<>(); // 숫자 큐
        boolean[] visited = new boolean[10000];
        oq.offer("");
        nq.offer(startNum);

        while(!oq.isEmpty()) {
            int curNum = nq.poll();
            String order = oq.poll();

            if(curNum == endNum) {
                answer = order;
                return;
            }

            int nextNum;
            // D
            nextNum = (curNum * 2) % 10000;
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                nq.offer(nextNum);
                oq.offer(order + "D");
            }
            // S
            nextNum = (curNum == 0) ? 9999 : curNum - 1;
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                nq.offer(nextNum);
                oq.offer(order + "S");
            }
            // L
            nextNum = (curNum % 1000) * 10 + (curNum / 1000);
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                nq.offer(nextNum);
                oq.offer(order + "L");
            }
            // R
            nextNum = (curNum % 10) * 1000 + (curNum / 10);
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                nq.offer(nextNum);
                oq.offer(order + "R");
            }
        }
    }
}