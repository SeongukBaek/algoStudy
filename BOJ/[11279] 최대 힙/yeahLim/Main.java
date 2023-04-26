import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        
        /* Heap : priorityqueue로 최대 힙 구하기 */
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                if(pq.isEmpty()) {
                    sb.append("0\n");
                }
                else {
                    sb.append(pq.poll()).append("\n");
                }
            }
            else {
                pq.offer(num);
            }
        }
        
        /* 출력 */
        System.out.println(sb);
    }

}