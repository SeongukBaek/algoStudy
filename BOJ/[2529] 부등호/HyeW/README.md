# [2529] 부등호

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

모든 숫자의 조합을 확인해야 하는 문제이기에 **순열**을 사용했다.
<br/>
```java
public static void perm(int d, int r, int[] output, boolean[] visited) {

        if (d == r) {
            double temp = changeDo(output);
            max = Math.max(max, temp);
            min = Math.min(min, temp);
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            output[d] = num[i];
    
            //부등호 불일치일 경우 가지치기
            if (d > 0)
                if (!validate(output[d - 1], output[d], inequal[d - 1])) {
                    visited[i] = false;
                    continue;
                }
    
            perm(d + 1, r, output, visited);
            visited[i] = false;
        }
}
```
재귀를 돌면서 0부터 9까지 선택안된 숫자를 선택하며 원하는 길이의 int배열이 나오면
`changeDo(int[])`함수로 숫자로 변환한 후 max와 min을 찾았다.
<br/>
또한 숫자가 하나씩 추가될 때마다 부등호를 만족하는지 `validate(int, int, char)`함수를 통해 확인하여 
실행시간을 줄였다.
## :black_nib: **Review**
- max와 min을 찾는 것으로 비교를 해야 한다는 생각에 숫자로 타입을 진행했다.
    - 하지만 출력형식이 012를 뽑았다면 12가 아닌 012로 나타내야 해서 0을 붙이는 코드가 추가되었다.
    - 스터디를 하고 나서, 숫자 비교가 필요한 것이 아니라 처음 만들어진 수가 min이 되며 가장 나중에 만들어진 수가 max가 되기 때문에 String으로 처리하는 것이 더욱 깔끔한 코드가 나온다는 것을 알게 되었다.
- `> < < < > > > < <`와 같이 공백이 있는 문자열에서 필요한 문자를 쓰기 위해서 StringTokenizer를 사용해 char배열로 받았다.
  ```java
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
        inequal[i] = st.nextToken().charAt(0);
    }
  ```
  - 스터디를 하고 나서, 받은 문자열의 공백을 `replace()`로 없애고 `String`으로 받는 방법이 있다는 것을 알게 되었다. 이 방법으로 3줄이었던 코드를 1줄로 깔끔하게 정리할 수 있다.