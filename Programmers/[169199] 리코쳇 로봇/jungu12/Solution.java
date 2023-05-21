import java.util.*;
class Solution {
    static String[] board;
    static int startX, startY;
    static int[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    public int solution(String[] board) {
        int answer = 0;
        this.board = board;
        visited = new int[board.length][board[0].length()];
        
        findRobot();   

        return countMoveToGoal();
    }
    
    void findRobot() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if(board[i].charAt(j) == 'R') {
                    startX = i;
                    startY = j;
                    return;
                }
            }
        }
    }
    
    int countMoveToGoal() {
        Queue<int[]> queue = new LinkedList<>();
        
        //시작 위치를 큐에 삽입
        queue.add(new int[] { startX, startY });
        
        //visited배열에 현재 라운드를 저장함
        visited[startX][startY] = 1;
        
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            //현재 라운드
            int curTurn = visited[curPos[0]][curPos[1]];
            
            //목표 위치에 도달한 경우
            if (board[curPos[0]].charAt(curPos[1]) == 'G') {
                return curTurn - 1;
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int curX = curPos[0];
                int curY = curPos[1];
                while(true) {
                    int nextX = curX + dx[dir];
                    int nextY = curY + dy[dir];
                    
                    //더 이상 미끄러질수 없다면
                    if (!isIn(nextX, nextY) || board[nextX].charAt(nextY) == 'D') {
                        //이미 방문한 곳이면 패스
                        if (visited[curX][curY] != 0) {
                            break;
                        }
                        
                        queue.add(new int[] { curX, curY });
                        visited[curX][curY] = curTurn + 1;
                        break;
                    }
                    
                    //로봇 이동 처리
                    curX = nextX;
                    curY = nextY;
                }
            }
        }
        return -1;
    }
    
    boolean isIn(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length();
    }
}
                                 

