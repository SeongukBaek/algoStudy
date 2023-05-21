import java.util.*;
class Solution {
    static int[] dx = { 1, 0, 0, -1 };
    static int[] dy = { 0, 1, -1, 0 };
    static int[][] board;
    static int[][][] fee;
    public int solution(int[][] board) {
        int answer = 0;
        this.board = board;
        fee = new int[board.length][board[0].length][4];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Arrays.fill(fee[i][j], Integer.MAX_VALUE);
            }
        }
        
        makeRoad();
        
        return Arrays.stream(fee[board.length - 1][board[0].length - 1]).min().getAsInt();
    }
    
    void makeRoad() {
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[] { 0, 0, 0 });
        queue.add(new int[] { 0, 0, 1 });
        queue.add(new int[] { 0, 0, 2 });
        queue.add(new int[] { 0, 0, 3 });
        Arrays.fill(fee[0][0], 0);
        
        while(!queue.isEmpty()) {
            int[] curPos = queue.poll(); 
            
            for (int i = 0; i < 4; i++) {
                int dir = (curPos[2] + i) % 4;
                int additionalFee = curPos[2] == dir ? 100 : 600;
                
                int nextX = curPos[0] + dx[dir];
                int nextY = curPos[1] + dy[dir];
                
                //맵 밖인 경우 패스, 벽을 만난 경우 패스
                if(!isIn(nextX, nextY) || board[nextX][nextY] == 1) {
                    continue;
                }
                
                //더 가격이 낮은 경로가 이미 있는 경우 패스
                if(fee[nextX][nextY][dir] < fee[curPos[0]][curPos[1]][curPos[2]] + additionalFee) {
                    continue;
                }
                
                fee[nextX][nextY][dir] = fee[curPos[0]][curPos[1]][curPos[2]] + additionalFee;
                queue.add(new int[] { nextX, nextY, dir });
                
            }
        }
    }
    
    boolean isIn(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}