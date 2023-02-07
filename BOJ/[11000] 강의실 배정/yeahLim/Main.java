import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] time = new int[n][2];
        for(int i=0; i<n; i++) {
            String[] str = br.readLine().split(" ");
            time[i][0] = Integer.parseInt(str[0]);
            time[i][1] = Integer.parseInt(str[1]);
        }

        /* 시작시간 오름차순으로 정렬*/
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 끝나는 시간은 pq에 들어가기 때문에 정렬 필요 x
                /*if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else*/
                    return o1[0] - o2[0];
            }
        });

        /* 시작 시간이 전 시간의 끝나는 시간보다 같거나 크면 제거 */
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위 작은 값
        pq.offer(time[0][1]);
        for(int i=1; i<n; i++) {
            if(pq.peek() <= time[i][0])
                pq.poll();
            pq.offer(time[i][1]);
        }

        /* 출력 */
        System.out.println(pq.size());

    }
}
