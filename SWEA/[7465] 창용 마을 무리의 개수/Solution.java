import java.io.*;
import java.util.*;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int N, M;
    static List<List<Integer>> input;
    static boolean[] visited;
 
    static void input() throws IOException {
        input = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            input.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            input.get(a).add(b);
            input.get(b).add(a);
        }
    }
 
    static void makeGroup(int num) {
        visited[num] = true;
        for (int person : input.get(num)) {
            if (!visited[person]) {
                makeGroup(person);
            }
        }
    }
 
    static int countGroup() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                makeGroup(i);
                cnt++;
            }
        }
        return cnt;
    }
 
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            System.out.println(String.format("#%d %d", i+1, countGroup()));
        }
    }
}