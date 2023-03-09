import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = new int[10][10];
    static int[] paper = {5, 5, 5, 5, 5};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0; i<10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /* 실행 */
        searchBoard(0, 0, 0);

        /* 출력 */
        if(answer == Integer.MAX_VALUE) answer = -1; // 색종이 못 붙이는 경우
        System.out.println(answer);
    }

    /* 보드에서 붙일 수 있는 공간 찾기 */
    static void searchBoard(int x, int y, int total) {
        if(x == 10 && y == 0) {
            // 색종이의 최소 개수 구하기
            answer = Math.min(answer, total);
            return;
        }

        if(total >= answer) {
            return;
        }

        if(y == 10) {
            searchBoard(x+1, 0, total);
            return;
        }

        // 색종이를 붙일 수 있는 공간일 경우
        if(board[x][y] == 1) {
            for(int i=4; i>=0; i--) {
                if(paper[i] == 0 || !searchSize(x, y, i)) continue;
                attachPaper(x, y, i, 0); // 색종이 붙이기
                paper[i]--;
                searchBoard(x, y+1, total+1);
                attachPaper(x, y, i, 1); // 색종이 떼기
                paper[i]++;
            }
        }

        // 아닐 경우
        else {
            searchBoard(x, y+1, total);
        }
    }

    /* 색종이 사이즈 찾기 */
    static boolean searchSize(int x, int y, int size) {
        for(int i=x; i<=x+size; i++) {
            for(int j=y; j<=y+size; j++) {
                if(i > 9 || j > 9) return false;
                if(board[i][j] == 0) return false;
            }
        }
        return true;
    }

    /* 색종이 붙이기 / 뗴기 */
    static void attachPaper(int x, int y, int size, int isAttach) {
        for(int i=x; i<=x+size; i++) {
            for(int j=y; j<=y+size; j++) {
                board[i][j] = isAttach; // 0은 붙이기, 1은 떼기
            }
        }
    }
}
