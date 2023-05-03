# [17683] 방금그곡

## :pushpin: **Algorithm**

문자열

## :round_pushpin: **Logic**

```java
//기억하고 있는 멜로디가 악보보다 긴 경우
if (playTime > convertedMelody.length()) {
    StringBuilder newMelody = new StringBuilder();
    for (int i = 0; i < playTime / convertedMelody.length(); i++) {
        newMelody.append(convertedMelody);
    }
    newMelody.append(convertedMelody.substring(0, playTime % convertedMelody.length()));
    convertedMelody = newMelody.toString();
}

//기억하고 있는 멜로디가 악보보다 짧은 경우
if (playTime < convertedMelody.length()) {
    convertedMelody = convertedMelody.substring(0, playTime);
}

//기억하고 있는 멜로디를 포함하면서 재생시간이 가장 긴 음악을 찾음
if (convertedMelody.contains(m) && playTime > maxPlayTime) {
    answer = title;
    maxPlayTime = playTime;
}
```

- 기억하고 있는 멜로디가 악보보다 긴 경우 악보를 멜로디 길이만큼 늘려준다.
- 짧다면 멜로디를 악보만큼만 사용한다.
- 기억하고 있는 멜로디를 포함하면서 재생시간이 가장 긴 음악을 찾는다.

```java
String convertMelody(String melody) {
    melody = melody.replaceAll("A#", "a");
    melody = melody.replaceAll("C#", "c");
    melody = melody.replaceAll("D#", "d");
    melody = melody.replaceAll("F#", "f");
    melody = melody.replaceAll("G#", "g");
    return melody;
}
```

- contains 메소드를 사용하기 위해 #이 붙은 음은 전부 소문자로 치환해주었다.

## :black_nib: **Review**

- 문자열 문제를 풀기위한 메소드에 대한 이해도가 아직 부족한 것 같다..
