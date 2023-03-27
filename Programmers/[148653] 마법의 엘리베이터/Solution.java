class Solution {
int magicStone = 0;
	int minMagicStone = Integer.MAX_VALUE;

	public int solution(int storey) {
		getMinMagicStone(storey);
		return minMagicStone;
	}

	void getMinMagicStone(int storey) {
		int magicStone = 0;

		while (storey > 0) {
			int curNum = storey%10;
 
			// 10 올라갔다가 내려가기
			if (curNum > 5) {
				storey += 10;
				magicStone += 10-curNum;
				
			}else if (curNum == 5) { 
                // 5일 경우 앞자리 보기 
				int nextNum = (storey/10)%10;
                if(nextNum < 5){
                    magicStone += curNum;
                }else{
                    storey += 10;
                    magicStone += 5;
                }
			}else{
                magicStone += curNum;
            }
			// 다음 자리수 보기
			storey /= 10;
		}
		minMagicStone = magicStone;
	}

}