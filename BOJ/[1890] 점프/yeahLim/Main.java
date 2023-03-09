import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] board;
    static long[][] routeCnt; // 루트의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        routeCnt = new long[n][n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        routeCnt[0][0] = 1;
        System.out.println(jump());
    }

    /* dp로 경로의 개수 구하기 */
    static long jump() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++){

                // 점프의 길이가 0일때
                if(board[i][j] == 0) {
                    continue;
                }

                int x = i + board[i][j];
                int y = j + board[i][j];

                // 아래로 갈 수 있을 때
                if (x < n) {
                    routeCnt[x][j] += routeCnt[i][j]; // 현재 경로의 개수를 더해준다
                }

                // 오른쪽으로 갈 수 있을 때
                if (y < n) {
                    routeCnt[i][y] += routeCnt[i][j];
                }
            }
        }

        return routeCnt[n-1][n-1];
    }

}
