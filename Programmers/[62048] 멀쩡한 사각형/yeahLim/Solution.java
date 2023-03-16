class Solution {

    public long solution(int w, int h) {
        
		int gcd = gcd(w, h);
		long diagonal = w + h - gcd; // 대각선에 걸쳐진 사각형의 개수
        return (long)w * h - diagonal;
    }
	
	/* 최대 공약수 구하기  */
	static int gcd(int w, int h) {
		
		int smallNum = (w < h) ? w : h; 
		
		for(int i=smallNum; i>0; i--) {
			if(w % i == 0 && h % i == 0) {
				return i;
			}
		}
		
		return 1;
	}
}