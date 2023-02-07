import java.io.*;
import java.util.*;

public class Tetromino {
    static int[] dr = {-1, 1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] arr;
    static int r, c;
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[r][c];

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean[][] visited = new boolean[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++)
            {
                visited[i][j] = true;
                dfs(i,j,0,arr[i][j], visited);
                visited[i][j] = false;
                another(i,j);
            }
        }

        System.out.println(max);

    }

    //사방탐색
    static void dfs(int x, int y, int d, int sum, boolean[][] visited) {

        if(d==3) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i <4; i++) {
            int dx = x + dr[i];
            int dy = y + dc[i];

            if(dx < 0 || dy < 0 || dx >= r || dy >= c)
                continue;
            if(visited[dx][dy])
                continue;

            visited[dx][dy] =true;
            dfs(dx, dy, d+1, sum+arr[dx][dy], visited);
            visited[dx][dy] =false;
        }

    }

    static void another(int x, int y) {
        if(x-1 >= 0 && y+2 < c)
            max = Math.max(arr[x][y] + arr[x][y+1] + arr[x][y+2] + arr[x-1][y+1],max);

        if(x-2 >= 0 && y-1 >= 0)
            max = Math.max(arr[x][y] + arr[x-1][y] + arr[x-2][y] + arr[x-1][y-1],max);

        if(x+1 < r && y-2 >= 0)
            max = Math.max(arr[x][y] + arr[x][y-1] + arr[x][y-2] + arr[x+1][y-1],max);

        if(x+2 < r && y+1  < c)
            max = Math.max(arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y+1],max);
    }
}