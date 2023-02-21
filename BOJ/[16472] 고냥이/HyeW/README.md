# [16472] 고냥이

## :pushpin: **Algorithm**

투 포인터

## :round_pushpin: **Logic**

```java
int start = 0;
for(int i = 0; i < meow.length(); i++) {
    
    chars.put(meow.charAt(i), chars.getOrDefault(meow.charAt(i), 0)+1);
    
    while(chars.size() > N) {
        char start_char = meow.charAt(start);
        
        chars.put(start_char, chars.get(start_char)-1);
        if(chars.get(start_char) == 0)
            chars.remove(start_char);
        
        start++;
    }
    
    max = Math.max(max, i - start + 1);
}
```
문자와 문자의 개수를 HashMap에 저장한다. 문자의 종류(Map의 크기)가 주어진 N보다 커지면 start인덱스를 사용해 문자의 종류가 줄어들 때까지 끝에서 문자를 하나씩 제거한다.<br/>

## :black_nib: **Review**
- 포인터 두개를 사용해서 문제를 해결한 적이 많은데 이를 투 포인터 알고리즘이라고 부른다는 것을 이 문제를 통해 알게되었다.
- 특정 문자의 개수를 알고 있어야 해서 key-value로 자료를 저장할 수 있는 Map을 사용했다.