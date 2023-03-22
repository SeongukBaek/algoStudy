# [14891] 톱니바퀴

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
gears = new int[4][8];
		start = new int[4]; // 초기 방향 지정
```

- 톱니바퀴의 모양을 각 배열에 담아두고 각각의 시작 인덱스를 저장하는 배열을 선언해두었다.

```java
// 왼쪽확인
for (int i = idx; i > 0; i--) {
  // 같은 극이면
  if (gears[i][leftDirection(start[i])] == gears[i - 1][rightDirection(start[i - 1])]) {
    break;
  }
  // 다른 극이면
  left++;
}

// 오른쪽 확인
for (int i = idx; i < 3; i++) {
  // 같은 극이면
  if (gears[i][rightDirection(start[i])] == gears[i + 1][leftDirection(start[i + 1])]) {
    break;
  }
  // 다른 극이면
  right++;
}
```

- 돌려야하는 톱니바퀴의 앞, 뒤를 확인해 돌아가는 갯수 만큼의 수를 각각 저장해둔다.

```java
int temp = type;
rotate(idx, type);
// 왼쪽
for (int i = 1; i <= left; i++) {
  temp = typeChange(temp);
  rotate(idx - i, temp);
}

temp = type;
// 오른쪽
for (int i = 1; i <= right; i++) {
  temp = typeChange(temp);
  rotate(idx + i, temp);
}
```

- 그 수만큼 톱니바퀴들을 돌려준다.

## :black_nib: **Review**

- 문제를 제대로 읽지 않아서 시간이 2배로 걸렸다...
- 문제를 확실히 이해하기 전까지는 다시는 타이핑을 시작하지 않겠다 :fire
