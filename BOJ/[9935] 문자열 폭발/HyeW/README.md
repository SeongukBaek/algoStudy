# [9935] 문자열 폭발

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
for (int i = 0; i < origin.length(); i++) {
    if (origin.charAt(i) != last_char) {
        chars.push(origin.charAt(i));
        continue;
    }
    if (checkBomb()) {
        removeString();
    } else {
        chars.push(origin.charAt(i));
    }
}
```
주어진 문자열을 스택에 넣을 때 폭발 문자열의 마지막 문자와 같다면 스택에 들어있는 이전 문자열과 비교하여 폭발 문자열과 같은지 알아낸다.<br/>
폭발 문자열과 같다면 그 문자열을 스택에서 pop()하여 없애준다.

## :black_nib: **Review**
- 문자열 문제도 많이 풀어보지 못했고 스택 알고리즘도 처음 사용해봐서 많이 헤맨 문제였다.
    - 앞으로 문자열을 비교하고 삭제하는 문제는 스택을 사용하면 될 것같다. 
- 시간초과가 났었는데 출력에서 sb.insert()를 사용해서 난 문제였다. 
    - `insert()` 는 내부에서 `System.arraycopy()`가 두 번 사용된다. (offset이후 부터 배열을 뒤로 옮겨야 하기 때문에) 하지만 `append()`는 한 번 사용되기에 `append()`가 실행시간이 더 짧다.
    - 당연한 이야기지만 문제를 풀 땐 생각하지 못한 부분이었다. 10000단위가 넘어가면 무조건 StringBuilder.append()를 쓰는게 좋을 것 같다.