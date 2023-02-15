import java.util.*;
import java.io.*;
 
class Solution {
    static int[][] people;
    static boolean[] check;
 
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
         
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 사람의 수
            int m = Integer.parseInt(st.nextToken()); // 관계의 수
            int count = 0;
            people = new int[n][n];
            check = new boolean[n]; // 관계를 확인했는지 확인하는 배열
 
            for (int i = 0; i < m; ++i) {
                st = new StringTokenizer(br.readLine());
                int m1 = Integer.parseInt(st.nextToken()) - 1;
                int m2 = Integer.parseInt(st.nextToken()) - 1;
                people[m1][m2] = 1;
                people[m2][m1] = 1;
            }
 
            for (int i = 0; i < n; i++) {
                if (!check[i]) {
                    checkFriends(i, n);
                    count++;
                }
            }
             
            for (int i = 0; i < n; i++) {
                if (!check[i]) {
                    count++;
                }
            }
            sb.append("#" + tc + " " + count + "\n");
        }
        System.out.println(sb.toString());
    }
 
    private static void checkFriends(int idx, int n) {
        if (check[idx]) {
            return;
        }
         
        check[idx] = true;
        for (int i = 0; i < n; i++) {
            // 아는 사이면
            if (people[idx][i] == 1) {
                if (!check[i]) {
                    checkFriends(i, n);
                }
            }
        }
    }
}