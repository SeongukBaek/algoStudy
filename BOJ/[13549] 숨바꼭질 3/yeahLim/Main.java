import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infor = br.readLine().split(" ");
        System.out.println(findSister(Integer.parseInt(infor[0]), Integer.parseInt(infor[1])));
    }

    /* BFS : 동생 찾는 시간 구하기 */
    private static int findSister(int n, int k) {
        Deque<int[]> q = new ArrayDeque<>();
        int[] visited = new int[100_001];
        q.offer(new int[]{n, 1}); // 위치, 시간
        visited[n] = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            int next = cur[0] - 1;
            if(next >= 0 && (visited[next] > cur[1] || visited[next] == 0)) {
                visited[next] = cur[1] + 1;
                q.offer(new int[] {next, cur[1] + 1});
            }
            
            next = cur[0] + 1;
            if(next <= 100000 && (visited[next] > cur[1] || visited[next] == 0)) {
                visited[next] = cur[1] + 1;
                q.offer(new int[]{next, cur[1] + 1});
            }
            
            next = cur[0] * 2;
            if(next <= 100000 && (visited[next] > cur[1] || visited[next] == 0)) {
                visited[next] = cur[1];
                q.offer(new int[] {next, cur[1]});
            }
        }

        return visited[k] - 1;
    }
}