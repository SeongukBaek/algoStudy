import java.util.*;

class Solution {
    static int[] picksList, result; // 0 - 다곡, 1 - 철곡, 2 - 돌곡
    static int min, size;
    
    public int solution(int[] picks, String[] minerals) {
        
        for(int i = 0; i < 3; i++){
            size += picks[i];
        }
       
        result = new int[size];
        
        min = 9999;
        pick(size, 0, picks, minerals);
        
        return min;
    }
    
    static void pick(int size, int cnt, int[] picks, String[] minerals){
        if(size == cnt){ // n개 다 뽑았으면 
            // 다 뽑았으면 피로도 최소값 갱신 
            min = Math.min(min, mining(minerals));
            return;
        }
        
       for(int i = 0; i < picks.length; i++){
            if(picks[i] > 0){
                picks[i]--;
                result[cnt] = i;
                pick(size, cnt+1, picks, minerals);
                picks[i]++;
            }
        }
    }
    
    // 광물 캐기 --> 피로도 구하기 
    static int mining(String[] minerals){
        int cost = 0;
        int index = 0;
        
        for(int i = 0; i < size; i++){ // 곡괭이 순서
            for(int j = 0; j < 5; j++){ // 광물은 최대 5개까지 캘 수 있음
                if(index == minerals.length){ // 광물 모두 캐면 멈춤
                    return cost;
                }
                cost += getCost(minerals[index], result[i]);
                index++;
            }
        }
        return cost;
    }
    
    static int getCost(String mineral, int pickaxe){
        
        if(pickaxe == 0){ // 다곡이면 1반환 
            return 1;
        }
        else if(pickaxe == 1){ // 철곡이면 광물이 다이아일 경우만 5, 나머지 1반환
            if(mineral.equals("diamond")){
                return 5;
            }else{
                return 1;
            }
        }else{ // 돌곡이면 다이아 25, 철 5, 돌 1반환
            if(mineral.equals("diamond")){
                return 25;
            }
            else if(mineral.equals("iron")){
                return 5;
            }
            else{
                return 1;
            }
        }
    }
}