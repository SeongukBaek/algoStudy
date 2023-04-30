class Solution {
    
    static int[] Ryan;
	static int[] answer;
	static int maxDiff;
    
    public int[] solution(int n, int[] info) {
        Ryan = new int[11];
		answer = new int[11];
		
		RyanTurn(n, 0, info, 0);
		
		boolean check = false;
		for(int i = 0; i < 11; i++) { // 만약 answer의 요소가 다 0이면 이길 수 없는 경
			if(answer[i] != 0) {
				check = true;
			}
		}
		
		if(check == true) {			
			return answer;
		}
		
		return new int[] {-1};
    }
    
    static void RyanTurn(int n, int nCnt, int[] info, int index) {
		
		if(n == nCnt) { // n번 다 쐈으면 점수계산 
			int rScore = 0;
			int aScore = 0;
			
			for(int i = 0; i < 11; i++) {
				// 둘 다 0번 맞췄을 때는 둘 다 점수 없기 때문에 패스 s
				if(Ryan[i] == 0 && info[i] == 0) {
					continue;
				}
				if(Ryan[i] > info[i]) {
					rScore += (10-i);
				}
				else {
					aScore += (10-i);
				}
			}
			
			// 만약 라이언이 이겼으면 
			if(rScore > aScore) {
				int diff = rScore - aScore;
				
				if(maxDiff < diff) {
					copyArr(answer, Ryan); // 정답 갱신 
					maxDiff = diff;
				}
				else if(maxDiff == diff) { // 점수 차가 같으면 가장 낮은 점수를 맞춘 경우가 정답 
					getLowScoreAns();
				}
			}
			return;
		}
		
		for(int i = index; i < 11; i++) {
			// 라이언이 맞춘 화살이 더 많으면 라이언 점수니까 더 쏠 필요x
			if(info[i] < Ryan[i]) {
				continue;
			}
			Ryan[i]++;
			RyanTurn(n, nCnt+1, info, i);
			Ryan[i]--; // 백트레킹 
		}
	}
	
	static void copyArr(int[] a, int[] b) {
		for(int i = 0; i < 11; i++) {
			a[i] = b[i];
		}
	}
	
	// 점수 차이가 같을 때는 낮은 점수를 더 많이 가진 것이 정답 
	static void getLowScoreAns() {
		// 낮은 점수부터 확인하면서 
		for(int i = 10; i >= 0; i--) {
			
			// 더 낮은 점수를 이전 정답이 먼저(많이) 가지고 있으면 갱신할 필요 x 
			if(answer[i] > Ryan[i]) {
				break;
			}
			
			// 더 낮은 점수를 새 정답이 먼저(많이) 가지고 있으면 갱신 
			if(answer[i] < Ryan[i]) { // 가장 낮은 점수를 더 많이 맞춘 경우 
				copyArr(answer, Ryan);
				break;
			}
		}
	}
}