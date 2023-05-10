import java.util.*;

class Solution {
    static char[][] map;
    static int o, x;
    
    public int solution(String[] board) {
        map = new char[3][3];
        
        int cnt = 0;
        for(int i = 0; i < 3; i++){
            map[i] = board[i].toCharArray();
        }

        countChar(); // 문자 카운트 
        
        if(x > o || (o - x) > 1){ // x의 개수가 더 많거나 둘의 개수가 2개 이상 차이나는 경우는 일어날 수 없음 
            return 0;
        }
        
        // 둘 다 빙고인 경우는 일어날 수 없음 
        if(checkBingo('O') && checkBingo('X')){
            return 0;
        }
        
        // ----- 둘 중 한 명이 이기는 상황 -----
        // 1. O가 이겼으면 O가 한 개 많아야 함 
        if(checkBingo('O')){
            if(o != (x+1)){
                return 0;
            }
        }
        
        // 2. X가 이겼으면 수가 같아야 함
        if(checkBingo('X')){
            if(o != x){
                return 0;
            }
        }
        
        return 1;
    }
    
    static boolean checkBingo(char c){
        boolean check = false;
        
        // 가로
        for(int i = 0; i < 3; i++){
            if(c == map[i][0] && map[i][0] == map[i][1] && map[i][1] == map[i][2]){
                check = true;
            }
        }
        
        // 세로
        for(int i = 0; i < 3; i++){
            if(c == map[0][i] && map[0][i] == map[1][i] && map[1][i] == map[2][i]){
                check = true;
            }
        }
        
        // 대각선(2가지 경우)
        if(c == map[0][0] && map[0][0] == map[1][1] && map[1][1] == map[2][2]){
            check = true;
        }
        
        if(c == map[0][2] && map[0][2] == map[1][1] && map[1][1] == map[2][0]){
            check = true;
        }
    
        return check;
    }
    
    static void countChar(){
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(map[i][j] == 'O'){
                    o++;
                }
                
                if(map[i][j] == 'X'){
                    x++;
                }
            }
        }
    }
}