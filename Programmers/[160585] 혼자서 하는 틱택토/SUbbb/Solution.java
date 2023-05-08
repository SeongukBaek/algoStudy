class Solution {

    // O와 X 개수로 1번 규칙 확인
    // O와 X의 개수 모두 3개 이상인 경우, 종료되었는지 판단해서 2번 규칙 확인

    private String[] board;

    public int solution(String[] board) {
        this.board = board;

        int[] counts = countMarks();
        int o = counts[0];
        int x = counts[1];

        // 아직 아무것도 안 한 상태
        if (o == 0 && x == 0) {
            return 1;
        }

        // o보다 x개수가 많다면 o 표시를 빼먹고 x를 표시한 것
        // o 개수와 x개수가 1개 차이가 아니라면 x를 표시하지 않은 것
        if (o < x || o - x > 1) {
            return 0;
        }

        // 둘 다 이미 종료되었는데 진행하는 경우 확인
        boolean oEnd = checkEnd('O');
        boolean xEnd = checkEnd('X');

        // 동시에 이길 수 없다.
        if (oEnd && xEnd) {
            return 0;
        }

        // o 개수가 더 많은 경우, x가 이미 끝나있을 수 없다.
        if (o > x && xEnd) {
            return 0;
        }

        // 두 개 개수가 같은 경우, o가 이미 끝나있을 수 없다.
        if (o == x && oEnd) {
            return 0;
        }

        return 1;
    }

    private int[] countMarks() {
        int o = 0;
        int x = 0;

        for (String line : board) {
            for (char mark : line.toCharArray()) {
                if (mark == 'O') {
                    o++;
                }
                if (mark == 'X') {
                    x++;
                }
            }
        }

        return new int[] {o, x};
    }

    // 0번째 행과 0, 2번째 열에서 문자를 발견하면, 가로, 세로, 대각선 모두 확인
    private boolean checkEnd(char word) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x].charAt(y) == word) {
                    if (x == 0 && checkVertical(word, y)) {
                        return true;
                    }
                    if (y == 0 && checkHorizontal(word, x)) {
                        return true;
                    }
                    if ((x == 0 && (y == 0 || y == 2)) && checkDiagonal(word, y)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // x가 0인 경우만 가로 확인
    private boolean checkVertical(char word, int y) {
        for (int index = 1; index < 3; index++) {
            if (board[index].charAt(y) != word) {
                return false;
            }
        }

        return true;
    }

    // y가 0인 경우만 세로 확인
    private boolean checkHorizontal(char word, int x) {
        for (int index = 1; index < 3; index++) {
            if (board[x].charAt(index) != word) {
                return false;
            }
        }

        return true;
    }

    // x가 0이고, y가 0 또는 2인 경우만 세로 확인
    private boolean checkDiagonal(char word, int y) {
        for (int index = 1; index < 3; index++) {
            // 오른쪽 아래 대각선
            if (y == 0) {
                if (board[index].charAt(index) != word) {
                    return false;
                }
            }

            // 왼쪽 아래 대각선
            if (y == 2) {
                if (board[index].charAt(y - index) != word) {
                    return false;
                }
            }
        }

        return true;
    }
}