# [142085] 디펜스 게임

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
tatic int playGame(int n, int k, int[] enemy){
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
```
- n에서 매 라운드마다 적의 수를 빼주면서 n이 0보다 작아지는 경우가 생기면, 이전 라운드들에서 적의 수가 가장 큰 라운드에 무족권을 사용하고 해당 라운드의 적의 수만큼 다시 n에 더해준다.
- 지난 라운드들에서 적의 수가 가장 큰 라운드를 찾기 위해 우선순위 큐를 사용했다.

## :black_nib: **Review**
- 한 라운드마다 무족권을 사용하거나 사용하지 않거나 하는 경우의 수로 접근했었는데, 꼭 매라운드에서 무족권을 사용할지 정해야 하는 게 아니라 지난 라운드에 대해서도 사용이 가능하다는 것을 배울 수 있었다.