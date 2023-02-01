# [2529] 부등호

## **Algorithm**

 - 브루트포스, 백트래킹


## **Logic**

``` java
public static void recurMin(int num, int depth) {
		
		if(count != 0)
			return;
		
		if(num == K + 1) {
			String str = Arrays.stream(result)
	                .mapToObj(String::valueOf)
	                .reduce((x, y) -> x + y)
	                .get();
			System.out.println(str);
			count = 1;
			return;
		}
		
		for(int i = 0; i<=9; i++) {
			//첫 번째 숫자이거나 방문하지 않았고 부등호가 성립하면
			if(depth == 0 || check[i] == false && compare(result[num - 1], i, c[depth-1])) {
				check[i] = true;
				result[index] = i;
				index++;
				recurMin(num+1, depth + 1);
				check[i] = false;
				index--;
			}
		}
	}
	
	
	public static void recurMax(int num, int depth) {
		
		if(count != 0)
			return;
		
		if(num == K + 1) {
			String str = Arrays.stream(result)
	                .mapToObj(String::valueOf)
	                .reduce((x, y) -> x + y)
	                .get();
			System.out.println(str);
			count = 1;
			return;
		}
		
		for(int i = 9; i>=0; i--) {
			//첫 번째 숫자이거나 방문하지 않았고 부등호가 성립하면
			if(depth == 0 || check[i] == false && compare(result[num - 1], i, c[depth-1])) {
				check[i] = true;
				result[index] = i;
				index++;
				recurMax(num+1, depth + 1);
				check[i] = false;
				index--;
			}
		}
	}
```
- 최댓값과 최솟값을 구해야하기 때문에 가장 작은 수인 0부터 9까지 순서대로 탐색했을때 첫 번째로 구해지는 숫자가 최솟값이고, 반대로 가장 큰 수인 9부터 0까지 거꾸로 탐색했을떄 처음으로 구해지는 숫자가 최댓값이라고 생각되었다. 
- 가장 첫 번째로 구해지는 숫자만 사용할 것이기 때문에 count 변수를 두어서 한 번 print 하고나면 count 값을 바꿔 recur에서 빠져나오도록 했다.
- 값을 int 배열에 넣었기 때문에 문자열로 바꿔주는 작업이 필요했다.


## **Review**
- max와 min 값을 한 번에 구할 수 있는 방법이 떠오르지 않아서 함수를 따로 만들었는데 재귀를 따로 두 번 돌릴 필요없이 한 번만 처음부터 끝까지 돌려서 가장 첫 번째 수와 마지막 수만 가져왔으면 됐을 것 같다.
- int 배열을 사용했는데 List를 사용해서 푸는 방법도 생각해봐야겠다.

