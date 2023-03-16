# [17683] 방금그곡

## :pushpin: **Algorithm**

문자열

## :round_pushpin: **Logic**

```java
String melodyConversion(String msg) {
    Deque<String> temp = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    String convertedMsg = "";

    for (String m : msg.split("")) {
        String c = m;
        if (c.equals("#")) {
            temp.push(temp.pop().toLowerCase());
            continue;
        }
        temp.push(c);
    }

        while (!temp.isEmpty()) {
            sb.append(temp.pollLast());
        }
        convertedMsg = sb.toString();
        return convertedMsg;
    }
```

- 스텍을 이용해 `#`이 들어간 경우 그 앞문자를 소문자로 치환해준다.

```java
String lyricsConversion(String lyrics, int time) {
    StringBuilder sb = new StringBuilder();
    lyrics = melodyConversion(lyrics);
    int length = lyrics.length();
    int quotient = time / length;
    int reminder = time % length;
    for (int i = 0; i < quotient; i++) {
        sb.append(lyrics);
    }
    for (int i = 0; i < reminder; i++) {
        sb.append(lyrics.charAt(i));
    }
    return sb.toString();
}
```

- 가사를 노래의 플레이타임에 맞춰 길이를 재조정해준다.

## :black_nib: **Review**

- 문자열을 어떻게 하면 편하게 가공할 수 있을지 고민이 많이 되는 문제였다.
- 아직 문자열을 다루는게 익숙하지 않아서 더 많은 문자열 문제를 풀어봐야겠다는 생각이 들었다.
