# [17779] 게리맨더링2 - Java

## :pushpin: **Algorithm**

순열, 구현, 시뮬레이션, 브루트포스 알고리즘

## :round_pushpin: **Logic**

```java
/* 선거구 나누기 */
static void divideDistrict(int depth, int[] num) {
    if(depth == 4) {
        if(checkBoundary(num)) {
            selected = new boolean[n+1][n+1];
            countPopulation();
        };
        return;
    }

    for(int i=1; i<=n; i++) {
        num[depth] = i;
        divideDistrict(depth+1, num);
    }
}
```

- 순열로 x, y, d1, d2값을 구한다
- 구한 값을 checkBoundary()를 통해 제한된 범위 안에 있는지 확인하고, 맞다면 countPopulation()을 실행한다.

```java
static void setBoundary5() {

    // 1번 경계선
    int i = x;
    int j = y;
    while(i<=x+d1 && j>=y-d1) {
        if(!selected[i][j]) {
            selected[i][j] = true;
        }
        i++;
        j--;
    }

    // 2번 경계선
    i = x;
    j = y;
    while(i<=x+d2 && j<=y+d2) {
        if(!selected[i][j]) {
            selected[i][j] = true;
        }
        i++;
        j++;
    }

    // 3번 경계선
    i = x+d1;
    j = y-d1;
    while(i<=x+d1+d2 && j<=y-d1+d2) {
        if(!selected[i][j]) {
            selected[i][j] = true;
        }
        i++;
        j++;
    }

    // 4번 경계선
    i=x+d2;
    j=y+d2;
    while(i<=x+d2+d1 && j>=y+d2-d1) {
        if(!selected[i][j]) {
            selected[i][j] = true;
        }
        i++;
        j--;
    }
}
```

- 경계선을 먼저 그은 후, 경계선에 true값을 부여한다.

```java
/* 1번 선거구의 인구 수 구하기 */
static int countDistrict1() {
    int ppl = 0;
    for(int i=1; i<x+d1; i++) {
        for(int j=1; j<=y; j++) {
            if(selected[i][j]) {
                break;
            }
            ppl += map[i][j];
        }
    }
    return ppl;
}
```

- true값이 나올때까지 각 구역의 인구 수를 더한다.

## :black_nib: **Review**

- 구현하다가 로직을 살짝 바꿨는데 if(!selected){} 코드가 필요없는데, 익숙해서 넣어버렸다. 조심하자!
- 구현하는데는 어렵지않았지만, 좌표나 이상/이하인지 미만/초과인지 이런 사소한 부분에 실수가 있었다.
