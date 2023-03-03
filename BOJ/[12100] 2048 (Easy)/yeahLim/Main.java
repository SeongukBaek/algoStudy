import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, max;
    static int[][] board;
    public static void main(String[] args) throws IOException {

        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        play2048(0);

        /* 출력 */
        System.out.println(max);
    }

    /* 중복 순열로 게임 5회 돌리기 */
    static void play2048(int count) {

        if(count == 5) {
            setMaxNum();
            return;
        }

        int[][] cpBd = cloneArray(board); // 원래 배열 복사

        for(int i=0; i<4; i++) {

            if(i == 0) moveUp();
            if(i == 1) moveDown();
            if(i == 2) moveLeft();
            if(i == 3) moveRight();

            play2048(count+1);

            board = cloneArray(cpBd); // 원래 배열로 복원
        }

    }

    /* 배열 복사 */
    static int[][] cloneArray(int[][] arr){
        int[][] cpArr = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                cpArr[i][j] = arr[i][j];
            }
        }
        return cpArr;
    }

    /* 위쪽으로 몰기 */
    static void moveUp() {
        for(int j=0; j<n; j++) {
            int idx = 0;
            int num = 0; // 숫자 임시 저장

            for(int i=0; i<n; i++) {

                // 블록이 존재하면
                if(board[i][j] != 0) {
                    // 그 전의 숫자와 같을 경우
                    if(num == board[i][j]) {
                        board[idx-1][j] = num * 2;
                        num = 0;
                        board[i][j] = 0;
                    }
                    // 다를 경우
                    else {
                        num = board[i][j];
                        board[i][j] = 0;
                        board[idx++][j] = num;
                    }
                }
            }
        }
    }

    /* 아래쪽으로 몰기 */
    static void moveDown() {
        for(int j=0; j<n; j++) {
            int idx = n-1;
            int num = 0; // 숫자 임시 저장
            for(int i=n-1; i>=0; i--) {
                if(board[i][j] != 0) {
                    if(num == board[i][j]) {
                        board[idx+1][j] = num * 2;
                        num = 0;
                        board[i][j] = 0;
                    }
                    else {
                        num = board[i][j];
                        board[i][j] = 0;
                        board[idx--][j] = num;
                    }
                }
            }
        }
    }

    /* 왼쪽으로 몰기 */
    static void moveLeft() {
        for(int i=0; i<n; i++) {
            int idx = 0;
            int num = 0; // 숫자 임시 저장
            for(int j=0; j<n; j++) {
                if(board[i][j] != 0) {
                    if(num == board[i][j]) {
                        board[i][idx-1] = num * 2;
                        num = 0;
                        board[i][j] = 0;
                    }
                    else {
                        num = board[i][j];
                        board[i][j] = 0;
                        board[i][idx++] = num;
                    }
                }
            }
        }
    }

    /* 오른쪽으로 몰기 */
    static void moveRight() {
        for(int i=0; i<n; i++) {
            int idx = n-1;
            int num = 0; // 숫자 임시 저장
            for(int j=n-1; j>=0; j--) {
                if(board[i][j] != 0) {
                    if(num == board[i][j]) {
                        board[i][idx+1] = num * 2;
                        num = 0;
                        board[i][j] = 0;
                    }
                    else {
                        num = board[i][j];
                        board[i][j] = 0;
                        board[i][idx--] = num;
                    }
                }
            }
        }
    }

    /* 최대값 찾기 */
    static void setMaxNum() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(max < board[i][j]) {
                    max = board[i][j];
                }
            }
        }
    }
} 
