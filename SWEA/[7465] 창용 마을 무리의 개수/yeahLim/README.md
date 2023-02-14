# [7465] 창용 마을 무리의 개수

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**


```java
map = new int[n + 1];
			for (int i = 1; i < n + 1; i++) {
				map[i] = i;
			}
```
- map에 각 숫자에 숫자값을 루트값으로 대입한다.
```java
/* 다른 무리를 한 무리로 합치기 */
	static void union(int num1, int num2) {
		int root1 = searchRoot(num1);
		int root2 = searchRoot(num2);
		if(root1 != root2) {
			map[root2] = root1;
			count--;
		}
	}
	
	/* 루트값 찾기 */
	static int searchRoot(int num) {
		if(map[num] == num) {
			return num;
		}
		return map[num] = searchRoot(map[num]);
	}
```
- 무리의 개수를 숫자의 개수로 설정한다.
- 루트값이 똑같다면 두 숫자의 무리를 다른 무리로 합치고, 무리의 개수를 -1해준다.

## :black_nib: **Review**
- 루트값을 똑같이 대입해주는 방법을 이용해서 푸니까 재밌었다.
