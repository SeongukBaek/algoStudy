# [17406] 배열 돌리기 4

## :pushpin: **Algorithm**

순열, 구현

## :round_pushpin: **Logic**

```java
static void permutation(int depth, int[] order) {
	if(depth == k) {			
		tmp = cloneArray(orgnArr); //orgn 배열 복사
		for(int i=0; i<k; i++) {
			rotateArray(r[order[i]]-s[order[i]], c[order[i]]-s[order[i]], r[order[i]]+s[order[i]], c[order[i]]+s[order[i]], tmp, cloneArray(tmp));
		}
		getMinValue(tmp);
		return ;
	}
	for(int i=0; i<k; i++) {
		if(!visited[i]) {
			visited[i] = true;
			order[depth] = i;
			permutation(depth+1, order);
			visited[i] = false;
		}
	}

}
```
- 순열을 통해, 명령 순서를 바꿔주면서 값을 rotateArray()의 매개변수에 넣어 배열을 돌렸다. 

```java
static void rotateArray(int x1, int y1, int x2, int y2, int[][] tmp, int[][] cpArr) {
	if(x1 == x2 && y1 == y2) {
		return ;
	}

	// 좌 -> 우
	for(int j=y1+1; j<=y2; j++) {
		tmp[x1][j] = cpArr[x1][j-1];
	}


	// 상 -> 하
	for(int i=x1+1; i<=x2; i++) {
		tmp[i][y2] = cpArr[i-1][y2];
	}

	// 우 -> 좌
	for(int j=y2-1; j>=y1; j--) {
		tmp[x2][j] = cpArr[x2][j+1];
	}

	// 하 -> 상
	for(int i=x2-1; i>=x1; i--) {
		tmp[i][y1] = cpArr[i+1][y1];
	}

	rotateArray(x1+1, y1+1, x2-1, y2-1, tmp, cloneArray(tmp));
}
```
- 상, 우, 하, 좌 순서로 for문을 돌려 복사한 배열의 값을 tmp배열에 넣어주었다.
- 재귀함수를 이용해 (x1, y1)의 인덱스값에 +1씩해주고 (x2, y2)의 인덱스값에 -1씩해주면서 인덱스값이 같아질때까지 배열의 값을 바꿔주었다.

## :black_nib: **Review**
- 구현하는 로직을 쉽게짰으나, 배열을 복사하는 부분에서 많이 어려웠다. 
  특히 자바에서 배열을 복사할때 얕은 복사와 깊은 복사에 많이 주의해야겠다.
  - 매개변수로 배열을 넘길때는 얕은 복사이므로 값을 수정하면 원본 값도 변한다.
  - 따라서, new를 통해서 배열을 새로 생성해서 깊은 복사를 해야한다.
- 배열을 돌릴때마다 최소값을 구해야하는 줄 알고, 정답을 맞히는데 오랜 시간이 걸렸다. 배열을 명령 순으로 돌리고 난 최후의 최소값 중에서 가장 작은 값이 정답이다.
