import java.util.Stack;

class Solution {
    Stack<Integer> delivery, pickup;
	
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		
		delivery = new Stack<>();
	    pickup = new Stack<>();
	    
	    // 스택에 배달할 집 번호 넣기 
	    for(int i = 0; i < n; i++) {
	    	int cnt = deliveries[i];
	    	while(cnt != 0) {
	    		delivery.push(i+1);
	    		cnt--;
	    	}
	    }
	    
	    // 스택에 수거할 집 번호 넣기
	    for(int i = 0; i < n; i++) {
	    	int cnt = pickups[i];
	    	while(cnt != 0) {
	    		pickup.push(i+1);
	    		cnt--;
	    	}
	    }
		
	    return getMinDistance(cap);
	}
	
	long getMinDistance(int cap) {
		long distance = 0; // 편도 이동 거리 
		
		while(!delivery.isEmpty() || !pickup.isEmpty()) { // 배달과 수거를 모두 끝낼 때까지 반복 
			int d = 0;
			int p = 0;
			
			// 배달
			if(!delivery.isEmpty()) {
				d = delivery.peek();
				popBox(delivery ,cap);
			}

			// 수거
			if(!pickup.isEmpty()) {
				p = pickup.peek();
				popBox(pickup, cap);
			}
			
			distance += Math.max(d, p) * 2; // 둘 중 더 먼 집을 거리로 선택 
		}
		
		return distance;
	}
	
	void popBox(Stack<Integer> stack, int cap) {
		int cnt = cap;
		
		while(!stack.isEmpty() && cnt > 0) {
			stack.pop();
			cnt--;
		}
	}
}