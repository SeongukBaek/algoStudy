# [5215] 햄버거 다이어트

## :pushpin: **Algorithm**

브루트포스

## :round_pushpin: **Logic**

```java
static void add(int idx, int taste, int sum) {
        if(sum > calLimit)
            return;
        if(taste > best)
            best = taste;
        if(idx == numLimit)
            return;
        add(idx+1, taste+ingredient[idx][0], sum+ingredient[idx][1]);
        add(idx+1, taste, sum);
    }
  ```
   - 이차원 배열에 재료별 맛과 칼로리를 저장하였다.
   - 배열을 돌면서 하나씩 추가 하거나 추가 하지 않고 다음 재료 선택으로 넘어갔다.
  
## :black_nib: **Review**
 - 큰 어려움 없이 해결하였다.

  
  	

  
