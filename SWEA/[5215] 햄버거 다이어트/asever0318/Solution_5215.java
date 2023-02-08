package algo;
import java.util.*;
import java.io.*;
   
//[5215] 햄버거 다이어트 
   
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
  
   /* public boolean isVisit() {
        return visit;
    }
  
    public void setVisit(boolean visit) {
        this.visit = visit;
    }*/
}
   
   
public class Solution_5215 {
 
    static int max = 0;
    static int N, L;
    static int[] result;
    //static List<Item> list = new ArrayList<>();
    static Item[] itemlist;
       
    public static int hamburger(int n, int kcalsum, int scoresum) {
  
        //System.out.println(kcalsum+" "+ scoresum);
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
   
   
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        result = new int[T];
          
        for(int i = 0; i<T; i++) {
            // 재료 수, 제한 칼로리  
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]); 
            L = Integer.parseInt(str[1]);
  
            itemlist = new Item[N];
             
            // 재료별 선호도와 칼로리를 Item 객체로 받아서 리스트에 넣기 
            for(int j = 0; j < N; j++) {
                String[] str2 = br.readLine().split(" ");
                itemlist[j] = new Item(Integer.parseInt(str2[0]),Integer.parseInt(str2[1]));
               /* Item item = new Item(Integer.parseInt(str2[0]),Integer.parseInt(str2[1]), false);
                list.add(item);*/
            }
  
            /*for(Item a : list)
                System.out.println(a.getKal()+ " "+a.getScore());*/
              
            max = 0;
            result[i] = hamburger(0, 0, 0);           
        }
          
        for(int a=0; a<T; a++)
            System.out.println("#"+(a+1)+" "+result[a]);
          
        br.close();
    }
   
}