# [181188] 요격 시스템

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (o1, o2) -> o1[0] - o2[0]);

        int end = targets[0][1];

        for (int i = 1; i < targets.length; i++) {
            if (end <= targets[i][0]) {
                end = targets[i][1];
                answer++;
                continue;
            }

            if (end > targets[i][1]) {
                end = targets[i][1];
            }
        }

        //마지막 요격 미사일 발사는 위 조건에서 걸리지 않으니 answer + 1 return
        return answer + 1;
    }
}
```

- 1. 미사일의 폭격 시작 구간을 오름차순으로 정렬해준다.
- 2. 새로운 미사일들을 검사하면서 가장 끝 폭격 지점이 작은 것을 저장한다.
- 3. 이전 미사일들의 끝 폭격 지점 중 가장 작은 것보다 더 큰 시작 지점을 가진 미사일이 나온다면, 하나의 요격 미사일로 방어가 불가능하다.
- 4. 위 경우 이전 구간들은 하나의 미사일을 쏴서 방어했다고 치고, 현재 미사일부터 다시 2번으로 돌아가 같은 작업을 반복한다.

## :black_nib: **Review**

- answer를 반환하는 것이 아닌 answer + 1을 반환해야 하는 것을 바로 떠올리지 못하였다..
- 나는 이 문제처럼 로직을 생각하는 것이 어렵지만 구현이 오래걸리지 않는 문제가 좋다 ㅎㅎ
