# [42860] 조이스틱

## :pushpin: **Algorithm**

브루트포스

## :round_pushpin: **Logic**

```java
/* 알파벳 변환 */
public int changeAlphabets(String name) {
    int count = 0;
    for (char na : name.toCharArray()) {
        count += Math.min(na - 'A', 'Z' - na + 1);
    }
    return count;
}
```

- 각 글자를 알파벳으로 변환시킨다.
- A~M까지는 'A'를 빼주는 방법이 유리하고, O~Z까지는 'Z'에서 빼주는 방법이 유리하다.

```java
/* Backtracking : A가 아닌 모든 알파벳 방문 횟수 구하기 */
public void move(int current, int count, int[] visited) {

    // 최대로 방문할 수 있는 길이보다 클 경우
    if (moveCnt <= count) {
        return;
    }

    // 종료 조건
    if (checkAllVisited(visited)) {
        moveCnt = Math.min(moveCnt, count);
        return;
    }

    // 왼쪽으로 이동
    int left = current - 1 >=0 ? current - 1 : n-1;
    visited[left] += 1;
    move(left, count + 1, visited);
    visited[left] -= 1;


    // 오른쪽으로 이동
    int right = current + 1 < n ? current + 1 : 0;
    visited[right] += 1;
    move(right, count + 1, visited);
    visited[right] -= 1;

}
```

- 왼쪽/오른쪽으로 끊임없이 방문하다가 모든 곳에서 방문했을 때 리턴한다.
- 최대로 방문할 수 있는 길이보다 클 경우에도, 더 이상 방문할 필요가 없기 때문에 리턴한다.
- visited는 중복 방문할 수 있기 때문에 int[]로 선언했다.

## :black_nib: **Review**

- 그리디로 푸는 방법도 배웠다.
- 생각치 못한 곳에서 계속 오류가 났는데, 로직을 변경할때는 그전 로직이 남아있는지 언제나 주의해야겠다.
