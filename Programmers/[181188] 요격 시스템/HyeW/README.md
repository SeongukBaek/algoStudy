# [181188] 요격 시스템

## :pushpin: **Algorithm**

우선순위 큐, 스택

## :round_pushpin: **Logic**

```java
// 미사일을 시작 좌표 기준으로 정렬한다.
for(int[] target : targets){
    missiles.add(new Missile(target[0], target[1]));
}

shoots.push(missiles.poll().e);
while(!missiles.isEmpty()){
    Missile cur = missiles.poll();
    // 이전 미사일의 끝나는 좌표보다 현재 시작 좌표가 더 크다면 새로운 요격 미사일을 사용한다.
    if(cur.s >= shoots.peek()){
        shoots.push(cur.e);
        continue;
    }
    // 기존의 요격 미사일을 사용할 수 있다면 끝 좌표를 더 적은 좌표로 갱신한다.
    int preEnd = shoots.pop();
    preEnd = Math.min(preEnd, cur.e);
    shoots.push(preEnd);
}
```

- 미사일을 시작 좌표 기준으로 정렬한다. 
  - 다음에 확인하는 미사일의 시작 좌표가 무조건 크거나 같기 때문에 요격 미사일 정보에는 끝 좌표만 저장하면 된다.
- 요격 미사일에 저장된 끝 점보다 미사일의 시작점이 크다면 새로운 요격미사일이 필요하다.
- 만약 미사일의 시작 점이 더 작다면 해당 미사일의 끝점과 요격 미사일의 끝점을 비교해 더 작은 값으로 갱신한다.

## :black_nib: **Review**

- 처음에 정렬하지 않고 미사일을 확인했다. 미사일이 만들어 둔 요격 미사일 구간에 포함되는지 하나하나 확인해서 시간초과가 났었다.
  - 시작 좌표를 기준으로 잡고 정렬하니 정렬된 부분에 대해선 보지 않아도 되어 시간초과를 해결했다.
- 예전에 풀었던 강의실 배정과 매우 유사한 문제였는데 내 힘으로 해결하지 못했다..