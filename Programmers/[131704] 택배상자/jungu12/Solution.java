import java.util.*;
class Solution {
   public int solution(int[] order) {
		int answer = 0;
		int mainConveyerBox = 1;
		int idx = 0;
		Stack<Integer> assistConveyerBelt = new Stack<>();
		while(answer != order.length) {
            //메인 컨베이어벨트의 박스 번호가 order와 같은 경우
			if (order[idx] == mainConveyerBox) {
				mainConveyerBox++;
				idx++;
				answer++;
				continue;
			}
            //보조 컨베이어벨트의 박스번호가 order와 같은 경우
			if(!assistConveyerBelt.isEmpty() && assistConveyerBelt.peek() == order[idx]) {
				assistConveyerBelt.pop();
				idx++;
				answer++;
				continue;
			}
            //더 이상 진행이 불가능한 경우
			if(mainConveyerBox == order.length) {
				break;
			}
            //진행 가능하다면 보조 컨베이어 벨트로 박스를 옮김
			assistConveyerBelt.push(mainConveyerBox++);
		}
		return answer;
	}
}