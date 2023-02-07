# [1225] 암호 생성기

## :pushpin: **Algorithm**



## :round_pushpin: **Logic**

```java
    static void pro() {
        int now;
        for(int i = 0 ; ; i++) {
            if(pw[(i+7)%8] == 0) {
                now = i;
                break;
            }
            pw[i % 8] -= (i%5+1);
            if(pw[i % 8] < 0)
                pw[i % 8] = 0;
        }
        System.out.print("#" + (T++)+ " ");
        for(int i = 0 ; i < 8 ; i++) {
            System.out.print(pw[(i+now)%8] + " ");
        }System.out.println();
    }
  ```
   - 배열에 저장하고 현재 index를 지칭하는 now 변수로 현재 위치에 접근했다.
  
  
## :black_nib: **Review**
 - 보다 더 적절한 자료구조를 활용하지 않고 그냥 배열로 해결한 것 같다.

  
  	

  
