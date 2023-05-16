import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        boolean canInsentive;
        List<int[]> employee = new ArrayList<>();
        List<int[]> insentiveEmployee = new ArrayList<>();
        
        //scores배열에서 점수의 합이 완호보다 높은 사원만 employee에 추가
        for (int i = 1; i < scores.length; i++) {
            if(scores[0][0] + scores[0][1] < scores[i][0] + scores[i][1]) {
                employee.add(scores[i]);
            }
        }
        
        for (int i = 1; i < scores.length; i++) {
            //완호보다 두 점수가 모두 높은 사원이 있다면 -1 반환
            if(scores[i][0] > scores[0][0] && scores[i][1] > scores[0][1]) {
                return -1;
            }
            
            //완호보다 점수의 합이 높은 사원이라면
            if(scores[i][0] + scores[i][1] > scores[0][0] + scores[0][1]) {
                canInsentive = true;
                
                //완호보다 점수합이 높은 사원들을 순회하며
                for(int j = 1; j < employee.size(); j++) {
                    //현재 사원이 인센티브를 받을 수 있는지 검사
                    if(employee.get(j)[0] > scores[i][0] && employee.get(j)[1] > scores[i][1]) {
                        canInsentive = false;
                        break;
                    }
                }
                
                //인센티브를 받을 수 있는 사원이라면 인센티브 받는 사원을 저장하는 insentiveEmployee에 저장
                if(canInsentive) {
                    insentiveEmployee.add(scores[i]);
                }
            }
        }
        
        return insentiveEmployee.size() + 1;
    }
}