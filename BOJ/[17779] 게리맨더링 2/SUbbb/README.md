# [17779] 게리맨더링2 - Java

## :pushpin: **Algorithm**

구현, 시뮬레이션, 브루트포스 알고리즘

## :round_pushpin: **Logic**

```java
private static void divideVotingZone(int x, int y, int d1, int d2) {
  List<Integer> populations = new ArrayList<>();
  isFifth = new boolean[N + 1][N + 1];

  populations.add(fifthZone(x, y, d1, d2));
  populations.add(firstZone(x, y, d1));
  populations.add(secondZone(x, y, d2));
  populations.add(thirdZone(x, y, d1, d2));
  populations.add(fourthZone(x, y, d1, d2));
  
  minDifference = Math.min(minDifference, Collections.max(populations) - Collections.min(populations));
}
```

- 다섯번째 선거구의 경계선을 먼저 그은 후, 첫번째부터 네번째 선거구의 인원을 구한다.
- 리스트의 최대, 최소값 함수로 차이를 구한다.

```java
private static int fifthZone(int x, int y, int d1, int d2) {
  int sum = 0;
  int r = 0;
  int c = 0;
  
  // 경계선을 먼저 그어둔다.
  for (r = x, c = y; r <= x + d1 && c >= y - d1; r++, c--) {
    sum += map[r][c];
    isFifth[r][c] = true;
  }
  for (r = x + 1, c = y + 1; r <= x + d2 && c <= y + d2; r++, c++) {
    sum += map[r][c];
    isFifth[r][c] = true;
  }
  for (r = x + d1 + 1, c = y - d1 + 1; r <= x + d1 + d2 && c <= y - d1 + d2; r++, c++) {
    sum += map[r][c];
    isFifth[r][c] = true;
  }
  for (r = x + d2 + 1, c = y + d2 - 1; r < x + d2 + d1 && c >= y + d2 - d1; r++, c--) {
    sum += map[r][c];
    isFifth[r][c] = true;
  }
  
  // 경계선 내부가 다섯번째 선거구임을 저장
  for (r = x + 1; r < x + d1 + d2; r++) {
    boolean isStart = false;
    for (c = 1; c <= N; c++) {
      if (isStart && !isFifth[r][c]) {
        sum += map[r][c];
        isFifth[r][c] = true;
        continue;
      }
      
      if (isStart && isFifth[r][c]) {
        break;
      }
      
      if (isFifth[r][c]) {
        isStart = true;
      }
    }
  }
  
  return sum;
}
```

- 경계선을 먼저 그은 후, 경계선 내부가 다섯번째 선거구임을 boolean 배열로 저장했다.
- 이후 다른 선거구는 해당 배열을 통해 구할 수 있다.

## :black_nib: **Review**
**이전 풀이**

- 처음에는 게리맨더링이랑 비슷한 줄 알았는데 조금은 달라서 생각해보는데 시간이 좀 걸렸다. 
- 선거구를 나누는 로직을 구현하는데 시간이 걸렸고, 게시판을 참고해서 나름대로 풀어봤는데, 최종적으로 인구수를 합산해 차이의 최소를 구하는 로직이 자꾸 틀렸다.
  - 어차피 나눠진 선거구의 인구 수를 나눌 때마다 합산하고 선거구를 다 나눈 뒤 (즉, 인구 수 합산까지 완료된 상태) 차이를 구하고 이를 업데이트하는 방식은 왜 틀리는지 모르겠다.

--- 

**개선 이후**
- 이전 풀이와 비슷하게, 선거구의 경계선을 나누는 로직에서 너무 많은 시간을 허비했다.
- 좀 더 깔끔하게 풀이가 가능하지 않을까 싶다..