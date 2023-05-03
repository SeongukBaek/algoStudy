class Solution {
    static int[] solution(int n, int s) {
		
		if(n > s) {
			return new int[] {-1};
		}
		return getAnswer(n, s);
	}
	
	static int[] getAnswer(int n, int s) {
		int[] ans = new int[n];

		// 1. n/s한 값을 집합의 원소로 나눠갖는다.
		for(int i = 0; i < n; i++) {
			ans[i] = s/n;
		}
		
		// 2. n/s한 나머지를 1씩 나눠갖는다. 
		for(int i = n-1, j = 0; i < 0 || j < s%n; i--, j++) {
			ans[i]++;
		}
		
		return ans;
	}
}