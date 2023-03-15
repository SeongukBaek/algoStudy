# [17683] 방금그곡

## :pushpin: **Algorithm**

문자열, Map

## :round_pushpin: **Logic**

```java
static Map<Character, Integer> sharpKey = new HashMap<Character, Integer>(){
    {
        put('C', 1);
        put('D', 2);
        put('E', 3);
        put('F', 4);
        put('G', 5);
        put('A', 6);
        put('B', 7);
    }
};

/* #키 숫자로 변환 */
static String convertToIntKey(String score) {
    StringBuilder sb = new StringBuilder();
    int last = score.length()-1;
    for(int i=0; i<last; i++) {
        if(score.charAt(i+1) == '#') {
            sb.append(sharpKey.get(score.charAt(i)));
            i++;
        }
        else sb.append(score.charAt(i));
    }
    if(score.charAt(last) != '#') sb.append(score.charAt(last));
    return sb.toString();
}
```

- `#` 이 들어가는 키의 경우 숫자로 변환해준다.
  - c# -> 1, D# -> 2 ... B# -> 7

```java
/* 악보 만들기 */
static String makeMusicScore(int length, String score) {
    int scoreLen = score.length();
    if (scoreLen > length)
        return score.substring(0, length);
    if (scoreLen < length)
        return score.repeat(length / scoreLen) + score.substring(0, length % scoreLen);
    return score;
}
```

- 재생시간만큼의 악보를 만든다.

```java
/* 노래 찾기 */
static boolean searchMusic(String target, String score) {
    return score.contains(target);
}
```

- 악보에 해당되는 노래를 찾는다.

## :black_nib: **Review**

- 적혀있는 그대로 풀었다.
