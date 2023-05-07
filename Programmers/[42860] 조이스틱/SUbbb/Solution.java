class Solution {
    private final int A = 65;
    private final int Z = 90;
    
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int move = length - 1; // 좌우 움직임 수를 체크

        for (int index = 0; index < length; index++) {
            answer += convertCost(name.charAt(index)); 

            int next = index + 1;
            // 연속되는 A 갯수 확인
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            // 순서대로 가는 것과, 뒤로 돌아가는 것 중 이동수가 적은 것을 선택
            move = Math.min(move, index * 2 + length - next);
            // BBBBAAAAAAAB 와 같이, 처음부터 뒷부분을 먼저 입력하는 것이 더 빠른 경우까지 고려하려면 아래의 코드가 필요
            move = Math.min(move, (length - next) * 2 + index);
        }
        
        return answer + move;
    }
    
    private int convertCost(char word) {
        return Math.min(Z - word + 1, word - A);
    }
}
