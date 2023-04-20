# [49994] 방문 길이

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
public int solution(String dirs) {
        int answer = 0;
        Position pos = new Position(0, 0);
        HashSet<String> set = new HashSet<>();

        for (char c : dirs.toCharArray()) {
            if(c == 'U' && pos.y < 5) {
                pos.y++;
                String str = pos.x + "," + (pos.y - 0.5);
                set.add(str);
            } else if(c == 'D' && pos.y > -5) {
                pos.y--;
                String str = pos.x + "," + (pos.y + 0.5);
                set.add(str);
            } else if(c == 'R' && pos.x < 5) {
                pos.x++;
                String str = (pos.x - 0.5) + "," + pos.y;
                set.add(str);
            } else if(c == 'L' && pos.x > -5) {
                pos.x--;
                String str = (pos.x + 0.5) + "," + pos.y;
                set.add(str);
            }
        }
        answer = set.size();
        return answer;
    }
```

- set에 도로에 대한 정보를 String 으로 저장한다.
- 안 겹치는 도로만 set에 추가해준다.

## :black_nib: **Review**

- 간단한 구현 문제였다.
