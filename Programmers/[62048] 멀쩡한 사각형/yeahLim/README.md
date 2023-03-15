# [62048] 멀쩡한 사각형

## :pushpin: **Algorithm**

최대공약수

## :round_pushpin: **Logic**

```java
static int gcd(int w, int h) {

		int smallNum = (w < h) ? w : h;

		for(int i=smallNum; i>0; i--) {
			if(w % i == 0 && h % i == 0) {
				return i;
			}
		}

		return 1;
	}
```

- 최대공약수를 구한다.

```java
int gcd = gcd(w, h);
long diagonal = w + h - gcd;
return (long)w * h - diagonal;
```

- 전체 넓이 - (대각선에 걸쳐진 사각형의 개수) 가 정답이다
- 대각선에 걸쳐진 사각형의 개수 =  (w/gcd + h/gcd -1)*gcd = w + h - gcd

## :black_nib: **Review**

- 수학적 개념이 들어가서 규칙을 찾기가 매우 어려웠다... 
- 수학적 규칙을 찾았다면, 그 공식을 최소로 축약해서 코드에 적용하도록 하자.
