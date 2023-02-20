# [16472] 고냥이 - Java

## :pushpin: **Algorithm**

투 포인터

## :round_pushpin: **Logic**

```java
while (left <= right && right < input.length()) {
    char current = input.charAt(right);
    
    if (!removeLeft) {
        // 이미 후보 문자열에 해당 알파벳이 있는 경우
        if (alphabets.containsKey(current)) {
            alphabets.put(current, alphabets.get(current) + 1);
            right++;
            continue;
        }
        
        // 후보 문자열에 알파벳이 없고, 아직 후보 문자열의 알파벳 종류 추가가 가능한 경우
        if (alphabets.size() < N) {
            alphabets.put(current, 1);
            right++;
            continue;
        }
    }
    
    // 후보 문자열에 알파벳을 추가할 수 없고, 왼쪽부터 문자열을 지워내야 하는 경우
    removeLeft = true;
    max = Math.max(max, right - left);
    
    char first = input.charAt(left);
    int count = alphabets.get(first) - 1;
    if (count == 0) {
        alphabets.remove(first);
        removeLeft = false;
    } else {
        alphabets.put(first, count);
    }
    left++;
}
```

- Map을 이용해서 번역하려는 문자열에 대한 정보를 저장한다.
- `removeLeft` 변수를 통해 현재 번역하려는 문자열의 왼쪽부터 제거하는 연산을 동작한다.

## :black_nib: **Review**
- 처음에는 `Queue`와 `Set`을 이용해서 번역하려는 문자열에 대한 정보를 저장 및 사용하려했는데, `caccb` 와 같은 경우에서 `left`를 옮겨주는 방법을 찾을 수 없었다.
- `Map`을 이용해서, 문자열을 빈도 정보를 추가함으로써, `count`가 0이 되는 문자를 지워냄으로써 해결할 수 있었다.
