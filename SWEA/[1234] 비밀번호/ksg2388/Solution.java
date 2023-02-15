import java.util.*;
import java.io.*;
 
class Solution {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
 
        for (int tc = 1; tc <= 10; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Stack<Character> stack = new Stack<>();
            int n = Integer.parseInt(st.nextToken());
            String code = st.nextToken();
             
            for (int i = 0; i < n; i++) {
                if (stack.isEmpty()) {
                    stack.add(code.charAt(i));
                    continue;
                }
                if (stack.peek() == code.charAt(i)) {
                    stack.pop();
                    continue;
                }
                stack.add(code.charAt(i));
            }
             
            sb.append("#" + tc + " ");
            for (char c: stack) {
                sb.append(c);
            }
            sb.append("\n");
        }
         
        System.out.println(sb.toString());
    }
}