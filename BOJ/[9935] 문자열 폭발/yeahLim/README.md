# [9935] 문자열 폭발

## :pushpin: **Algorithm**

문자열, 구현

## :round_pushpin: **Logic**

```java
for (int i = 0; i < str.length(); i++) {
  sb.append(str.charAt(i));
  if (sb.length() >= burst.length()) {
    boolean isTarget = true;
    for (int j = 0; j < burst.length(); j++) {
      char c1 = burst.charAt(j);
      char c2 = sb.charAt(sb.length() - burst.length() + j);
      if(c1 != c2) {
        isTarget = false;
        break;
      }
    }
    if(isTarget) {
      sb.delete(sb.length()-burst.length(), sb.length());
    }
  }

}
```

- 문자열의 문자를 하나하나 StringBuilder에 넣어준다. 
- StringBuilder의 길이가 폭발문자열의 길이 이상이면, 각각의 문자를 c1, c2에 담아서 비교한다.
- 같으면, StringBuilder에서 삭제한다.
- 다르면, break하고 다음 문자를 추가해준다. 그리고 그 문자부터 폭발문자열의 길이 만큼 비교한다. 
- 이상 생략 (반복)

## :black_nib: **Review**
- 처음에 문자열의 기본 메소드를 통해 간단하게 풀었다가 시간 초과가 났다.
- String은 immutable하기때문에, 내용물이 변하면, 비효율적이다.
- StringBuilder는 가변적인 char[]를 멤버변수로 갖는 클래스이다. 
