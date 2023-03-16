class Solution {
    public int solution(int[] order) {
    	// 이미 택배기사에게 적재되었는지 저장
    	boolean[] hasLoaded = new boolean[order.length];
        int answer = 0;
        
        int assistantVeltTop = 0;
        for (int index = 0; index < order.length;) {
        	int box = order[index] - 1;

        	// 만약 현재 실으려는 박스의 번호보다 보조 벨트에 있는 박스의 번호가 더 크다면 더 이상 싣지 못함
            if (assistantVeltTop > box) {
            	break;
            }
            
            // 실으려는 박스와 보조 벨트에 있는 박스의 번호가 같다면 싣기
            if (assistantVeltTop == box) {
            	hasLoaded[assistantVeltTop] = true;
                answer++;
                
                // 아직 싣지 않은 박스의 번호로 assistantVeltTop 수정
                while (assistantVeltTop > 0 && hasLoaded[assistantVeltTop]) {
                	assistantVeltTop--;
                }
                
                index++;
                continue;
            }
            
            // 해당 박스 이전까지의 박스를 보조 벨트에 싣기
            while (assistantVeltTop < box) {
            	assistantVeltTop++;
            }
        }
        
        return answer;
    }
}