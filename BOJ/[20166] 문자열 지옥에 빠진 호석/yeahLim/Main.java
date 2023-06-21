import java.io.*;
import java.util.*;

class Main {

    static int n, m, k;
    static char[][] map;
    static String[] stringSeq;
    static int[] count;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static Map<String, Integer> stringCnt = new HashMap<>();

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String info = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = info.charAt(j);
            }
        }

        stringSeq = new String[k];
        count = new int[k];
        for (int i = 0; i < k; i++) {
            stringSeq[i] = br.readLine();
            stringCnt.put(stringSeq[i], 0);
        }
        
        /* DFS : 각 위치의 모든 문자열의 경우 찾기 */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(map[i][j]);
                searchString(i, j, sb);
            }
        }

        /* 출력 */
        for (String str : stringSeq) {
            System.out.println(stringCnt.get(str));
        }
    }

    private static void searchString(int curX, int curY, StringBuilder sb) {
        String str = sb.toString();
        if (stringCnt.containsKey(str)) {
            stringCnt.put(str, stringCnt.get(str) + 1);
        }

        if (sb.length() >= 5) {
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nx = curX + dx[i];
            int ny = curY + dy[i];

            nx = nx == -1 ? n - 1 : nx;
            nx = nx == n ? 0 : nx;
            ny = ny == -1 ? m - 1 : ny;
            ny = ny == m ? 0 : ny;

            sb.append(map[nx][ny]);
            searchString(nx, ny, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}