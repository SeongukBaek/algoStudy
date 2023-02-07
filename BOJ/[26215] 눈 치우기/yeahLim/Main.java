import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(str[i]);
        int count = 0;

        if(n == 1)
            count = arr[0];
        else {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 : 큰 숫자
            for (int i = 0; i < n; i++)
                pq.offer(arr[i]);
            while (pq.peek() != 0) {
                int tmp1 = pq.poll() - 1;
                int tmp2 = pq.poll() - 1;
                pq.offer(tmp1);
                pq.offer(tmp2);
                count++;
            }
        }

        if(count > 1440)
            System.out.println(-1);
        else
            System.out.println(count);
    }
}
