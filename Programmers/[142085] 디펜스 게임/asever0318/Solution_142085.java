import java.util.*;

class Solution {
    static class Enemy implements Comparable<Enemy>{
        int index, size;
        
        public Enemy(int index, int size){
            this.index = index;
            this.size = size;
        }
        
        // 적의 수가 더 큰 순서 --> 인덱스가 더 빠른 순서
        public int compareTo(Enemy enemy) {
            if(enemy.size == this.size){
                return Integer.compare(enemy.index, this.index); 
            }
            return Integer.compare(enemy.size, this.size);
        }
    }
    
    public int solution(int n, int k, int[] enemy) {
        
        // 무적권 수가 라운드 수보다 크면 무족권만 사용해서 해결 가능
        if(k >= enemy.length){
            return enemy.length;
        }
        
        return playGame(n, k, enemy);
    }
    
    // n이 0보다 작아지는 경우가 생기면 이전 라운드들에서 적의 수가 가장 큰 라운드에 무족권 사용
    static int playGame(int n, int k, int[] enemy){
        PriorityQueue<Enemy> rounds = new PriorityQueue<>(); // 지난 라운드 저장
        
        for(int i = 0; i < enemy.length; i++){
            
            rounds.add(new Enemy(i, enemy[i])); // 
            n -= enemy[i]; // 해당 라운드의 적 수만큼 빼주기
            
            if(n < 0){ // 해당 라운드의 적의 수를 뺸 n이 0보다 작을 때 
                // 1. k를 다 썼으면 라운드 수 반환하고 종료
                if(k == 0){
                    return i;
                }

                // 2. k가 남아있으면 현재 라운드 포함 지난 라운드에서 가장 큰 라운드에 무족권 쓰고 해당 라운드 적 수를 n에 더해주기
                if(k > 0){
                    if(!rounds.isEmpty()){
                        k--; // 무족권 사용 
                        Enemy max = rounds.poll(); // 가장 큰 라운드 뽑기
                        n += max.size; // 해당 라운드 적의 수만큼 n에 다시 더해주기
                    }
                }
            }
        }
        
        return enemy.length;
    }
}