import java.util.*;
import java.io.*;
 
class Solution {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 1; tc <= t; ++tc) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            Set<Integer> scores = new HashSet<>(Arrays.asList(0));
             
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                List<Integer> temp = new ArrayList<>(scores);
                for (int j = 0; j < temp.size(); j++) {
                    scores.add(temp.get(j) + num);
                }
            }
             
            sb.append("#").append(tc).append(" ").append(scores.size()).append("\n");
        }
        System.out.println(sb.toString());
    }
}