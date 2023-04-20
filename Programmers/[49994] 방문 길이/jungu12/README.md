# [2263] 트리의 순회

## :pushpin: **Algorithm**

Divide and Conquer

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

- 음수 사이클이 존재한다면 문제에서 찾고자 하는 조건을 만족하는 경우이다.
- 음수 사이클이 생기는지 확인하여 생긴다면 true를 반환한다.

## :black_nib: **Review**

- 벨만 포드 알고리즘에 대한 이해도가 부족하다..
