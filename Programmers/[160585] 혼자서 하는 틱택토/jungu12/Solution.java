class Solution {
    static String[] board;
    static int oCnt;
    static int xCnt;
    static boolean oWin;
    static boolean xWin;
    public int solution(String[] board) {
        this.board = board;
        
        countOX();
        countWin();
        
        if (oCnt != xCnt && oCnt != xCnt + 1) {
            return 0;
        }
        
        //선공만 이긴 경우 조건에 부합하는지 check
        if (oWin && !xWin && oCnt != xCnt + 1) {
            return 0;
        }
        
        //후공만 이긴 경우 조건에 부합하는지 check
        if (!oWin && xWin && oCnt != xCnt) {
            return 0;
        }

        //둘 다 이기는 경우
        if (oWin && xWin) {
            return 0;
        }
        
        return 1;
    }
    
    void countOX() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') {
                    oCnt++;
                    continue;
                }
                
                if (board[i].charAt(j) == 'X') {
                    xCnt++;
                }
            }
        }
    }
    
    void countWin() {       
        //가로에서 같은 표시가 만들어졌는지 Check
        for (int i = 0; i < 3; i++) {
            char pattern = board[i].charAt(0);

            if (pattern != board[i].charAt(1) || pattern != board[i].charAt(2)) {
                continue;
            }
            
            if (pattern == 'O') {
                oWin = true;
                continue;
            }
            
            if (pattern == 'X') {
                xWin = true;
            }
        }
        
        //세로에서 같은 표시가 만들어졌는지 Check
        for (int i = 0; i < 3; i++) {
            char pattern = board[0].charAt(i);
            
            if (pattern != board[1].charAt(i) || pattern != board[2].charAt(i)) {
                continue;
            }

            if (pattern == 'O') {
                oWin = true;
                continue;
            }
            
            if (pattern == 'X') {
                xWin = true;
            }
        }
        
        //대각선에서 같은 표시가 만들어졌는지 Check
        char pattern = board[0].charAt(0);
        if (pattern == 'O' && board[1].charAt(1) == pattern && board[2].charAt(2) == pattern) {
            oWin = true;
        } 
        if (pattern == 'X' && board[1].charAt(1) == pattern && board[2].charAt(2) == pattern) {
            xWin = true;
        }
        
        pattern = board[0].charAt(2);
        if (pattern == 'O' && board[1].charAt(1) == pattern && board[2].charAt(0) == pattern) {
            oWin = true;
        } 
        if (pattern == 'X' && board[1].charAt(1) == pattern && board[2].charAt(0) == pattern) {
            xWin = true;
        }
    }
}