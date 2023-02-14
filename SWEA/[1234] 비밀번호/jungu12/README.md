# [1234] 비밀번호

## :pushpin: **Algorithm**


## :round_pushpin: **Logic**

```java
static void check(int idx) {
  if (idx >= pw.size() - 1) {
      return;
  }
  if (pw.get(idx) == pw.get(idx + 1)) {
      for (int i = 0; i < 2; i++) {
          pw.remove(idx);
      }
      check((idx - 1 < 0) ? 0 : idx-1);
  } else {
      check(idx + 1);
  }
}
  ```
   - pw라는 Arraylist에 input을 입력 받은 후, 처음부터 조건을 만족하는 짝을 찾아 remove 해준다.
   - 조건을 만족하는 짝을 찾는다면 idx - 1 해주고 다시 짝을 찾는다.
  
  
## :black_nib: **Review**
 - 조건을 찾아 idx - 1을 해줄 경우, 0보다 작아 질 수 있다는 것을 뒤늦게 생각하였다.

  
  	

  
