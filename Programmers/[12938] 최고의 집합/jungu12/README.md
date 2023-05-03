# [12938] 최고의 집합

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
public int[] solution(int n, int s) {
    int[] answer = {-1};

    //집합이 생길 수 없는 경우 return
    if(s < n) {
        return answer;
    }

    answer = new int[n];
    Arrays.fill(answer, s / n);

    //나머지 만큼 순차적으로 배열의 맨 뒤에서부터 1씩 더해줌
    for(int i = 0; i < s % n; i++){
        answer[n - i - 1]++;
    }

    return answer;
}
```

- 집합의 요소들이 차이가 가장 적은 경우가 최고의 집합이라 생각하였다.
- 그래서 모두가 가질 수 있는 값 중 가장 큰 값을 넣고 나머지는 공평하게 배분하였다.

## :black_nib: **Review**

- EASY
