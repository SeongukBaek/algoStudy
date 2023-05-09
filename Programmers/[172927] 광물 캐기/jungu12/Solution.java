import java.util.*;
class Solution {
    static int[] picks;
    static String[] minerals;
    static int[][] mineralGroups;
    static int answer;
    static int lackPicks;
    static int picksCnt;
    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
        picksCnt = picks[0] + picks[1] + picks[2];

        if (picksCnt < minerals.length / 5 + 1) {
            lackPicks = 1;
        }

        mineralGroups = new int[minerals.length / 5 + 1 - lackPicks][3];
        
        makeMineralGroup();
        
        //상위 광물이 많은 순으로 정렬
        Arrays.sort(mineralGroups, (o1, o2) -> {
            if(o1[0] != o2[0]) {
                return o2[0] - o1[0];
            }
            
            if(o1[1] != o2[1]) {
                return o2[1] - o1[1];
            }
            
            return o2[2] - o2[2];
        });
        
        digUpMinerals();
        
        return answer;
    }

    /**
     * 광물을 5개씩 나누어 저장하는 작업을 수행하는 method
     * 곡갱이 숫자와 광물 개수를 고려하여 캐어지는 광물만 나눈다.
     */
    void makeMineralGroup() {
        int cnt = 0;
        for(int i = 0 ; i < Math.min(picksCnt, minerals.length / 5 + 1); i++) {
            for (int j = 0; j < 5; j++) {
                if(cnt == minerals.length) {
                    return;
                }
                
                if(minerals[i * 5 + j].equals("diamond")){
                    mineralGroups[i][0]++;
                }
                
                else if(minerals[i * 5 + j].equals("iron")){
                    mineralGroups[i][1]++;
                }
                
                else if(minerals[i * 5 + j].equals("stone")){
                    mineralGroups[i][2]++;
                }
            
                cnt++;
            }
        }
        return;
    }
    
    /**
     * 광물 그룹을 순회하며 피로도를 계산해준다.
     */
    void digUpMinerals() {
        for (int i = 0; i < Math.min(picksCnt, minerals.length / 5 + 1); i++) {
            int pick = choosePick();
            System.out.println(calcTired(pick, i));
            picks[pick]--;
            answer += calcTired(pick, i);
        }
    }
    
    int choosePick() {
        if(picks[0] != 0) {
            return 0;
        }
        if(picks[1] != 0) {
            return 1;
        }
        return 2;
    }
    
    int calcTired(int pick, int idx) {
        if(pick == 0) {
            return mineralGroups[idx][0] + mineralGroups[idx][1] + mineralGroups[idx][2];
        }
        
        if(pick == 1) {
            return mineralGroups[idx][0] * 5 + mineralGroups[idx][1] + mineralGroups[idx][2];
        }
        
        return mineralGroups[idx][0] * 25 + mineralGroups[idx][1] * 5 + mineralGroups[idx][2];
    }
}