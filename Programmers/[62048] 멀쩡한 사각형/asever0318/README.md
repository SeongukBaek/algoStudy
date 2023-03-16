# [62048] 멀쩡한 사각형

## :pushpin: **Algorithm**

최대공약수

## :round_pushpin: **Logic**

```java
public long solution(long w, long h) {
        long answer = 1;

        answer = (w * h) - (w + h - gdc(w, h));

        return answer;
    }

    // 최대공약수
    static long gdc(long w, long h){
        long gdc = 0;

        for(long i = 1; i <= w && i <= h; i++){
            if(w % i == 0 && h % i == 0){
                gdc = i;
            }
        }
        return gdc;
    }
```

- 겹치는 부분의 개수를 (가로 + 세로 - 최대공약수)로 구할 수 있었다.
- 사용할 수 있는 정사각형의 개수는 전체 - 겹치는 부분의 개수로 구했다.

## :black_nib: **Review**

- 규칙은 찾았는데 이게 최대공약수인 것은 답을 보고 알았다..
