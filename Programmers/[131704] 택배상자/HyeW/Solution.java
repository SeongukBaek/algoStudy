import java.util.*;

class Solution {
	
	public int solution(int[] order) {
        int truckParcel = 0;
        //스택사용
        Deque<Integer> assistantBelt = new ArrayDeque<>();
        int curParcel = order[truckParcel];
        
        //기존 컨테이너 벨트 확인
        for(int main = 1; main <= order.length; main++){

            if(curParcel == main){
                curParcel = order[++truckParcel];
                continue;
            }
            
            //보조 컨테이너 벨트 확인
            //보조 컨테이너에 상자가 없을 경우
            if(assistantBelt.isEmpty()){
            	assistantBelt.offer(main);
            	continue; 
            }
            
            //보조 컨테이너에 현재 순서의 상자가 있는 경우
            if(assistantBelt.peekLast() == curParcel){
                main--;
                curParcel = order[++truckParcel];
                assistantBelt.pollLast();
                continue;
            }
            
            assistantBelt.offer(main);
        }
        
        //보조 컨테이너 벨트에 남아있는 상자 확인
        while(!assistantBelt.isEmpty()){
        	curParcel = order[truckParcel];
        	
            if(assistantBelt.peekLast() != curParcel){
                break;
            }
            
            assistantBelt.pollLast();
            truckParcel++;
        }
        
        return truckParcel;
    }
   
}