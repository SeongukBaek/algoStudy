# [17683] 방금그곡

## :pushpin: **Algorithm**

문자열, Map

## :round_pushpin: **Logic**

```java
private static String convertSharp(String melody) {
    StringBuilder result = new StringBuilder();
    for (int index = 0; index < melody.length(); index++) {
        char current = melody.charAt(index);
        if (index + 1 < melody.length() && melody.charAt(index + 1) == '#') {
            current = Character.toLowerCase(current);
            index++;
        }
        result.append(current);
    }
    return result.toString();
}
```
- `#` 이 들어가는 경우 그 앞의 대문자를 소문자로 변경하도록 치환한다.

```java
for (Map.Entry<String, String> music : musicInfos.entrySet()) {
    String song = music.getKey();
    String melody = music.getValue();

    // 기억하는 멜로디를 포함하지 않으면 패스
    if (!melody.contains(m)) {
        continue;
    }

    if (maxTime < melody.length()) {
        thatSong = song;
        maxTime = melody.length();
    }
}
```
- 순서를 저장하는 `LinkedHashMap` 에 담긴 곡 정보를 하나씩 확인하면서 기억하는 멜로디를 포함하고 있는지 확인한다.

## :black_nib: **Review**

- 주어진 문제의 조건 중, 먼저 입력된 노래순이라는 조건이 있어서, 순서를 유지할 수 있는 자료구조가 필요했다.
- 또한 #이 붙은 경우 처리가 매우 까다로워서, 이를 치환하는 방법이 주요했다.
