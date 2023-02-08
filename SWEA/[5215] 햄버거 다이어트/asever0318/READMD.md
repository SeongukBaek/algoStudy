# [5215] 햄버거 다이어트

## **Algorithm**
- 부르트포스 알고리즘 

## **logic**

```java
class Item{
    int score;
    int kal;
   // boolean visit; // 미방문 false 방문 true
       
    public Item(int score, int kal) {
        this.score = score;
        this.kal = kal;
        //this.visit = visit;
    }
   
    public int getScore() {
        return score;
    }
   
    public void setScore(int score) {
        this.score = score;
    }
   
    public int getKal() {
        return kal;
    }
   
    public void setKal(int kal) {
        this.kal = kal;
    }
}
```
- 햄버거 재료를 Item 객체로 만들어 주었다.

```java
spublic static int hamburger(int n, int kcalsum, int scoresum) {
  
        // 칼로리 합이 L보다 크면 종료 
        if(kcalsum > L)
            return max;
          
        // 칼로리 합이 L이하이고 선호도 합이 max보다 크면 max 교체 
        if(kcalsum <= L) {
            if(max < scoresum)
                max = scoresum;
        }
          
        // 모든 재료 다 보면 끝 
        if(n == N)
            return max;
          
        // 해당 재료 선택 
        hamburger(n+1, kcalsum + itemlist[n].getKal(), scoresum + itemlist[n].getScore());
        // 해당 재료 선택 x
        hamburger(n+1, kcalsum, scoresum);
          
        return max;
    }
```
- 문제 조건에서 제한 칼로리를 넘지 않으면서 선호도가 가장 높은 햄버거를 조합해야하는데, 이를 구하기 위해서 모든 재료를 탐색하면서 해당 재료를 넣었을 때와 넣지 않았을 때 모두 탐색하였다.

## **Review**
- 처음에 문제를 접근할 때 햄버거 재료 item 객체를 List로 받았는데, 그렇게 했을 때 다음 testcase가 들어왔을 때 재료의 목록 관리가 어려워서 item배열로 생성해서 고쳐주었다.

