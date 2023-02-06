import java.util.*;
import java.io.*;
  
public class swea5215 {
  
    private static int t;
    private static int n;
    private static int l;
    private static int[][] foods;
    private static int result;
  
    public static void main(String[] args) throws IOException {
 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
 
            foods = new int[n][2];
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                foods[i][0] = Integer.parseInt(st.nextToken());
                foods[i][1] = Integer.parseInt(st.nextToken());
            }
             
            result = 0;
            calculatePoint(0, 0, 0);
 
            System.out.println("#" + tc + " " + result);
        }
    }
  
    private static void calculatePoint(int curCalorie, int point, int idx) {
        // 제한된 칼로리보다 많아지는 경우 종료 
        if (curCalorie > l) {
            return;
        }
        if (idx == n) {
            if (result < point)
                result = point;
            return;
        }
 
        calculatePoint(curCalorie + foods[idx][1], point + foods[idx][0], idx + 1);
        calculatePoint(curCalorie, point, idx + 1);
 
    }
  
}