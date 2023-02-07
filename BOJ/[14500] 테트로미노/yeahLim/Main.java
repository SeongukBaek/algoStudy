import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] paper;
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] visited;
    static int[][] v;
    static int result = 0;


    public static void main(String[] args) throws IOException {

        /* 입력 */
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new int[n][m];
        v = new int[n][m];
        paper = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // paper[i] = new int[m];         // 객체 생성안해주면 nullpointerException 발생..
            for(int j = 0; st.hasMoreTokens(); j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }

        /* 구현 */
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                dfs(i, j, 0, 0);
                combi(i, j, 0, paper[i][j]);  // ㅏ,ㅓ,ㅗ,ㅜ
            }

        /* 출력 */
        System.out.println(result);
    }

    static void dfs(int i, int j, int depth, int total) {

        if (depth == 4){
            if(total > result)
                result = total;
            return;
        }

        for(int k=0; k<4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if ((0 <= x && x < n && 0 <= y && y < m) && visited[x][y] == 0) {
                visited[x][y] = 1;
                dfs(x, y, depth+1, total+paper[x][y]);
                visited[x][y] = 0;
            }
        }
    }

    static void combi(int i, int j, int depth, int total) {
        if(depth == 3){
            if(total > result)
                result = total;
            return ;
        }

        for(int k=0; k<4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if((0 <= x && x < n && 0 <= y && y < m) && v[x][y] == 0) {
                v[x][y] = 1;
                combi(i, j, depth+1, total+paper[x][y]);
                v[x][y] = 0;
            }
        }

    }
}
