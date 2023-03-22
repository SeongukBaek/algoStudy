class Solution {
    static int min;
	
	public static int solution(int storey) {
        min = Integer.MAX_VALUE;
        useMagicstone(storey, 0);
        
        return min;
    }
	
	public static void useMagicstone(int storey, int cnt) {
		if(storey == 0) {
			if(min > cnt) { // 최소값 갱신 
				min = cnt;
			}
			return;
		}
		
		int num = storey % 10; // 나머지 
        
		// 결론적으로 나머지가 0이 되도록 만들어 줄거임 
        if(num >= 5) { // 나머지가 5이상이면 더해서 만들기  
        	int plus1 = 10 - num; // +1 할 횟수 
        	int temp = storey + plus1; // 현재 숫자에서 plus1만큼 더해주기 
        	useMagicstone(temp, cnt+plus1); // +1한 횟수만큼 마법의 돌쓴 거니까 cnt+plus1
        }
        
        if(0 < num && num <= 5) { // 나머지가 5이하면 나머지만큼 빼서 만들기
        	int temp = storey - num; // 현재 숫자에서 -1을 나머지만큼 해주기  
        	useMagicstone(temp, cnt+num); // -1한 횟수만큼 마법의 돈 쓴거니까 cnt+num
        }
        
        if(num == 0) { // 나머지가 0이면
        	useMagicstone(storey/10, cnt); // 나머지가 0이면 계산할 필요x, 10으로 나눠서 넘겨주기
        }
	}
}