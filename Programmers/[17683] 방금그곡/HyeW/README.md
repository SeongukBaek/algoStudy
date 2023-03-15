# [17683] 방금그곡

## :pushpin: **Algorithm**

문자열, 투포인터

## :round_pushpin: **Logic**

```java
/* #붙은 음을 소문자로 바꿔준다. */
String changeMelody(String origin)
```

`C#`을 `c`로 바꾼 것과 같이 #이 붙은 음을 소문자로 변환해 준다.

```java
while(music.playTime > time){
  // 현재 시간의 음 뽑기
  char curTone = music.sheet.charAt(sheetIndex);

  // 같다면
  if(m.charAt(mIndex) == curTone) {
    if(!isSame) {
      preIndex = sheetIndex;
      isSame = true;
    }
    mIndex++;
    sheetIndex = (sheetIndex+1)%sheetLen;
  }else { // 다르면
    if(isSame) {
      sheetIndex = (preIndex+1)%sheetLen;
      time = time - mIndex;
      isSame = false;
    }else {
      sheetIndex = (sheetIndex+1)%sheetLen;
    }
    mIndex = 0;
  }

  //주어진 멜로디와 일치하면
  if(mIndex == m.length()) {
    return true;
  }

  time++;
}
```

문자를 하나씩 비교하며 맞는지 판단한다.<br/>
문자를 비교하다가 다르다면 비교를 시작했던 첫인덱스의 다음 인덱스로 이동한다.<br/>

## :black_nib: **Review**

- 문자열을 너무 안풀어봐서 어떤 함수를 써야할지 막막했다. 결국 문자열에 있는 함수를 구현해서 풀었다. 다 풀고나니 replace와 contains함수가 있다는 것을 알았다. 다음엔 함수를 잘 활용해야겠다.
