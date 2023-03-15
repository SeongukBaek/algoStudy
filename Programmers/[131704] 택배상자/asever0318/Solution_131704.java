import java.util.Stack;

class Solution {
	static Stack<Integer> container;
    
    public int solution(int[] order) {
        int answer = 0;
        container = new Stack<>();

		answer = countBox(order);
        
        return answer;
    }
    
    
    static int countBox(int[] order) {
		int count = 0;
		int box = 1;
		int i = 0;
		
		while(i < order.length) { // 순서 
			
			if(order[i] != box) { // 상자 - 순서 다르면 
				if(!container.isEmpty()) { // 스택이 비어있지 않으면
					if(container.peek() == order[i]) { // top에 있는 것과 같으면 
						container.pop(); // 스택에서 빼줌 
						count++;
						i++;
						continue;
					}
				}
				
				if(box == order.length) {
					break;
				}
				
				for(int b = box; b <= order.length; b++) {
					if(order[i] == b) {
						count++;
						box = b + 1;
						i++;
						break;
					}
					else {
						container.push(b);
						box = b;
					}
				}
			}
			else { // 상자 - 순서 같으면
				count++; // 상자 올리기 
				box++; // 다음 상자 
				i++;
			}
		}
		
		return count;
	}
}