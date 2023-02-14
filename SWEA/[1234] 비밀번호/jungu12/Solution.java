import java.io.*;
import java.util.*;
 
public class Solution {
    static List<Integer> pw;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String input = st.nextToken();
        pw = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            pw.add(Integer.parseInt(input.charAt(i) + ""));
        }
    }
 
    static void findPw() {
        check(0);
    }
 
    static void check(int idx) {
        if (idx >= pw.size() - 1) {
            return;
        }
        if (pw.get(idx) == pw.get(idx + 1)) {
            for (int i = 0; i < 2; i++) {
                pw.remove(idx);
            }
            check((idx - 1 < 0) ? 0 : idx-1);
        } else {
            check(idx + 1);
        }
    }
 
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 10 ; i++) {
            input();
            findPw();
            System.out.print("#"+ (i+1) +" ");
            for(int a : pw) {
                System.out.print(a);
            }System.out.println();
        }
    }
}