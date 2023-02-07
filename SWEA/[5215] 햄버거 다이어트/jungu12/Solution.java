import java.util.*;
import java.io.*;
 
class Solution {
    static int N;
    static int numLimit;
    static int calLimit;
    static int best;
    static int[][] ingredient;
     
    static void add(int idx, int taste, int sum) {
        if(sum > calLimit)
            return;
        if(taste > best)
            best = taste;
        if(idx == numLimit)
            return;
        add(idx+1, taste+ingredient[idx][0], sum+ingredient[idx][1]);
        add(idx+1, taste, sum);
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
         
        //test case 만큼 반복
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numLimit = Integer.parseInt(st.nextToken());
            calLimit = Integer.parseInt(st.nextToken());
            best = 0;
            ingredient = new int[numLimit][2];
            for(int j = 0; j < numLimit ; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < 2 ; k++) {
                    ingredient[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            add(0, 0, 0);
            System.out.println(String.format("#%d %d", i+1, best));
        }
    }
}