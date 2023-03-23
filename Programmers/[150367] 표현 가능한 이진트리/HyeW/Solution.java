import java.util.*;

class Solution {
  public int[] solution(long[] numbers) {
    	 List<Integer> answer = new ArrayList<>();
         
         for(long num : numbers){
             String binary = Long.toBinaryString(num);
             int nodeCnt = binary.length();
             //현재 이진트리 높이
             int height = (int)Math.ceil(Math.log(nodeCnt+1)/Math.log(2));
             //현재 높이일때 포화이진트리의 노드 수
             int minNodeCnt = (int)Math.pow(2, height)-1;
                
             //포화이진 트리가 되도록 더미노드를 앞에 붙여줌
             StringBuilder sb = new StringBuilder();
             while(nodeCnt++ < minNodeCnt ) {
                 sb.append("0");
             }
             binary = sb.toString()+binary;
             
             //만든 이진트리 검사
             if(binaryTreeValidation(binary)) {
            	 answer.add(1);
             }else {
            	 answer.add(0);
             }
         }
         
         return answer.stream().mapToInt(Integer::intValue).toArray();
    }

	private boolean binaryTreeValidation(String binary) {
		boolean valid = true;
		
        //이진트리 루트 찾기
		int mid = (binary.length()-1)/2;
		char root = binary.charAt(mid);
        
        //루트의 왼쪽 자식트리 
		String left = binary.substring(0,mid);
        //루트의 오른쪽 자식트리
		String right = binary.substring(mid+1,binary.length());
		
        //루트가 0인데 자식이 1이라면 이진트리 충족 못함
		if(root == '0' && (left.charAt((left.length()-1)/2)=='1' || right.charAt((right.length()-1)/2)=='1')){
			return false;
		}
		
        //자식트리의 노드개수가 3이상일 때 자식트리 탐방
		if(left.length() >= 3) {
			valid = binaryTreeValidation(left);
			if(valid) {
				valid = binaryTreeValidation
                    (right);
			}
		}
		return valid;
	}
}