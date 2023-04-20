import java.util.*;

class Solution {
    class Position {
        double x;
        double y;

        public Position (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(String dirs) {
        int answer = 0;
        Position pos = new Position(0, 0); 
        HashSet<String> set = new HashSet<>(); 

        for (char c : dirs.toCharArray()) {
            if(c == 'U' && pos.y < 5) {
                pos.y++;
                String str = pos.x + "," + (pos.y - 0.5); 
                set.add(str);
            } else if(c == 'D' && pos.y > -5) {
                pos.y--;
                String str = pos.x + "," + (pos.y + 0.5);
                set.add(str);
            } else if(c == 'R' && pos.x < 5) {
                pos.x++;
                String str = (pos.x - 0.5) + "," + pos.y;
                set.add(str);
            } else if(c == 'L' && pos.x > -5) {
                pos.x--;
                String str = (pos.x + 0.5) + "," + pos.y;
                set.add(str);
            }
        }
        answer = set.size();
        return answer;
    }
    
}