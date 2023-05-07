class Solution {
    static int solution(String name) {
		char[] str = name.toCharArray();
		int sum = 0;
		
		// 1. 각 문자의 최소 조작 횟수 구하기 
		for(int i = 0; i < str.length; i++) {
			if(str[i] == 'A') {
				continue;
			}
			sum += Math.min(Math.abs(str[i]-'A'), Math.abs('Z'-str[i]+1));
		}
		
		// 2. 각 문자로 이동하는 최소 횟수 
		int move = str.length-1; // 최대 이동 횟수는 str-1번 
		
		for(int i = 0; i < str.length-1; i++) {
			// 만약 다음 문자가 'A'이면 연속된 A길이 구하기 
			if(str[i+1] == 'A') {
				int j = i+1;
				while(j < str.length && str[j] == 'A') {
					j++;
				}
				
				move = Math.min(move, (i*2+(str.length-j)));
				move = Math.min(move, (str.length-j)*2+i);
			}
		}
		
		return sum + move;
	}
}