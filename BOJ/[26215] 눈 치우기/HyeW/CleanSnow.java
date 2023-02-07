import java.io.*;
import java.util.*;

public class CleanSnow {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> snow = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            snow.add(Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;
        while(!snow.isEmpty()) {

            if(snow.size() == 1){
                cnt += snow.poll();
                break;
            }
            if(cnt > 1440)
                break;

            int a = snow.poll();
            int b = snow.poll();

            cnt += b;

            if(a != b)
                snow.add(a-b);
        }

        if(cnt > 1440)
            System.out.println(-1);
        else
            System.out.println(cnt);
    }

}